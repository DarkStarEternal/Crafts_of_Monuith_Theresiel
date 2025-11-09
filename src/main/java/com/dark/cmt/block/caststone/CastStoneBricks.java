package com.dark.cmt.block.caststone;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;

public class CastStoneBricks extends Block {
    public CastStoneBricks() {
        super(Block.Settings
                .create()
                .hardness(3f)
                .resistance(3f)
                .strength(3f)
                .requiresTool()
        );
    }
}
