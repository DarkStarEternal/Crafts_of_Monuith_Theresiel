package com.dark.cmt.item.smitheditems;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.text.Text;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class UnfinishedSmithedItem extends Item {

    public List<String> commands;
    public ItemStack finishedItem;
    public float temperature = 300;

    public static final String COMMANDS_KEY = "Commands";
    public static final String COMPLETED_KEY = "CompletedCommands";
    public static final String CURRENT_INDEX_KEY = "CurrentIndex";
    public static final String TEMPERATURE_KEY = "Temperature";
    public static final String MATERIAL_KEY = "Material";

    public UnfinishedSmithedItem(Settings settings, List<String> givenCommands, Item finishedItem) {
        super(settings);
        this.commands = givenCommands;
        this.finishedItem = new ItemStack(finishedItem);
    }

    private NbtCompound getOrCreateData(ItemStack stack) {
        NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);
        return comp != null ? comp.getNbt().copy() : new NbtCompound();
    }

    private void saveData(ItemStack stack, NbtCompound data) {
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(data));
    }

    public List<String> getStringList(ItemStack stack, String key) {
        List<String> out = new ArrayList<>();
        NbtCompound nbt = getOrCreateData(stack);

        if (!nbt.contains(key, NbtElement.LIST_TYPE)) {
            return out;
        }

        NbtList list = nbt.getList(key, NbtElement.STRING_TYPE);
        for (int i = 0; i < list.size(); i++) {
            out.add(list.getString(i));
        }
        return out;
    }

    public String getStringListEntry(ItemStack stack, String key, int index) {
        NbtCompound nbt = getOrCreateData(stack);
        if (!nbt.contains(key, NbtElement.LIST_TYPE)) return "";

        NbtList list = nbt.getList(key, NbtElement.STRING_TYPE);
        if (index < 0 || index >= list.size()) return "";

        return list.getString(index);
    }

    public void setStringListEntry(ItemStack stack, String key, String value, int index) {
        NbtCompound nbt = getOrCreateData(stack);

        NbtList list = nbt.contains(key, NbtElement.LIST_TYPE)
                ? nbt.getList(key, NbtElement.STRING_TYPE)
                : new NbtList();

        while (list.size() <= index) {
            list.add(NbtString.of(""));
        }

        list.set(index, NbtString.of(value));
        nbt.put(key, list);
        saveData(stack, nbt);
    }

    public float getTemperature(ItemStack stack) {
        NbtCompound nbt = getOrCreateData(stack);
        return nbt.contains(TEMPERATURE_KEY, NbtElement.FLOAT_TYPE)
                ? nbt.getFloat(TEMPERATURE_KEY)
                : 300f;
    }

    public void setTemperature(ItemStack stack, float t) {
        NbtCompound nbt = getOrCreateData(stack);
        nbt.putFloat(TEMPERATURE_KEY, t);
        saveData(stack, nbt);
    }

    public String getMaterial(ItemStack stack) {
        NbtCompound nbt = getOrCreateData(stack);
        return nbt.contains(MATERIAL_KEY, NbtElement.STRING_TYPE)
                ? nbt.getString(MATERIAL_KEY)
                : "";
    }

    public int getIndex(ItemStack stack) {
        NbtCompound nbt = getOrCreateData(stack);
        return nbt.getInt(CURRENT_INDEX_KEY);
    }

    public void setIndex(ItemStack stack, int index) {
        NbtCompound nbt = getOrCreateData(stack);
        nbt.putInt(CURRENT_INDEX_KEY, index);
        saveData(stack, nbt);
    }

    public boolean isTransformStateMet(ItemStack stack) {
        return getTemperature(stack) <= 30 &&
                getStringList(stack, COMPLETED_KEY).equals(getStringList(stack, COMMANDS_KEY));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient && isTransformStateMet(stack)) {
            ItemStack finishedStack = this.finishedItem.copy();
            if (entity instanceof PlayerEntity player) {
                player.getInventory().setStack(slot, finishedStack);
            } else if (entity instanceof ItemEntity itemEntity) {
                itemEntity.setStack(finishedStack);
            }
        }
        else if (!world.isClient && getTemperature(stack) >= 30) {
            setTemperature(stack, getTemperature(stack) - 0.2f);
        }
    }

    public ItemStack createNewStack(String material) {
        ItemStack stack = new ItemStack(this);

        NbtCompound nbt = new NbtCompound();

        NbtList cmds = new NbtList();
        for (String cmd : this.commands) {
            cmds.add(NbtString.of(cmd));
        }
        nbt.put(COMMANDS_KEY, cmds);

        nbt.put(COMPLETED_KEY, new NbtList());
        nbt.putInt(CURRENT_INDEX_KEY, 0);
        nbt.putFloat(TEMPERATURE_KEY, this.temperature);
        nbt.putString(MATERIAL_KEY, material);

        saveData(stack, nbt);

        stack.set(
                DataComponentTypes.CUSTOM_NAME,
                Text.literal("Unfinished Hook [" + material + "]").setStyle(Style.EMPTY.withItalic(false))
        );

        return stack;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient && user instanceof ServerPlayerEntity sp) {

            sp.sendMessage(Text.literal("Completed: " + getStringList(stack, COMPLETED_KEY)), false);
            sp.sendMessage(Text.literal("Commands: " + getStringList(stack, COMMANDS_KEY)), false);
            sp.sendMessage(Text.literal("Temperature: " + getTemperature(stack)), false);
            sp.sendMessage(Text.literal("Material: " + getMaterial(stack)), false);
        }

        return ActionResult.SUCCESS_NO_ITEM_USED;
    }
}
