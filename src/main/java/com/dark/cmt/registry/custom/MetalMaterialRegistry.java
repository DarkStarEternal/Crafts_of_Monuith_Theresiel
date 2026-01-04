package com.dark.cmt.registry.custom;

import com.dark.cmt.materials.SmithingMaterial;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MetalMaterialRegistry {
    public static final List<SmithingMaterial> MATERIALS = new ArrayList<>();

    public static void register(SmithingMaterial material) {
        MATERIALS.add(material);
    }

    public static final SmithingMaterial getMaterialFromInput(ItemStack input) {
        for (SmithingMaterial material : MATERIALS) {
            if (input.isIn(material.getMaterialItems())) {
                return material;
            }
        }
        return null;
    }

    public static List<SpriteIdentifier> getAllLayers() {
        List<SpriteIdentifier> list = new ArrayList<SpriteIdentifier>();
        for (int i = 0;
             i < MATERIALS.size();
             ) {
            list.addAll(MATERIALS.get(i).textureIdentifiers);
            i++;
        }
        return list;
    }
}
