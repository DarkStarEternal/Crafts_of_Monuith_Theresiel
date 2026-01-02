package com.dark.cmt.registry;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

import java.util.List;

public class CMTSmithingManualRecipes {

    public static final SmithingManualRecipe HOOK = new SmithingManualRecipe("hok_head",
            new ItemStack(CMTItems.UNFINISHEDHOOK),new ItemStack(CMTItems.HOOK) ,CMTItemTagProvider.INGOTS);
}
