package com.dark.cmt.block.ore.sulfur;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SulfuricNetherOre extends ExperienceDroppingBlock {
    public SulfuricNetherOre() {
        super(UniformIntProvider.create(11,29), Settings.create().requiresTool());
    }
}
