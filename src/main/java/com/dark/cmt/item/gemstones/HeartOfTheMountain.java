package com.dark.cmt.item.gemstones;

import net.minecraft.util.Rarity;

public class HeartOfTheMountain extends GemstoneItem{
    public HeartOfTheMountain() {
        super(new Settings().maxCount(1).rarity(Rarity.EPIC), new HeartOfTheMountainEffect());
    }
}
