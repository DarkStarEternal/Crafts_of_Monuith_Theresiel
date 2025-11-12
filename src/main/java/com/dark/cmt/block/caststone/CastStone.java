package com.dark.cmt.block.caststone;

import net.minecraft.block.Block;

public class CastStone extends Block {
    public CastStone() {
        super(Settings
                .create()
                .hardness(3f)
                .resistance(3f)
                .strength(3f)
                .requiresTool()
        );
    }
}
