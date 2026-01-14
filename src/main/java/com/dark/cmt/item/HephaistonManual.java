package com.dark.cmt.item;

import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class HephaistonManual extends SmithingManual{

    public HephaistonManual() {
        super(new Settings().maxCount(1).rarity(Rarity.RARE));
    }
}
