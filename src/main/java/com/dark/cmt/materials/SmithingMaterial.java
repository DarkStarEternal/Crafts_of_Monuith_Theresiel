package com.dark.cmt.materials;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmithingMaterial {
    public final TagKey<Item> materialItems;

    public final String materialIdentifier;

    public float glowHeat;
    public float meltHeat;

    public SmithingMaterial(TagKey<Item> materialItems, String materialIdentifier, float glowHeat, float meltHeat) {
        this.materialItems = materialItems;
        this.materialIdentifier = materialIdentifier;

        this.glowHeat = glowHeat;
        this.meltHeat = meltHeat;
    }

    public TagKey<Item> getMaterialItems() {
        return this.materialItems;
    }

    public String getMaterialIdentifier() {
        return this.materialIdentifier;
    }
}
