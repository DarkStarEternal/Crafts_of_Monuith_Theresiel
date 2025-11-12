package com.dark.cmt.block.metal_blocks;

import net.minecraft.block.Block;

public class SilverBlock extends Block {
    public SilverBlock() {
        super(Settings
                .create()
                .hardness(3f)
                .resistance(3f)
                .strength(3f)
                .requiresTool()
        );
    }
}
