package com.dark.cmt.item;

import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SmithingManual extends Item {

    public List<String> recipeList = new ArrayList<>();

    public SmithingManual(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }

    public List<Identifier> getRecipeList(ItemStack stack) {
        List<Identifier> result = new ArrayList<>();

        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound nbt = component != null ? component.copyNbt() : new NbtCompound();
        NbtList list;
        if (nbt.contains("Recipes", NbtElement.LIST_TYPE)) {
            list = nbt.getList("Recipes", NbtElement.STRING_TYPE);
        } else {
            list = new NbtList();
        }

        for (int i = 0; i < list.size(); i++) {

            String base = list.getString(i);
            String[] split = base.split(":");
            List<String> lSplit = Arrays.stream(split).toList();

            Identifier id = Identifier.of(lSplit.get(0), lSplit.get(1));
            result.add(id);
        }

        return result;
    }

    public void addAndReturn(ItemStack stack, Identifier preset) {
        NbtComponent component = stack.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound nbt = component != null ? component.copyNbt() : new NbtCompound();

        NbtList list;
        if (nbt.contains("Recipes", NbtElement.LIST_TYPE)) {
            list = nbt.getList("Recipes", NbtElement.STRING_TYPE);
        } else {
            list = new NbtList();
        }

        String value = preset.toString();
        System.out.println(value);
        // Prevent duplicates
        for (int i = 0; i < list.size(); i++) {
            if (list.getString(i).equals(value)) {
                return;
            }
        }

        list.add(NbtString.of(value));
        nbt.put("Recipes", list);

        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

}
