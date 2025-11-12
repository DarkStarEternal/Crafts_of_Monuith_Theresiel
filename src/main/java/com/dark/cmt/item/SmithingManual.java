package com.dark.cmt.item;

import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.registry.CMTSmithingManualRecipes;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class SmithingManual extends Item {

    public List<SmithingManualRecipe> recipeList = new ArrayList<>();

    public SmithingManual() {
        super(new Settings().maxCount(1).rarity(Rarity.RARE));

        addToRecipeList(CMTSmithingManualRecipes.LEADHOOK);

        addToRecipeList(CMTSmithingManualRecipes.STEELBLADE);
        addToRecipeList(CMTSmithingManualRecipes.STEELARMGUARD);
    }

    public List<SmithingManualRecipe> getRecipeList() {
        return recipeList;
    }

    public void addToRecipeList(SmithingManualRecipe recipe) {
        this.recipeList.add(recipe);
    }
}
