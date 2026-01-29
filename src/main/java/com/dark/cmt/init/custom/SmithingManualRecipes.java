package com.dark.cmt.init.custom;

import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SmithingManualRecipes {
    public static final List<SmithingManualRecipe> RECIPES = new ArrayList<>();

    public static void register(SmithingManualRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static SmithingManualRecipe getRecipeFromID(String name) {
        for (SmithingManualRecipe recipe : RECIPES) {
            if (Objects.equals(recipe.getName(), name)) {
                return recipe;
            }
        }
        return null;
    }
}
