package com.dark.cmt.screen.smithinganvil;

import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import com.dark.cmt.networking.C2SSmithingAnvilUpdatePayload;
import com.dark.cmt.networking.CMTNetwork;
import com.dark.cmt.registry.CMTScreenHandlers;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;



public class SmithingAnvilScreenHandler extends ScreenHandler {

    private Inventory inventory;
    private BlockPos blockPos;
    private BlockEntity blockEntity;



    public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos));
    }

    public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(CMTScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, syncId);
        if (!(blockEntity instanceof SmithingAnvilBlockEntity smithingBE))
            throw new IllegalStateException("BlockEntity is not a SmithingAnvilBlockEntity!");

        this.inventory = smithingBE;
        this.blockPos = blockEntity.getPos();
        this.blockEntity = blockEntity;

        this.addSlot(new Slot(inventory, 0, 7, 35) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });
        this.addSlot(new Slot(inventory, 1, 7, 13) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        this.addSlot(new Slot(inventory, 2, 96, 13) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });


        this.

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
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
