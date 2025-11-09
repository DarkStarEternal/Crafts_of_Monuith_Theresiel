package com.dark.cmt.block.ore;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SulfuricDeepslateOre extends ExperienceDroppingBlock {
    public SulfuricDeepslateOre() {
        super(UniformIntProvider.create(6,19), Settings.create().requiresTool());
    }
}
