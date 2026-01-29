package com.dark.cmt.block.smithinganvil;

import com.dark.cmt.block.SimpleInventoryWrapper;
import com.dark.cmt.init.CMTDataComponents;
import com.dark.cmt.init.custom.MetalMaterialRegistry;
import com.dark.cmt.item.CurrentHeatComponent;
import com.dark.cmt.item.SmithingManual;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.init.CMTBlockEntities;
import com.dark.cmt.init.custom.SmithingManualRecipes;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilCombinerScreenHandler;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilPartScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SmithingAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> partInventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private final DefaultedList<ItemStack> combinerInventory = DefaultedList.ofSize(16, ItemStack.EMPTY);

    private AnvilMode mode = AnvilMode.PARTS;

    public SmithingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(CMTBlockEntities.SMITHINGANVILBLOCKENTITY, pos, state);
    }

    public enum AnvilMode {
        PARTS,
        COMBINE
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (!world.isClient) {
            sync();
        }
    }

    public void setMode(AnvilMode mode) {
        this.mode = mode;
        markDirty();
    }

    public AnvilMode getMode() {
        return mode;
    }

    public Inventory getPartInventory() {
        return new SimpleInventoryWrapper(partInventory, this);
    }

    public Inventory getCombinerInventory() {
        return new SimpleInventoryWrapper(combinerInventory, this);
    }


    public void sync() {
        if (world instanceof ServerWorld serverWorld) {
            BlockEntityUpdateS2CPacket packet = BlockEntityUpdateS2CPacket.create(this);
            serverWorld.getPlayers(p -> p.squaredDistanceTo(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5) < 64)
                    .forEach(player -> player.networkHandler.sendPacket(packet));
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Smithing Anvil");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return switch (mode) {
            case PARTS -> new SmithingAnvilPartScreenHandler(syncId, playerInventory, this);
            case COMBINE -> new SmithingAnvilCombinerScreenHandler(syncId, playerInventory, this);
        };
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, partInventory, registryLookup);
        Inventories.writeNbt(nbt, combinerInventory, registryLookup);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, partInventory, registryLookup);
        Inventories.readNbt(nbt, combinerInventory, registryLookup);
    }

    public void transformCraftItem(int recipeID, int recipePage, ServerPlayerEntity player, String material) {
        if (!isCorrectInput(recipePage, recipeID, partInventory.get(0))) return;

        ItemStack originalStack = partInventory.get(0);
        float currentTemp =  originalStack.get(CMTDataComponents.CURRENTHEAT).temperature();
        ItemStack recipeStack = getUnfinishedItemFromRecipeEntry(recipePage, recipeID, material, currentTemp);
        if (recipeStack.isEmpty()) return;

        if (recipeStack.get(DataComponentTypes.CUSTOM_DATA) == null) {
            if (recipeStack.getItem() instanceof UnfinishedSmithedItem u) {
                recipeStack = u.createNewStack(material,currentTemp);
            }
        }

        partInventory.set(0, recipeStack.copy());
        markDirty();

        if (player.currentScreenHandler instanceof SmithingAnvilPartScreenHandler handler) {
            handler.slots.get(0).setStack(recipeStack.copy()); // avoid sharing same ref
            handler.sendContentUpdates();
        }
    }


    public void applyCraftCommand(String craftStep, ServerPlayerEntity player) {
        ItemStack originalStack = partInventory.get(0);

        if (!originalStack.isEmpty() && validateCraftStep(craftStep, originalStack)) {
            if (originalStack.getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem) {
                int idx = unfinishedSmithedItem.getIndex(originalStack);
                unfinishedSmithedItem.setStringListEntry(originalStack, "CompletedCommands", craftStep, idx);
                unfinishedSmithedItem.setTemperature(originalStack, unfinishedSmithedItem.getTemperature(originalStack) - unfinishedSmithedItem.getTemperature(originalStack) / 10);
                unfinishedSmithedItem.setIndex(originalStack, idx + 1);

                ItemStack updated = originalStack.copy();
                partInventory.set(0, updated);
                markDirty();

                if (player.currentScreenHandler instanceof SmithingAnvilPartScreenHandler handler) {
                    handler.slots.get(0).setStack(updated.copy());
                    handler.sendContentUpdates();
                }
            }
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, SmithingAnvilBlockEntity be) {
        if (world.isClient) return;

        ItemStack stack = be.partInventory.get(0);
        if (stack.getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem && unfinishedSmithedItem.isTransformStateMet(stack)) {
            be.partInventory.set(0, unfinishedSmithedItem.finishedItem.copy());
        }

        if (stack.contains(CMTDataComponents.CURRENTHEAT)) {
            float temp = Objects.requireNonNull(stack.get(CMTDataComponents.CURRENTHEAT)).temperature();
            stack.set(CMTDataComponents.CURRENTHEAT, new CurrentHeatComponent(temp - 0.5f));
        }

    }

    public ItemStack getUnfinishedItemFromRecipeEntry(int page, int entry, String material, float t){
        SmithingManualRecipe recipe = getRecipeEntry(page, entry);
        if (recipe == null) return ItemStack.EMPTY;

        ItemStack stack = recipe.getUnfinishedOutput();
        if (stack.isEmpty()) return ItemStack.EMPTY;

        if (stack.get(DataComponentTypes.CUSTOM_DATA) == null) {
            Item item = stack.getItem();
            if (item instanceof UnfinishedSmithedItem unfinished) {
                return unfinished.createNewStack(material, t);
            }
        }

        return stack;
    }

    public boolean validateCraftStep(String craftStep, ItemStack input) {
        if (input.getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem) {
            if (unfinishedSmithedItem.getStringListEntry(input, "Commands",
                    unfinishedSmithedItem.getIndex(input)).equals(craftStep)) {
                SmithingMaterial material = MetalMaterialRegistry.getMaterialFromString(unfinishedSmithedItem.getMaterial(input));
                System.out.println(unfinishedSmithedItem.getMaterial(input));
                System.out.println(material.glowHeat);
                if (material.glowHeat < unfinishedSmithedItem.getTemperature(input)) {
                    return true;
                }
            }
        }
        return false;
    }

    public TagKey getInputItemFromRecipeEntry(int page, int entry){
        if (getRecipeEntry(page, entry) != null) {
            TagKey tag = getRecipeEntry(page, entry).getInput();
            return tag;
        }
        return null;
    }

    public SmithingManualRecipe getRecipeEntry(int page, int entry){
        Item slot2 = partInventory.get(2).getItem();
        if (slot2 instanceof SmithingManual manual) {

            List<SmithingManualRecipe> recipeList = new ArrayList<>();

            for (String recipeName : manual.getRecipeList(partInventory.get(2))) {
                recipeList.add(SmithingManualRecipes.getRecipeFromID(recipeName));
            }

            List<SmithingManualRecipe> pageEntries = getPageEntries(recipeList, page, 3);
            int idx = entry - 1;
            if (idx >= 0 && idx < pageEntries.size()) {
                return pageEntries.get(idx);
            }
        }
        return null;
    }

    public List<SmithingManualRecipe> getPageEntries(List<SmithingManualRecipe> list, int page, int itemsPerPage) {
        int start = page * itemsPerPage;

        if (start >= list.size())
            return Collections.emptyList();

        int end = Math.min((start + itemsPerPage), list.size());
        return list.subList(start, end);
    }

    public boolean isCorrectInput(int page, int recipeindex, ItemStack input) {
        if (input.isIn(getInputItemFromRecipeEntry(page, recipeindex))) {
            return true;
        }
        return false;
    }

}
