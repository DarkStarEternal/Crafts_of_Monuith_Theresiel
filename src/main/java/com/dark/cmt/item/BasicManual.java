package com.dark.cmt.item;

import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.registry.CMTSmithingManualRecipes;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class BasicManual extends SmithingManual{
    public List<SmithingManualRecipe> recipeList = new ArrayList<>();

    public BasicManual() {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON));
        addToRecipeList(CMTSmithingManualRecipes.HOOK);
    }

    public List<SmithingManualRecipe> getRecipeList() {
        return recipeList;
    }

    public void addToRecipeList(SmithingManualRecipe recipe) {
        this.recipeList.add(recipe);
    }
}
