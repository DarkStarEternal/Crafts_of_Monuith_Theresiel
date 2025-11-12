package com.dark.cmt.registry;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.item.ItemStack;

public class CMTSmithingManualRecipes {
    public static final SmithingManualRecipe LEADHOOK = new SmithingManualRecipe("lead_hook",
            CMTItemTagProvider.LEAD_INGOTS, new ItemStack(CMTItems.UNFINISHEDLEADHOOK), new ItemStack(CMTItems.LEADHOOK));
    public static final SmithingManualRecipe STEELBLADE = new SmithingManualRecipe("steel_blade",
            CMTItemTagProvider.STEEL_INGOTS, new ItemStack(CMTItems.UNFINISHEDSTEELSWORDBLADE), new ItemStack(CMTItems.STEELSWORDBLADE));
    public static final SmithingManualRecipe STEELARMGUARD = new SmithingManualRecipe("steel_armguard",
            CMTItemTagProvider.STEEL_INGOTS, new ItemStack(CMTItems.UNFINISHEDSTEELARMGUARD), new ItemStack(CMTItems.STEELARMGUARD));
}
