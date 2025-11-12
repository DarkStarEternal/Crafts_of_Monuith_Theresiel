package com.dark.cmt.block.smithinganvil;

import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.registry.CMTBlockEntities;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
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

import java.util.Optional;
import java.util.stream.Stream;

public class SmithingAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, Inventory {

    private static final int CRAFTSLOT = 1;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
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
}
