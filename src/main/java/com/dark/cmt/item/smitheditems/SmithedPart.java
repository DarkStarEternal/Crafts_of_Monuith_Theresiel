package com.dark.cmt.item.smitheditems;

import com.dark.cmt.init.CMTDataComponents;
import com.dark.cmt.item.CurrentHeatComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
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

    public ItemStack createNewStack(String material, float temp, String type, String path) {
        ItemStack stack = new ItemStack(this);

        NbtCompound nbt = new NbtCompound();

        nbt.putString(MATERIAL_KEY, material);
        nbt.putString(PATH_KEY, path);
        nbt.putString(TYPE_KEY, type);

        saveData(stack, nbt);

        stack.set(
                DataComponentTypes.CUSTOM_NAME,
                Text.literal(this.itemType + " [" + material + "]").setStyle(Style.EMPTY.withItalic(false))
        );
        stack.set(
                CMTDataComponents.CURRENTHEAT,
                new CurrentHeatComponent(temp)
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

    private void saveData(ItemStack stack, NbtCompound data) {
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(data));
    }
}
