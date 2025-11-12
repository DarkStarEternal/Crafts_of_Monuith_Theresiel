package com.dark.cmt.block.ore.silver;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SilverDeepslateOre extends ExperienceDroppingBlock {
    public SilverDeepslateOre() {
        super(UniformIntProvider.create(6,19), Settings.create().requiresTool());
    }
}
