package com.dark.cmt.block.ore.lead;

import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class LeadOre extends ExperienceDroppingBlock {
    public LeadOre() {
        super(UniformIntProvider.create(2,5), Settings.create().requiresTool().hardness(3f).resistance(2f));
    }
}
