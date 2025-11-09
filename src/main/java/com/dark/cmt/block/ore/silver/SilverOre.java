package com.dark.cmt.block.ore.sulfur;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SulfuricOre extends ExperienceDroppingBlock {
    public SulfuricOre() {
        super(UniformIntProvider.create(6,19), Settings.create().requiresTool());
    }
}
