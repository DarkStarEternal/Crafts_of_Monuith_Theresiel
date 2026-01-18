package com.dark.cmt.block.ore;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class JadeBlock extends ExperienceDroppingBlock {
    public JadeBlock() {
        super(UniformIntProvider.create(10,200), Settings.create().requiresTool());
    }
}
