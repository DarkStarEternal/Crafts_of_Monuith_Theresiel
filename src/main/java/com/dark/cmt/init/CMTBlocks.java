package com.dark.cmt.init;

import com.dark.cmt.CMT;

import com.dark.cmt.block.knowledgestones.KnowledgeStoneBase;
import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceBase;
import com.dark.cmt.block.ore.JadeBlock;
import com.dark.cmt.block.ore.lead.DeepslateLeadOre;
import com.dark.cmt.block.ore.lead.LeadOre;
import com.dark.cmt.block.ore.phosphorus.PhosphoricStone;
import com.dark.cmt.block.ore.silver.SilverDeepslateOre;
import com.dark.cmt.block.ore.silver.SilverOre;
import com.dark.cmt.block.ore.sulfur.SulfuricDeepslateOre;
import com.dark.cmt.block.ore.sulfur.SulfuricNetherOre;
import com.dark.cmt.block.ore.sulfur.SulfuricOre;
import com.dark.cmt.block.ore.tin.DeepslateTinOre;
import com.dark.cmt.block.ore.tin.TinOre;
import com.dark.cmt.block.pedestal.PedestalBlock;
import com.dark.cmt.block.smithinganvil.SmithingAnvil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTBlocks {
    public static final Block BURNT_TERRACOTTA = registerBlockWithItem("burnt_terracotta", new Block(AbstractBlock.Settings.create().requiresTool().hardness(2).resistance(2)));
    public static final Block ORNATE_BURNT_TERRACOTTA = registerBlockWithItem("ornate_burnt_terracotta", new Block(AbstractBlock.Settings.create().requiresTool().hardness(3).resistance(2)));

    public static final Block LEADORE = registerBlockWithItem("lead_ore", new LeadOre());
    public static final Block DEEPSLATELEADORE = registerBlockWithItem("deepslate_lead_ore", new DeepslateLeadOre());

    public static final Block TINORE = registerBlockWithItem("tin_ore", new TinOre());
    public static final Block DEEPSLATETINORE = registerBlockWithItem("deepslate_tin_ore", new DeepslateTinOre());

    public static final Block PHOSPHORICSTONE = registerBlockWithItem("phosphoric_stone", new PhosphoricStone());

    public static final Block SULFURICORE = registerBlockWithItem("sulfuric_ore", new SulfuricOre());
    public static final Block SULFURICDEEPSLATEORE = registerBlockWithItem("sulfuric_deepslate_ore", new SulfuricDeepslateOre());
    public static final Block SULFURICNETHERORE = registerBlockWithItem("sulfuric_nether_ore", new SulfuricNetherOre());

    public static final Block SILVERORE = registerBlockWithItem("silver_ore", new SilverOre());
    public static final Block DEEPSLATESILVERORE = registerBlockWithItem("deepslate_silver_ore", new SilverDeepslateOre());

    public static final Block JADEBLOCK = registerBlockWithItem("jade_block", new JadeBlock());

    public static final Block SILVERBLOCK = registerBlockWithItem("silver_block", new Block(AbstractBlock.Settings.create().hardness(3f).resistance(3f).strength(3f).requiresTool()));
    public static final Block STEELBLOCK = registerBlockWithItem("steel_block", new Block(AbstractBlock.Settings.create().hardness(4f).resistance(5f).strength(3.6f).requiresTool()));
    public static final Block LEADBLOCK = registerBlockWithItem("lead_block", new Block(AbstractBlock.Settings.create().hardness(2.5f).resistance(3f).strength(1.2f).requiresTool()));
    public static final Block TINBLOCK = registerBlockWithItem("tin_block", new Block(AbstractBlock.Settings.create().hardness(3f).resistance(3.3f).strength(2f).requiresTool()));
    public static final Block BRONZEBLOCK = registerBlockWithItem("bronze_block", new Block(AbstractBlock.Settings.create().hardness(3.1f).resistance(4f).strength(2.5f).requiresTool()));

    public static final Block BLACKSMITHFURNACEBASE = registerBlockWithItem("blacksmith_furnace_base", new BlacksmithFurnaceBase());
    public static final Block KNOWLEDGESTONE = registerBlockWithItem("knowledge_stone", new KnowledgeStoneBase(
            AbstractBlock.Settings.copy(Blocks.BEDROCK)
                    .hardness(3f)
                    .resistance(3f)
                    .strength(3f)
                    .nonOpaque()));
    public static final Block SMITHINGANVIL = registerBlockWithItem("smithing_anvil", new SmithingAnvil(
            AbstractBlock.Settings
                    .copy(Blocks.IRON_BLOCK)
                    .hardness(3f)
                    .resistance(3f)
                    .strength(3f)
                    .requiresTool()
                    .nonOpaque()));
    public static final Block PEDESTAL = registerBlockWithItem("pedestal", new PedestalBlock(AbstractBlock.Settings.create().nonOpaque()));

    public static final Block STEELCOINSTACK = registerBlockWithItem("steel_coinstack", new Block(AbstractBlock.Settings.create().hardness(1f).resistance(2f).strength(1.1f).requiresTool()));
    public static final Block MIXEDCOINSTACK = registerBlockWithItem("mixed_coinstack", new Block(AbstractBlock.Settings.create().hardness(1f).resistance(2f).strength(1.1f).requiresTool()));
    public static final Block GOLDCOINSTACK = registerBlockWithItem("gold_coinstack", new Block(AbstractBlock.Settings.create().hardness(1f).resistance(2f).strength(1.1f).requiresTool()));

    private static Block registerBlockWithItem(String name, Block block) {
        Registry.register(Registries.BLOCK, Identifier.of(CMT.MODID, name), block);

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
