package com.dark.cmt.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

public class SmithingManualRecipe {
    public String name;
    public boolean isKnowledgeStoneRecipe;
    public TagKey<Item> hasRecipe;
    public ItemStack unfinishedOutput;
    public ItemStack finalitem;

    public SmithingManualRecipe(String name1, ItemStack unfinishedOutput1, ItemStack finalItem1, TagKey<Item> hasRecipe, boolean obtainedFromKnowledgeStones) {
        this.name = name1;
        this.hasRecipe = hasRecipe;
        this.unfinishedOutput = unfinishedOutput1;
        this.finalitem = finalItem1;
        this.isKnowledgeStoneRecipe = obtainedFromKnowledgeStones;
    }

    public String getName() {
        return name;
    }

    public ItemStack getFinalitem() {
        return finalitem;
    }

    public TagKey<Item> getInput() {
        return hasRecipe;
    }

    public ItemStack getUnfinishedOutput() {
        return unfinishedOutput;
    }
}
