package com.dark.cmt.init;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.init.custom.MetalMaterialRegistry;

public class CMTSmithingMaterials {

    public static final SmithingMaterial LEAD = new SmithingMaterial(
            CMTItemTagProvider.LEAD_INGOTS,
            "lead"
    );
    public static final SmithingMaterial STEEL = new SmithingMaterial(
            CMTItemTagProvider.STEEL_INGOTS,
            "steel"
    );
    public static final SmithingMaterial BRONZE = new SmithingMaterial(
            CMTItemTagProvider.BRONZE_INGOTS,
            "bronze"
    );
    public static final SmithingMaterial SILVER = new SmithingMaterial(
            CMTItemTagProvider.SILVER_INGOTS,
            "silver"
    );
    public static final SmithingMaterial TIN = new SmithingMaterial(
            CMTItemTagProvider.TIN_INGOTS,
            "tin"
    );
    public static final SmithingMaterial NETHERITE = new SmithingMaterial(
            CMTItemTagProvider.NETHERITE_INGOTS,
            "netherite"
    );
    public static final SmithingMaterial GOLD = new SmithingMaterial(
            CMTItemTagProvider.GOLD_INGOTS,
            "gold"
    );
    public static final SmithingMaterial IRON = new SmithingMaterial(
            CMTItemTagProvider.IRON_INGOTS,
            "iron"
    );
    public static final SmithingMaterial COPPER = new SmithingMaterial(
            CMTItemTagProvider.COPPER_INGOTS,
            "copper"
    );

    public static void register() {
        MetalMaterialRegistry.register(STEEL);
        MetalMaterialRegistry.register(LEAD);
        MetalMaterialRegistry.register(BRONZE);
        MetalMaterialRegistry.register(SILVER);
        MetalMaterialRegistry.register(NETHERITE);
        MetalMaterialRegistry.register(GOLD);
        MetalMaterialRegistry.register(IRON);
        MetalMaterialRegistry.register(COPPER);
    }
}