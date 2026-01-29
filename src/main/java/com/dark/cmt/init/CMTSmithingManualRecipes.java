package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.init.custom.SmithingManualRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class CMTSmithingManualRecipes {
    public static final SmithingManualRecipe SICLEHEAD = new SmithingManualRecipe("sicle_head",
            new ItemStack(CMTItems.UNFINISHEDSICLEHEAD),new ItemStack(CMTItems.SICLEHEAD) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe HOEHEAD = new SmithingManualRecipe("hoe_handle",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);

    public static final SmithingManualRecipe SHORTHANDLE = new SmithingManualRecipe("short_handle",
            new ItemStack(CMTItems.UNFINISHEDSHORTHANDLE),new ItemStack(CMTItems.SHORTHANDLE) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe MEDIUMHANDLE = new SmithingManualRecipe("medium_handle",
            new ItemStack(CMTItems.UNFINISHEDMEDIUMHANDLE),new ItemStack(CMTItems.MEDIUMHANDLE) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe LONGHANDLE = new SmithingManualRecipe("long_handle",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);

    public static final SmithingManualRecipe PICKAXEHEAD = new SmithingManualRecipe("pickaxe_head",
            new ItemStack(CMTItems.UNFINISHEDPICKAXEHEAD),new ItemStack(CMTItems.PICKAXEHEAD) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe SHOVELHEAD = new SmithingManualRecipe("shovel_head",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe SPADEHEAD = new SmithingManualRecipe("shovel_head",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);

    public static final SmithingManualRecipe DICTATORSCROWN = new SmithingManualRecipe("dictators_crown",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.DWARF_GOLD_INGOTS, true);

    public static final SmithingManualRecipe AXEBLADE = new SmithingManualRecipe("axe_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe BATTLEAXEBLADE = new SmithingManualRecipe("battle_axe_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe HELLBARDEBLADE = new SmithingManualRecipe("hellbarde_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe HATCHEDBLADE = new SmithingManualRecipe("hatchet_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);

    public static final SmithingManualRecipe ZWEIHANDERBLADE = new SmithingManualRecipe("zweihander_blade",
            new ItemStack(CMTItems.UNFINISHEDZWEIHANDERBLADE),new ItemStack(CMTItems.ZWEIHANDERBLADE) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe GLADIUSBLADE = new SmithingManualRecipe("gladius_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe EINHANDERBLADE = new SmithingManualRecipe("einhander_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe SWORDBLADE = new SmithingManualRecipe("sword_blade",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);

    public static final SmithingManualRecipe SHOULDERPAD = new SmithingManualRecipe("shoulderpad",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe CHESTPLATINGPART = new SmithingManualRecipe("chestplating_part",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.BLOCKS, true);
    public static final SmithingManualRecipe BACKPLATE = new SmithingManualRecipe("backplate",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.BLOCKS, true);
    public static final SmithingManualRecipe ARMPLATE = new SmithingManualRecipe("armplate",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe GLOVE = new SmithingManualRecipe("glove",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe HELMETCASE = new SmithingManualRecipe("helmet_case",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.BLOCKS, true);
    public static final SmithingManualRecipe HELMETVISOR = new SmithingManualRecipe("helmet_vison",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe LEGPLATE = new SmithingManualRecipe("legplate",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe KNEEPLATE = new SmithingManualRecipe("kneeplate",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.INGOTS, true);
    public static final SmithingManualRecipe BOOT = new SmithingManualRecipe("boot",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.BLOCKS, true);

    public static final SmithingManualRecipe AMBERHEART = new SmithingManualRecipe("amber_heart",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.AMBER_HEART_BASE, false);

    public static final SmithingManualRecipe DECORATIVEELEMENT = new SmithingManualRecipe("decorative_element",
            new ItemStack(Items.AIR),new ItemStack(Items.AIR) ,CMTItemTagProvider.NUGGETS, true);

    public static void register() {
        SmithingManualRecipes.register(SICLEHEAD);
        SmithingManualRecipes.register(HOEHEAD);

        SmithingManualRecipes.register(PICKAXEHEAD);
        SmithingManualRecipes.register(SHOVELHEAD);
        SmithingManualRecipes.register(SPADEHEAD);

        SmithingManualRecipes.register(ZWEIHANDERBLADE);
        SmithingManualRecipes.register(GLADIUSBLADE);
        SmithingManualRecipes.register(SWORDBLADE);
        SmithingManualRecipes.register(EINHANDERBLADE);

        SmithingManualRecipes.register(AXEBLADE);
        SmithingManualRecipes.register(BATTLEAXEBLADE);
        SmithingManualRecipes.register(HELLBARDEBLADE);
        SmithingManualRecipes.register(HATCHEDBLADE);

        SmithingManualRecipes.register(LONGHANDLE);
        SmithingManualRecipes.register(MEDIUMHANDLE);
        SmithingManualRecipes.register(SHORTHANDLE);

        SmithingManualRecipes.register(SHOULDERPAD);
        SmithingManualRecipes.register(CHESTPLATINGPART);
        SmithingManualRecipes.register(BACKPLATE);
        SmithingManualRecipes.register(GLOVE);
        SmithingManualRecipes.register(ARMPLATE);
        SmithingManualRecipes.register(HELMETCASE);
        SmithingManualRecipes.register(HELMETVISOR);
        SmithingManualRecipes.register(LEGPLATE);
        SmithingManualRecipes.register(KNEEPLATE);
        SmithingManualRecipes.register(BOOT);

        SmithingManualRecipes.register(DECORATIVEELEMENT);

        SmithingManualRecipes.register(DICTATORSCROWN);
    }
}