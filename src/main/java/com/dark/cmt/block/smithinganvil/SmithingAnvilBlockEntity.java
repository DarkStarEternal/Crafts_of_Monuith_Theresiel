package com.dark.cmt.block.smithinganvil;

import com.dark.cmt.item.SmithingManual;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.registry.CMTBlockEntities;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
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
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
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

import java.util.Collections;
import java.util.List;

public class SmithingAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, Inventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(inventory, slot, amount);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.squaredDistanceTo(
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5
        ) <= 64.0;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    public SmithingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(CMTBlockEntities.SMITHINGANVILBLOCKENTITY, pos, state);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (!world.isClient) {
            sync();
        }
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
        return new SmithingAnvilScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    public void transformCraftItem(int recipeID, int recipePage, ServerPlayerEntity player) {
        if (isCorrectInput(recipePage, recipeID, inventory.get(0))) {
            ItemStack newStack = getUnfinishedItemFromRecipeEntry(recipePage, recipeID);
            if (!newStack.isEmpty()) {
                // Replace the stack fully in the inventory
                inventory.set(0, newStack.copy());
                markDirty();

                // Update the player's open screen if applicable
                if (player.currentScreenHandler instanceof SmithingAnvilScreenHandler handler) {
                    handler.slots.get(0).setStack(newStack.copy()); // avoid sharing same reference
                    handler.sendContentUpdates();
                }
            }
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, SmithingAnvilBlockEntity be) {
        if (world.isClient) return;

        ItemStack stack = be.inventory.get(0);
        if (stack.getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem && unfinishedSmithedItem.isTransformStateMet(stack)) {
            be.inventory.set(0, unfinishedSmithedItem.finishedItem);
        }
    }

    public ItemStack getUnfinishedItemFromRecipeEntry(int page, int entry){
        if (getRecipeEntry(page, entry) != null) {
            ItemStack stack = getRecipeEntry(page, entry).getUnfinishedOutput();
            return stack;
        }
        return new ItemStack(Blocks.AIR.asItem());
    }

    public TagKey getInputItemFromRecipeEntry(int page, int entry){
        if (getRecipeEntry(page, entry) != null) {
            TagKey tag = getRecipeEntry(page, entry).getInput();
            return tag;
        }
        return null;
    }

    public SmithingManualRecipe getRecipeEntry(int page, int entry){
        Item slot2 = inventory.get(2).getItem();
        if (slot2 instanceof SmithingManual manual) {
            if (getPageEntries(manual.getRecipeList(), page, 3).get(entry-1) != null) {
                return getPageEntries(manual.getRecipeList(), page, 3).get(entry-1);
            }
            else {
                return null;
            }
        }
        return null;
    }

    public List<SmithingManualRecipe> getPageEntries(List<SmithingManualRecipe> list, int page, int itemsPerPage) {
        int start = page * itemsPerPage;

        // Prevent out of bounds
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
