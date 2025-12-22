package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.registry.custom.MetalMaterialRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CMTSmithingMaterials {

    public static final SmithingMaterial LEAD = new SmithingMaterial(
            CMTItemTagProvider.LEAD_INGOTS,
            "lead",
            List.of(
                    // Hook
                    Identifier.of(CMT.MODID, "item/rounded/hook/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/rounded/hook/head/lead"),

                    // Hoe
                    Identifier.of(CMT.MODID, "item/rounded/hoe/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/rounded/hoe/head/lead"),

                    // Sickle
                    Identifier.of(CMT.MODID, "item/rounded/sicle/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/rounded/sicle/head/lead"),

                    // Swords
                    Identifier.of(CMT.MODID, "item/sword/short/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/sword/short/lead"),
                    Identifier.of(CMT.MODID, "item/sword/regular/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/sword/regular/lead"),
                    Identifier.of(CMT.MODID, "item/sword/long/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/sword/long/lead"),
                    Identifier.of(CMT.MODID, "item/sword/hand/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/sword/handguard/lead"),

                    // Digging tools
                    Identifier.of(CMT.MODID, "item/digging/shovel/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/digging/shovel/head/lead"),
                    Identifier.of(CMT.MODID, "item/digging/spade/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/digging/spade/head/lead"),
                    Identifier.of(CMT.MODID, "item/digging/pickaxe/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/digging/pickaxe/head/lead"),

                    // Axes
                    Identifier.of(CMT.MODID, "item/axe/regular/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/axe/regular/head/lead"),
                    Identifier.of(CMT.MODID, "item/axe/battle/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/axe/battle/head/lead"),
                    Identifier.of(CMT.MODID, "item/axe/hellbarde/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/axe/hellbarde/head/lead"),

                    // Spears
                    Identifier.of(CMT.MODID, "item/spear/regular/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/spear/regular/head/lead")
            )
    );

    public static void register() {
        MetalMaterialRegistry.register(LEAD);
    }
}