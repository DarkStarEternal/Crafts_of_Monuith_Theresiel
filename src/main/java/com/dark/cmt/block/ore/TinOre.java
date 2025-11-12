package com.dark.cmt.block.ore;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class TinOre extends ExperienceDroppingBlock {
    public TinOre() {
        super(UniformIntProvider.create(2,5), Settings.create().requiresTool());
    }
}
