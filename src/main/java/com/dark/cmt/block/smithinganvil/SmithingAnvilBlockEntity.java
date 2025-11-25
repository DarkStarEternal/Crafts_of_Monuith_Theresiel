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
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class SmithingAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, Inventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    public int size() { return inventory.size(); }
    public boolean isEmpty() { return inventory.stream().allMatch(ItemStack::isEmpty); }
    public ItemStack getStack(int slot) { return inventory.get(slot); }
    public ItemStack removeStack(int slot, int count) { return Inventories.splitStack(inventory, slot, count); }
    public ItemStack removeStack(int slot) { return Inventories.removeStack(inventory, slot); }
    public void setStack(int slot, ItemStack stack) { inventory.set(slot, stack); }
    public boolean canPlayerUse(PlayerEntity player) { return true; }
    public void clear() { inventory.clear(); }

    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public SmithingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(CMTBlockEntities.SMITHINGANVILBLOCKENTITY, pos, state);
    }

    public void updateInventory(int slotID, ItemStack stack) {
        this.inventory.set(slotID, stack);
        markDirty();
        toUpdatePacket();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (world != null) {
            BlockState state = getCachedState();
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
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

    public static void tick(World world, BlockPos pos, BlockState state, SmithingAnvilBlockEntity be) {
        if (world.isClient) return;

        ItemStack stack = be.inventory.get(0);
        if (stack.getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem && unfinishedSmithedItem.isTransformStateMet(stack)) {
            be.inventory.set(0, unfinishedSmithedItem.finishedItem);
        }
    }

    public void transformCraftItem(int RecipeID, int RecipePage) {

    }

    public ItemStack getUnfinishedItemFromRecipeEntry(int page, int entry, ItemStack input){
        Item slot2 = inventory.get(2).getItem();
        if (slot2 instanceof SmithingManual manual) {
            if (getRecipeEntry(page, entry) != null) {
                if (input.isIn(getRecipeEntry(page, entry).getInput())) {
                    ItemStack stack = getRecipeEntry(page, entry).getUnfinishedOutput();
                    return stack;
                }
            }
            else {
                ItemStack stack = new ItemStack(Blocks.BARRIER.asItem());
                return stack;
            }
        }
        return new ItemStack(Blocks.AIR.asItem());
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
}
