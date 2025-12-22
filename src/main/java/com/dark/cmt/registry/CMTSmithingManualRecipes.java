package com.dark.cmt.registry;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

import java.util.List;

public class CMTSmithingManualRecipes {

    public static final SmithingManualRecipe LEADHOOK = new SmithingManualRecipe("lead_hook",
            new ItemStack(CMTItems.UNFINISHEDHOOK),new ItemStack(CMTItems.HOOK) ,CMTItemTagProvider.INGOTS);
}
