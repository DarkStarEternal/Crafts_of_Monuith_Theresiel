package com.dark.cmt.init;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.init.custom.MetalMaterialRegistry;

public class CMTSmithingMaterials {

    public static final SmithingMaterial LEAD = new SmithingMaterial(
            CMTItemTagProvider.LEAD_MATERIAL,
            "lead"
    );
    public static final SmithingMaterial DWARFGOLD = new SmithingMaterial(
            CMTItemTagProvider.DWARF_GOLD_MATERIAL,
            "dwarfgold"
    );
    public static final SmithingMaterial STEEL = new SmithingMaterial(
            CMTItemTagProvider.STEEL_MATERIAL,
            "steel"
    );
    public static final SmithingMaterial BRONZE = new SmithingMaterial(
            CMTItemTagProvider.BRONZE_MATERIAL,
            "bronze"
    );
    public static final SmithingMaterial SILVER = new SmithingMaterial(
            CMTItemTagProvider.SILVER_MATERIAL,
            "silver"
    );
    public static final SmithingMaterial TIN = new SmithingMaterial(
            CMTItemTagProvider.TIN_MATERIAL,
            "tin"
    );
    public static final SmithingMaterial NETHERITE = new SmithingMaterial(
            CMTItemTagProvider.NETHERITE_MATERIAL,
            "netherite"
    );
    public static final SmithingMaterial GOLD = new SmithingMaterial(
            CMTItemTagProvider.GOLD_MATERIAL,
            "gold"
    );
    public static final SmithingMaterial IRON = new SmithingMaterial(
            CMTItemTagProvider.IRON_MATERIAL,
            "iron"
    );
    public static final SmithingMaterial COPPER = new SmithingMaterial(
            CMTItemTagProvider.COPPER_MATERIAL,
            "copper"
    );

    public static void register() {
        MetalMaterialRegistry.register(STEEL);
        MetalMaterialRegistry.register(LEAD);
        MetalMaterialRegistry.register(TIN);
        MetalMaterialRegistry.register(BRONZE);
        MetalMaterialRegistry.register(SILVER);
        MetalMaterialRegistry.register(NETHERITE);
        MetalMaterialRegistry.register(GOLD);
        MetalMaterialRegistry.register(IRON);
        MetalMaterialRegistry.register(COPPER);
        MetalMaterialRegistry.register(DWARFGOLD);
    }
}