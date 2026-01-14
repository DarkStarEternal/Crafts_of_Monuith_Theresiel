package com.dark.cmt.init;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.init.custom.SmithingManualRecipes;
import net.minecraft.item.ItemStack;

public class CMTSmithingManualRecipes {
    public static final SmithingManualRecipe HOOK = new SmithingManualRecipe("hook_head",
            new ItemStack(CMTItems.UNFINISHEDHOOK),new ItemStack(CMTItems.HOOK) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe SHORTHANDLE = new SmithingManualRecipe("short_handle",
            new ItemStack(CMTItems.UNFINISHEDSHORTHANDLE),new ItemStack(CMTItems.SHORTHANDLE) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe PICKAXEHEAD = new SmithingManualRecipe("pickaxe_head",
            new ItemStack(CMTItems.UNFINISHEDPICKAXEHEAD),new ItemStack(CMTItems.PICKAXEHEAD) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe MEDIUMHANDLE = new SmithingManualRecipe("medium_handle",
            new ItemStack(CMTItems.MEDIUMHANDLE),new ItemStack(CMTItems.MEDIUMHANDLE) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe ZWEIHANDERBLADE = new SmithingManualRecipe("zweihander_blade",
            new ItemStack(CMTItems.ZWEIHANDERBLADE),new ItemStack(CMTItems.ZWEIHANDERBLADE) ,CMTItemTagProvider.INGOTS, true);

    public static void register() {
        SmithingManualRecipes.register(HOOK);
        SmithingManualRecipes.register(SHORTHANDLE);
    }
}
