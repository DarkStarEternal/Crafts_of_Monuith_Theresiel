package com.dark.cmt.item;

import com.dark.cmt.init.custom.SmithingManualRecipes;
import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class CreativeManual extends SmithingManual{
    public CreativeManual() {
        super(new Settings().maxCount(1).rarity(Rarity.EPIC));
    }
}
