package com.dark.cmt.block.ore.lead;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class DeepslateLeadOre extends ExperienceDroppingBlock {
    public DeepslateLeadOre() {
        super(UniformIntProvider.create(4,6), Settings.create().requiresTool().hardness(2f).resistance(3f));
    }
}
