package com.dark.cmt.block.ore.tin;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class DeepslateTinOre extends ExperienceDroppingBlock {
    public DeepslateTinOre() {
        super(UniformIntProvider.create(1,3), Settings.create().requiresTool().hardness(3f).resistance(3f));
    }
}
