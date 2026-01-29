package com.dark.cmt.block.ore.silver;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SilverOre extends ExperienceDroppingBlock {
    public SilverOre() {
        super(UniformIntProvider.create(6,19), Settings.create().requiresTool().hardness(3f).resistance(2f));
    }
}
