package com.dark.cmt.block.ore.tin;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class TinOre extends ExperienceDroppingBlock {
    public TinOre() {
        super(UniformIntProvider.create(2,5), Settings.create().requiresTool().hardness(2f).resistance(2f));
    }
}
