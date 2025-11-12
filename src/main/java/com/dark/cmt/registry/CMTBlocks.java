package com.dark.cmt.registry;

import com.dark.cmt.CMT;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceBase;
import com.dark.cmt.block.caststone.CastStone;
import com.dark.cmt.block.caststone.CastStoneBricks;
import com.dark.cmt.block.caststone.tiles.CastStoneTiles;
import com.dark.cmt.block.caststone.tiles.CastStoneTilesStairs;
import com.dark.cmt.block.metal_blocks.SilverBlock;
import com.dark.cmt.block.ore.LeadOre;
import com.dark.cmt.block.ore.silver.SilverDeepslateOre;
import com.dark.cmt.block.ore.silver.SilverOre;
import com.dark.cmt.block.ore.sulfur.SulfuricDeepslateOre;
import com.dark.cmt.block.ore.sulfur.SulfuricNetherOre;
import com.dark.cmt.block.ore.sulfur.SulfuricOre;
import com.dark.cmt.block.ore.TinOre;
import com.dark.cmt.block.smithinganvil.SmithingAnvil;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTBlocks {
    public static final Block CASTSTONEBRICKS = registerBlockWithItem("cast_stone_bricks", new CastStoneBricks());
    public static final Block CASTSTONE = registerBlockWithItem("cast_stone", new CastStone());
    public static final Block CASTSTONETILES = registerBlockWithItem("cast_stone_tiles", new CastStoneTiles());
    public static final Block CASTSTONETILESSTAIRS = registerBlockWithItem("cast_stone_tiles_stairs", new CastStoneTilesStairs());

    public static final Block LEADORE = registerBlockWithItem("lead_ore", new LeadOre());

    public static final Block TINORE = registerBlockWithItem("tin_ore", new TinOre());

    public static final Block SULFURICORE = registerBlockWithItem("sulfuric_ore", new SulfuricOre());
    public static final Block SULFURICDEEPSLATEORE = registerBlockWithItem("sulfuric_deepslate_ore", new SulfuricDeepslateOre());
    public static final Block SULFURICNETHERORE = registerBlockWithItem("sulfuric_nether_ore", new SulfuricNetherOre());

    public static final Block SILVERORE = registerBlockWithItem("silver_ore", new SilverOre());
    public static final Block SILVERDEEPSLATEORE = registerBlockWithItem("silver_deepslate_ore", new SilverDeepslateOre());

    public static final Block SILVERBLOCK = registerBlockWithItem("silver_block", new SilverBlock());

    public static final Block BLACKSMITHFURNACEBASE = registerBlockWithItem("blacksmith_furnace_base", new BlacksmithFurnaceBase());

    public static final Block SMITHINGANVIL = registerBlockWithItem("smithing_anvil", new SmithingAnvil());

    private static Block registerBlockWithItem(String name, Block block) {
        // Register the block
        Registry.register(Registries.BLOCK, Identifier.of(CMT.MODID, name), block);

        // Register the block item
        Registry.register(Registries.ITEM, Identifier.of(CMT.MODID, name),
                new BlockItem(block, new Item.Settings()));

        return block;
    }
    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(CMT.MODID, name), block);
    }

    public static void registerModBlocks() {

    }
}
