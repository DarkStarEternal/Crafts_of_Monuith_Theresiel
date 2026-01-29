package com.dark.cmt.block.ore.phosphorus;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class PhosphoricStone extends ExperienceDroppingBlock {
    public PhosphoricStone() {
        super(UniformIntProvider.create(2,9), Settings.create().requiresTool().hardness(1.5f).resistance(2f));
    }
}
