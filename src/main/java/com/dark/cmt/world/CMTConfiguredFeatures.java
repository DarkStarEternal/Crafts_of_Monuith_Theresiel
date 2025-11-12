package com.dark.cmt.world;

import com.dark.cmt.CMT;
import com.dark.cmt.registry.CMTBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.rmi.registry.Registry;
import java.util.List;

public class CMTConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> SULFURIC_ORE_KEY = registerKey("sulfuric_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SULFURIC_NETHER_ORE_KEY = registerKey("sulfuric_nether_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEAD_ORE_KEY = registerKey("lead_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = registerKey("silver_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        List<OreFeatureConfig.Target> overworldSulfuricOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, CMTBlocks.SULFURICORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, CMTBlocks.SULFURICDEEPSLATEORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldSilverOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, CMTBlocks.SILVERORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, CMTBlocks.SILVERDEEPSLATEORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldLeadOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, CMTBlocks.LEADORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldTinOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, CMTBlocks.TINORE.getDefaultState()));
        List<OreFeatureConfig.Target> netherSulfuricOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables, CMTBlocks.SULFURICNETHERORE.getDefaultState()));



        register(context, SULFURIC_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSulfuricOres, 7));
        register(context, SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 8));
        register(context, LEAD_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldLeadOres, 7));
        register(context, TIN_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTinOres, 10));
        register(context, SULFURIC_NETHER_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherSulfuricOres, 10));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(CMT.MODID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

}
