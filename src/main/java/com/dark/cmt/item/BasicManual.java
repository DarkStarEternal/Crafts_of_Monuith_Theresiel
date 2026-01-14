package com.dark.cmt.item;

import com.dark.cmt.init.CMTSmithingManualRecipes;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class BasicManual extends SmithingManual{
    public BasicManual() {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON));

    }
}
