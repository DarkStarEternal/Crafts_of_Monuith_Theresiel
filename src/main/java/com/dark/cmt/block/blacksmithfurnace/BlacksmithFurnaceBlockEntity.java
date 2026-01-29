package com.dark.cmt.block.blacksmithfurnace;

import com.dark.cmt.block.ImplementedInventory;
import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.init.CMTBlockEntities;
import com.dark.cmt.init.CMTDataComponents;
import com.dark.cmt.item.CurrentHeatComponent;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlacksmithFurnaceBlockEntity extends BlockEntity implements ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private BlockPos controllerPos = null;
    private float maxTemperature = 29f;
    private float currentTemperature = 29f;
    private float burnDuration = 0;

    public boolean addItem(ItemStack stack, int slot) {
        if (slot < 0 || slot >= 3) return false;

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

    public BlockPos getControllerPos() {
        return this.controllerPos;
    }

    public void decreaseTemperature(BlacksmithFurnaceBlockEntity be) {
        if (be.currentTemperature > 29f) {
            be.currentTemperature = be.currentTemperature - 0.05f;
        }
    }

    public void increaseTemperature(BlacksmithFurnaceBlockEntity be) {
        if (be.currentTemperature < be.maxTemperature) {
            be.currentTemperature = Math.min(be.currentTemperature + 0.5f, be.maxTemperature);

        }
    }

    public float getMaxTemperature() {
        return this.maxTemperature;
    }

    public void setMaxTemperature(float temperature) {
        this.maxTemperature = temperature;
    }

    public float getCurrentTemperature() {
        return this.currentTemperature;
    }

    public void setCurrentTemperature(float temperature) {
        this.currentTemperature = temperature;
    }

    public void setMaxTemperatureFromItem(ItemStack stack) {
        if (stack.isIn(CMTItemTagProvider.BF_FUEL_LOW)) {
            this.setMaxTemperature(200f);
        } else if (stack.isIn(CMTItemTagProvider.BF_FUEL_MEDIUM)) {
            this.setMaxTemperature(1300f);
        } else if (stack.isIn(CMTItemTagProvider.BF_FUEL_HIGH)) {
            this.setMaxTemperature(3000f);
        } else {
            this.setMaxTemperature(29f);
        }
    }

    public BlacksmithFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlacksmithFurnaceBlockEntity be) {
       if (be.getPos().equals(be.getControllerPos()) && !world.isClient()) {
           ItemStack fuel = be.inventory.get(2);

           if (!fuel.isEmpty() && be.burnDuration <= 0f) {
               int fuelTime = AbstractFurnaceBlockEntity.createFuelTimeMap()
                       .getOrDefault(fuel.getItem(), 200);
               be.burnDuration = fuelTime;
               be.setMaxTemperatureFromItem(fuel);
               fuel.decrement(1);
               be.markDirty();
           }

           if (be.burnDuration > 0f) {
               be.burnDuration -= 0.2f;
               be.increaseTemperature(be);
               if (!be.getItem(0).isEmpty()) {
                   ItemStack stack0 = be.getItem(0);
                   if (stack0.contains(CMTDataComponents.CURRENTHEAT)) {
                       float itemTemp = stack0.get(CMTDataComponents.CURRENTHEAT).temperature();
                       if (itemTemp < be.getCurrentTemperature()) {
                           float temp = itemTemp + be.getCurrentTemperature() / 100 ;
                           stack0.set(CMTDataComponents.CURRENTHEAT, new CurrentHeatComponent(temp));
                           be.markDirty();
                       }
                   } else {
                       stack0.set(CMTDataComponents.CURRENTHEAT, new CurrentHeatComponent(29f));
                       be.markDirty();
                   }
               }
               if (!be.getItem(1).isEmpty()) {
                   ItemStack stack0 = be.getItem(1);
                   if (stack0.contains(CMTDataComponents.CURRENTHEAT)) {
                       float itemTemp = stack0.get(CMTDataComponents.CURRENTHEAT).temperature();
                       if (itemTemp < be.getCurrentTemperature()) {
                           float temp = itemTemp + be.getCurrentTemperature() / 100 ;
                           stack0.set(CMTDataComponents.CURRENTHEAT, new CurrentHeatComponent(temp));
                           be.markDirty();
                       }
                   } else {
                       stack0.set(CMTDataComponents.CURRENTHEAT, new CurrentHeatComponent(29f));
                       be.markDirty();
                   }
               }
           } else {
               be.decreaseTemperature(be);
           }
       }
    }

    public ItemStack getItem(int Index) {
        return inventory.get(Index);
    }

    @Override
    public void writeNbt(NbtCompound nbt , RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        if (controllerPos != null) {
            nbt.putInt("ControllerX", controllerPos.getX());
            nbt.putInt("ControllerY", controllerPos.getY());
            nbt.putInt("ControllerZ", controllerPos.getZ());
        }
        if (burnDuration >= -999) {
            nbt.putFloat("burnTime", burnDuration);
        }
        if (currentTemperature >= -999) {
            nbt.putFloat("currentTemperature", currentTemperature);
        }
    }

    public ActionResult sendItemToController(ItemStack stack) {
        if (true) {
            BlockEntity blockEntity = this.getWorld().getBlockEntity(this.controllerPos);
            if (blockEntity instanceof BlacksmithFurnaceBlockEntity furnaceBE) {
                if (furnaceBE.getItem(0).isEmpty()) {
                    furnaceBE.addItem(stack, 0);
                    stack.decrement(1);
                    return ActionResult.SUCCESS;
                } else if (furnaceBE.getItem(1).isEmpty()) {
                    furnaceBE.addItem(stack, 1);
                    stack.decrement(1);
                    return ActionResult.SUCCESS;
                } else if (furnaceBE.getItem(2).isEmpty()) {
                    furnaceBE.addItem(stack, 2);
                    stack.decrement(1);
                    return ActionResult.SUCCESS;
                }

            }
        }
        return ActionResult.FAIL;
    }

    public ActionResult sendItemToControllerFuel(ItemStack stack) {
        BlockEntity be = world.getBlockEntity(controllerPos);
        if (!(be instanceof BlacksmithFurnaceBlockEntity furnace)) return ActionResult.FAIL;

        if (FuelRegistry.INSTANCE.get(stack.getItem()) == null) {
            return ActionResult.FAIL;
        }

        ItemStack fuelSlot = furnace.inventory.get(2);
        if (fuelSlot.isEmpty()) {
            furnace.inventory.set(2, stack.copyWithCount(1));
            stack.decrement(1);
            furnace.markDirty();
            return ActionResult.SUCCESS;
        } else if (fuelSlot.getItem() == stack.getItem()) {
            fuelSlot.increment(1);
            stack.decrement(1);
            furnace.markDirty();
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }



    public ItemStack getItemToController() {
        if (this.pos != this.controllerPos) {
            BlockEntity blockEntity = this.getWorld().getBlockEntity(this.controllerPos);
            if (blockEntity instanceof BlacksmithFurnaceBlockEntity furnaceBE) {
                if (furnaceBE.getItem(0) != ItemStack.EMPTY) {
                    ItemStack stack = furnaceBE.getItem(0);
                    furnaceBE.removeStack(0);
                    return stack;
                } else if (furnaceBE.getItem(1) != ItemStack.EMPTY) {
                    ItemStack stack = furnaceBE.getItem(1);
                    furnaceBE.removeStack(1);
                    return stack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory,registryLookup);
        if (nbt.contains("ControllerX") && nbt.contains("ControllerY") && nbt.contains("ControllerZ")) {
            controllerPos = new BlockPos(nbt.getInt("ControllerX"), nbt.getInt("ControllerY"), nbt.getInt("ControllerZ"));
        }
        if (nbt.contains("burnTime")) {
            burnDuration = nbt.getFloat("burnTime");
        }
        if (nbt.contains("currentTemperature")) {
            currentTemperature = nbt.getFloat("currentTemperature");
        }
    }

    public void setController(BlockPos controllerPos) {
        this.controllerPos = controllerPos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
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
