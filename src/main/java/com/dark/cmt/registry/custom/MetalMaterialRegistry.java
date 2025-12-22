package com.dark.cmt.registry.custom;

import com.dark.cmt.materials.SmithingMaterial;
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

    public static List<Identifier> getAllLayers() {
        List<Identifier> list = new ArrayList<Identifier>();
        for (int i = 0;
             i < MATERIALS.size();
             ) {
            list.addAll(MATERIALS.get(i).textureIdentifiers);
            i++;
        }
        return list;
    }

    public static List<Identifier> getLayersFromMaterial(String materialIdentifier) {
        for (int i = 0; i < MATERIALS.size();) {
            if (MATERIALS.get(i).getMaterialIdentifier().toLowerCase().equals(materialIdentifier.toLowerCase())) {
                return MATERIALS.get(i).textureIdentifiers;
            }
            i++;
        }
        return List.of();
    }
}
