package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.registry.custom.MetalMaterialRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CMTSmithingMaterials {

    public static final SmithingMaterial LEAD = new SmithingMaterial(
            CMTItemTagProvider.LEAD_INGOTS,
            "lead",
            List.of(
                    // Hook
                    new SpriteIdentifier(Identifier.of("minecraft", "items") ,Identifier.of(CMT.MODID, "item/smithing/rounded/hook/head/lead_unfinished"))
                    /*Identifier.of(CMT.MODID, "item/smithing/rounded/hook/head/lead"),

                    // Hoe
                    Identifier.of(CMT.MODID, "item/smithing/rounded/hoe/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/rounded/hoe/head/lead"),

                    // Sickle
                    Identifier.of(CMT.MODID, "item/smithing/rounded/sicle/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/rounded/sicle/head/lead"),

                    // Swords
                    Identifier.of(CMT.MODID, "item/smithing/sword/short/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/short/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/regular/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/regular/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/long/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/long/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/hand/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/sword/handguard/lead"),

                    // Digging tools
                    Identifier.of(CMT.MODID, "item/smithing/digging/shovel/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/digging/shovel/head/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/digging/spade/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/digging/spade/head/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/digging/pickaxe/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/digging/pickaxe/head/lead"),

                    // Axes
                    Identifier.of(CMT.MODID, "item/smithing/axe/regular/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/axe/regular/head/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/axe/battle/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/axe/battle/head/lead"),
                    Identifier.of(CMT.MODID, "item/smithing/axe/hellbarde/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/axe/hellbarde/head/lead"),

                    // Spears
                    Identifier.of(CMT.MODID, "item/smithing/spear/regular/head/lead_unfinished"),
                    Identifier.of(CMT.MODID, "item/smithing/spear/regular/head/lead")*/
            )
    );

    public static void register() {
        MetalMaterialRegistry.register(LEAD);
    }
}