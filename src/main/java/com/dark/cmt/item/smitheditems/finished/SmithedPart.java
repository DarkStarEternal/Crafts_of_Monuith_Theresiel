package com.dark.cmt.item.smitheditems.finished;

import com.dark.cmt.init.CMTDataComponents;
import com.dark.cmt.item.CurrentHeatComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class SmithedPart extends Item {
    private String path;
    private String itemType;

    public static final String MATERIAL_KEY = "Material";
    public static final String TYPE_KEY = "Type";
    public static final String PATH_KEY = "Path";

    public SmithedPart(Settings settings, String path, String itemType) {
        super(settings);
        this.path = path;
        this.itemType = itemType;
    }

    public ItemStack createNewStack(String type, String path) {
        ItemStack stack = new ItemStack(this);

        NbtCompound nbt = new NbtCompound();

        String material = nbt.getString(MATERIAL_KEY);
        nbt.putString(PATH_KEY, path);
        nbt.putString(TYPE_KEY, type);

        saveData(stack, nbt);

        stack.set(
                DataComponentTypes.CUSTOM_NAME,
                Text.literal(this.itemType + " [" + material + "]").setStyle(Style.EMPTY.withItalic(false))
        );

        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient && getTemperature(stack) >= 29) {
            setTemperature(stack, getTemperature(stack) - 0.2f);
        }
    }

    public float getTemperature(ItemStack stack) {
        if (stack.contains(CMTDataComponents.CURRENTHEAT)) {
            return (stack.get(CMTDataComponents.CURRENTHEAT)).temperature();
        }
        return 29f;
    }

    public void setTemperature(ItemStack stack, float t) {
        stack.set(CMTDataComponents.CURRENTHEAT, new CurrentHeatComponent(t));
    }

    public String getMaterial(ItemStack stack) {
        NbtCompound nbt = getOrCreateData(stack);
        return nbt.contains(MATERIAL_KEY, NbtElement.STRING_TYPE)
                ? nbt.getString(MATERIAL_KEY)
                : "";
    }

    public void setMaterial(ItemStack stack, String material) {
        NbtCompound nbt = getOrCreateData(stack);
        nbt.putString(MATERIAL_KEY, material);
        saveData(stack, nbt);
    }

    private NbtCompound getOrCreateData(ItemStack stack) {
        NbtComponent comp = stack.get(DataComponentTypes.CUSTOM_DATA);
        return comp != null ? comp.getNbt().copy() : new NbtCompound();
    }

    private void saveData(ItemStack stack, NbtCompound data) {
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(data));
    }
}
