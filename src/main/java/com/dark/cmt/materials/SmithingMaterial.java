package com.dark.cmt.materials;

import net.minecraft.client.util.SpriteIdentifier;
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

    public final List<SpriteIdentifier> textureIdentifiers = new ArrayList<>();

    public SmithingMaterial(TagKey<Item> materialItems, String materialIdentifier, List<SpriteIdentifier> identifierList) {
        this.materialItems = materialItems;
        this.materialIdentifier = materialIdentifier;
        this.textureIdentifiers.addAll(identifierList);
    }

    public TagKey<Item> getMaterialItems() {
        return this.materialItems;
    }

    public String getMaterialIdentifier() {
        return this.materialIdentifier;
    }
}
