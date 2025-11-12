package com.dark.cmt.block.caststone.tiles;

import net.minecraft.block.Block;

public class CastStoneTiles extends Block {
    public CastStoneTiles() {
        super(Settings
                .create()
                .hardness(3f)
                .resistance(3f)
                .strength(3f)
                .requiresTool()
        );
    }
}
