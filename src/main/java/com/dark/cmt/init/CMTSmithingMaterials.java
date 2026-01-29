package com.dark.cmt.init;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.init.custom.MetalMaterialRegistry;

public class CMTSmithingMaterials {

    public static final SmithingMaterial LEAD = new SmithingMaterial(
            CMTItemTagProvider.LEAD_MATERIAL,
            "lead",
            151f,
            327f
    );
    public static final SmithingMaterial DWARFGOLD = new SmithingMaterial(
            CMTItemTagProvider.DWARF_GOLD_MATERIAL,
            "dwarfgold",
            1203f,
            2725f
    );
    public static final SmithingMaterial STEEL = new SmithingMaterial(
            CMTItemTagProvider.STEEL_MATERIAL,
            "steel",
            571f,
            1527f
    );
    public static final SmithingMaterial BRONZE = new SmithingMaterial(
            CMTItemTagProvider.BRONZE_MATERIAL,
            "bronze",
            417f,
            827f
    );
    public static final SmithingMaterial SILVER = new SmithingMaterial(
            CMTItemTagProvider.SILVER_MATERIAL,
            "silver",
            351f,
            927f
    );
    public static final SmithingMaterial TIN = new SmithingMaterial(
            CMTItemTagProvider.TIN_MATERIAL,
            "tin",
            127f,
            232f
    );
    public static final SmithingMaterial NETHERITE = new SmithingMaterial(
            CMTItemTagProvider.NETHERITE_MATERIAL,
            "netherite",
            2951f,
            5327f
    );
    public static final SmithingMaterial GOLD = new SmithingMaterial(
            CMTItemTagProvider.GOLD_MATERIAL,
            "gold",
            793f,
            1064f
    );
    public static final SmithingMaterial IRON = new SmithingMaterial(
            CMTItemTagProvider.IRON_MATERIAL,
            "iron",
            716f,
            1538f
    );
    public static final SmithingMaterial COPPER = new SmithingMaterial(
            CMTItemTagProvider.COPPER_MATERIAL,
            "copper",
            451f,
            1085f
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