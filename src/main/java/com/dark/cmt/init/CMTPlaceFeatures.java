package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.world.gen.CMTOrePlacement;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class CMTPlaceFeatures {

    public static final RegistryKey<PlacedFeature> SULFURIC_ORE_PLACED_KEY = registerKey("sulfuric_ore_placed");
    public static final RegistryKey<PlacedFeature> SULFURIC_NETHER_ORE_PLACED_KEY = registerKey("sulfuric_nether_ore_placed");
    public static final RegistryKey<PlacedFeature> LEAD_ORE_PLACED_KEY = registerKey("lead_ore_placed");
    public static final RegistryKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final RegistryKey<PlacedFeature> SILVER_ORE_PLACED_KEY = registerKey("silver_ore_placed");
    public static final RegistryKey<PlacedFeature> PHOSPHORIC_STONE_PLACED_KEY = registerKey("phosphoric_stone_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context,
                SULFURIC_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(CMTConfiguredFeatures.SULFURIC_ORE_KEY),
                CMTOrePlacement.modifiersWithCount(12,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-154),
                                YOffset.fixed(312))));
        register(context,
                PHOSPHORIC_STONE_PLACED_KEY,
                configuredFeatures.getOrThrow(CMTConfiguredFeatures.PHOSPHORIC_STONE_KEY),
                CMTOrePlacement.modifiersWithCount(22,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-154),
                                YOffset.fixed(312))));
        register(context,
                SULFURIC_NETHER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(CMTConfiguredFeatures.SULFURIC_NETHER_ORE_KEY),
                CMTOrePlacement.modifiersWithCount(22,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-174),
                                YOffset.fixed(532))));
        register(context,
                LEAD_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(CMTConfiguredFeatures.LEAD_ORE_KEY),
                CMTOrePlacement.modifiersWithCount(7,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-124),
                                YOffset.fixed(112))));
        register(context,
                TIN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(CMTConfiguredFeatures.TIN_ORE_KEY),
                CMTOrePlacement.modifiersWithCount(9,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-64),
                                YOffset.fixed(112))));
        register(context,
                SILVER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(CMTConfiguredFeatures.SILVER_ORE_KEY),
                CMTOrePlacement.modifiersWithCount(12,
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-154),
                                YOffset.fixed(312))));

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(CMT.MODID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
