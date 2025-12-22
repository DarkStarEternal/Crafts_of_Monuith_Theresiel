package com.dark.cmt.item;

import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.registry.CMTSmithingManualRecipes;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class SmithingManual extends Item {

    public List<SmithingManualRecipe> recipeList = new ArrayList<>();

    public SmithingManual(Settings settings) {
        super(settings);
    }

    public List<SmithingManualRecipe> getRecipeList() {
        return recipeList;
    }

    public void addToRecipeList(SmithingManualRecipe recipe) {
        this.recipeList.add(recipe);
    }
}
