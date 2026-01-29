package com.dark.cmt.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class SimpleInventoryWrapper implements Inventory {
    private final DefaultedList<ItemStack> stacks;
    private final BlockEntity be;

    public SimpleInventoryWrapper(DefaultedList<ItemStack> stacks, BlockEntity be) {
        this.stacks = stacks;
        this.be = be;
    }

    @Override public int size() { return stacks.size(); }
    @Override public boolean isEmpty() { return stacks.stream().allMatch(ItemStack::isEmpty); }
    @Override public ItemStack getStack(int slot) { return stacks.get(slot); }
    @Override public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(stacks, slot, amount);
        be.markDirty();
        return result;
    }
    @Override public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(stacks, slot);
        be.markDirty();
        return result;
    }
    @Override public void setStack(int slot, ItemStack stack) {
        stacks.set(slot, stack);
        be.markDirty();
    }

    @Override
    public void markDirty() {
    }

    @Override public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
    @Override public void clear() {
        stacks.clear();
        be.markDirty();
    }
}
