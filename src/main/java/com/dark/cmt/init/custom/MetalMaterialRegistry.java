package com.dark.cmt.init.custom;

import com.dark.cmt.materials.SmithingMaterial;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
}
