package com.dark.cmt.block.caststone.tiles;

import com.dark.cmt.registry.CMTBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class CastStoneTilesStairs extends StairsBlock {
    public CastStoneTilesStairs() {
        super(CMTBlocks.CASTSTONETILES.getDefaultState(), Settings
                .create()
                .requiresTool()
                .hardness(3f)
                .strength(3f)
                .resistance(3f));
    }
}
