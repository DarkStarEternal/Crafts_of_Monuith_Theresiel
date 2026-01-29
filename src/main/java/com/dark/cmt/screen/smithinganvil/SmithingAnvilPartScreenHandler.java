package com.dark.cmt.screen.smithinganvil;

import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import com.dark.cmt.init.CMTScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class SmithingAnvilPartScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private BlockPos blockPos;
    private BlockEntity blockEntity;

    public SmithingAnvilPartScreenHandler(int syncId, PlayerInventory playerInv, BlockPos pos) {
        this(syncId, playerInv,
                (SmithingAnvilBlockEntity) playerInv.player.getWorld().getBlockEntity(pos));
        this.blockEntity = playerInv.player.getWorld().getBlockEntity(pos);
        this.blockPos = pos;
    }

    public SmithingAnvilPartScreenHandler(int syncId, PlayerInventory playerInv,
                                          SmithingAnvilBlockEntity be) {
        super(CMTScreenHandlers.SMITHINGANVILPARTSCREENHANDLER, syncId);

        this.inventory = be.getPartInventory();

        addSlot(new Slot(inventory, 0, 7, 35) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 1, 7, 13) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 2, 96, 13){ public int getMaxItemCount() { return 1; }});

        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);
    }


    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public BlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
