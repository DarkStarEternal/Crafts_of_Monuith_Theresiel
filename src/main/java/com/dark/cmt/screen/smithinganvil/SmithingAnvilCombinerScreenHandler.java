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

public class SmithingAnvilCombinerScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private BlockPos blockPos;
    private BlockEntity blockEntity;

    public SmithingAnvilCombinerScreenHandler(int syncId, PlayerInventory playerInv, BlockPos pos) {
        this(syncId, playerInv,
                (SmithingAnvilBlockEntity) playerInv.player.getWorld().getBlockEntity(pos));
        this.blockEntity = playerInv.player.getWorld().getBlockEntity(pos);
        this.blockPos = pos;
    }

    public SmithingAnvilCombinerScreenHandler(int syncId, PlayerInventory playerInv,
                                              SmithingAnvilBlockEntity be) {
        super(CMTScreenHandlers.SMITHINGANVILCOMBINERSCREENHANDLER, syncId);

        this.inventory = be.getCombinerInventory();

        addSlot(new Slot(inventory, 0, 7, 7) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 1, 25, 7) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 2, 43, 7){ public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 3, 61, 7){ public int getMaxItemCount() { return 1; }});

        addSlot(new Slot(inventory, 4, 7, 25) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 5, 25, 25) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 6, 43, 25){ public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 7, 61, 25){ public int getMaxItemCount() { return 1; }});

        addSlot(new Slot(inventory, 8, 7, 43) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 9, 25, 43) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 10, 43, 43){ public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 11, 61, 43){ public int getMaxItemCount() { return 1; }});

        addSlot(new Slot(inventory, 12, 7, 61) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 13, 25, 61) { public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 14, 43, 61){ public int getMaxItemCount() { return 1; }});
        addSlot(new Slot(inventory, 15, 61, 61){ public int getMaxItemCount() { return 1; }});

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
