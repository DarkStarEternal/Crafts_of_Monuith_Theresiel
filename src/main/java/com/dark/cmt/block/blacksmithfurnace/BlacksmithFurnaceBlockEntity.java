package com.dark.cmt.block.blacksmithfurnace;

import com.dark.cmt.registry.CMTBlockEntities;
import com.dark.cmt.registry.CMTBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public class BlacksmithFurnaceBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private BlockPos controllerPos = null;
    private final RegistryWrapper.WrapperLookup registries = new RegistryWrapper.WrapperLookup() {
        @Override
        public Stream<RegistryKey<? extends Registry<?>>> streamAllRegistryKeys() {
            return Stream.empty();
        }

        @Override
        public <T> Optional<RegistryWrapper.Impl<T>> getOptionalWrapper(RegistryKey<? extends Registry<? extends T>> registryRef) {
            return Optional.empty();
        }
    };

    public boolean addItem(ItemStack stack, int slot) {
        if (slot < 0 || slot >= 2) return false;

        if (inventory.get(slot).isEmpty() && !stack.isEmpty()) {
            inventory.set(slot, stack.copyWithCount(1)); // Only allow one item
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
            }
            return true;
        }

        return false;
    }


    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public BlacksmithFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, pos, state);
    }

    public ItemStack getItem(int Index) {
        return inventory.get(Index);
    }

    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory, registries);
        if (controllerPos != null) {
            nbt.putLong("Controller", controllerPos.asLong());
        }

        super.writeNbt(nbt,registries);
    }

    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory,registries);
        if (nbt.contains("Controller")) {
            controllerPos = BlockPos.fromLong(nbt.getLong("Controller"));
        }

        super.readNbt(nbt,registries);
    }

    public void setController(BlockPos controllerPos) {
        this.controllerPos = controllerPos;
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
}
