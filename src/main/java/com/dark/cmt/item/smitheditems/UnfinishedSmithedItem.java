package com.dark.cmt.item.smitheditems;

import com.dark.cmt.registry.CMTItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UnfinishedSmithedItem extends Item {

    public List<String> commands;
    public List<String> completedCommands;
    public int currentCommandIndex = 0;
    public ItemStack finishedItem;
    public float temperature = 300;

    private static final String COMMANDS_KEY = "Commands";
    private static final String COMPLETED_KEY = "CompletedCommands";
    private static final String CURRENT_INDEX_KEY = "CurrentIndex";
    private static final String TEMPERATURE_KEY = "Temperature";

    public UnfinishedSmithedItem(Settings settings, List<String> givenCommands, Item finishedItem) {
        super(settings);
        this.commands = givenCommands;
        this.finishedItem = new ItemStack(finishedItem);
    }

    private String getStringListEntry(ItemStack stack, String key, int index) {
        NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);

        if (comp != null) {
            var nbt = comp.copyNbt();
            if (nbt.contains(key)) {
                var list = nbt.getList(key, 8);
                return list.getString(index);

            }
        }
        return "";
    }

    private List<String> getStringList(ItemStack stack, String key) {
        NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);
        List<String> result = new ArrayList<>();

        if (comp != null) {
            var nbt = comp.copyNbt();
            if (nbt.contains(key)) {
                var list = nbt.getList(key, 8);
                for (int i = 0; i < list.size(); i++) {
                    result.add(list.getString(i));
                }
            }
        }

        return result;
    }


    private void setStringList(ItemStack stack, String key, List<String> data) {
        var listTag = new net.minecraft.nbt.NbtList();
        for (String s : data) listTag.add(net.minecraft.nbt.NbtString.of(s));

        var comp = Optional.ofNullable(stack.get(DataComponentTypes.CUSTOM_DATA))
                .map(NbtComponent::copyNbt) // clone existing if any
                .orElse(new NbtCompound());

        var nbt = comp.copy();
        nbt.put(key, listTag);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    private int getIndex(ItemStack stack) {
        NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (comp == null) return 0;
        return comp.copyNbt().getInt(CURRENT_INDEX_KEY);
    }

    private void setIndex(ItemStack stack, int i) {
        var comp = Optional.ofNullable(stack.get(DataComponentTypes.CUSTOM_DATA))
                .map(NbtComponent::copyNbt)
                .orElse(new NbtCompound());

        NbtCompound nbt = comp.copy();
        nbt.putInt(CURRENT_INDEX_KEY, i);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }


    private float getTemperature(ItemStack stack) {
        NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (comp == null) return 300F;
        return comp.copyNbt().getFloat(TEMPERATURE_KEY);
    }

    private void setTemperature(ItemStack stack, float t) {
        var comp = Optional.ofNullable(stack.get(DataComponentTypes.CUSTOM_DATA))
                .map(NbtComponent::copyNbt)
                .orElse(new NbtCompound());

        NbtCompound nbt = comp.copy();
        nbt.putFloat(TEMPERATURE_KEY, t);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }


    private ItemStack getFinishedItem() {
        return this.finishedItem;
    }


    private void setFinishedItem(ItemStack stack) {
        this.finishedItem = stack;
    }

    public void createCompletedList() {
        this.completedCommands = new ArrayList<>();
    }

    public void validateCommand(String inputCommand, ItemStack stack) {
        if (getStringList(stack, COMMANDS_KEY).get(getIndex(stack)).equals(inputCommand)) {
            List<String> completedList = getStringList(stack, COMPLETED_KEY);

            completedList.add(getStringListEntry(stack, COMMANDS_KEY, getIndex(stack)));
            setIndex(stack, getIndex(stack) + 1);
        }
    }

    public void addToCommands(String input) {
        this.commands.add(input);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient && isTransformStateMet(stack)) {
            ItemStack finishedStack = this.finishedItem;

            if (entity instanceof PlayerEntity player) {
                player.getInventory().setStack(slot, finishedStack);
            } else if (entity instanceof ItemEntity itemEntity) {
                itemEntity.setStack(finishedStack);
            }
        }
        else if (!world.isClient && this.temperature >= 30) {
            setTemperature(stack, getTemperature(stack) - 0.2f);
        }
    }

    public boolean isTransformStateMet(ItemStack stack) {
        return getTemperature(stack) <= 30 && getStringList(stack, COMPLETED_KEY).equals(getStringList(stack, COMMANDS_KEY));
    }



    public ItemStack createNewStack() {
        ItemStack stack = new ItemStack(this);

        NbtCompound nbt = new NbtCompound();

        var list = new net.minecraft.nbt.NbtList();
        for (String command : this.commands) {
            list.add(net.minecraft.nbt.NbtString.of(command));
        }
        nbt.put(COMMANDS_KEY, list);

        // Start with an empty completed list
        nbt.put(COMPLETED_KEY, new net.minecraft.nbt.NbtList());

        // Set starting command index + temp
        nbt.putInt(CURRENT_INDEX_KEY, 0);
        nbt.putFloat(TEMPERATURE_KEY, this.temperature);

        // Attach to stack
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));

        return stack;
    }
}
