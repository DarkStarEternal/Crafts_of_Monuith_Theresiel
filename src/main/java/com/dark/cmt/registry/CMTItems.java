package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.item.SmithingManual;
import com.dark.cmt.item.bronze.BronzeIngot;
import com.dark.cmt.item.crudeiron.*;
import com.dark.cmt.item.lead.LeadIngot;
import com.dark.cmt.item.silver.RawSilver;
import com.dark.cmt.item.silver.SilverIngot;
import com.dark.cmt.item.smitheditems.finished.LeadHook;
import com.dark.cmt.item.smitheditems.finished.SteelArmGuard;
import com.dark.cmt.item.smitheditems.finished.SteelSwordBlade;

import com.dark.cmt.item.smitheditems.unfinished.UnfinishedLeadHook;
import com.dark.cmt.item.smitheditems.unfinished.UnfinishedSteelArmGuard;
import com.dark.cmt.item.smitheditems.unfinished.UnfinishedSteelSwordBlade;
import com.dark.cmt.item.steel.SteelIngot;
import com.dark.cmt.item.tin.TinIngot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTItems {
    public static final Item CRUDEIRONINGOT = registerItem("crude_iron_ingot", new CrudeIronIngot());
    public static final Item CRUDEIRONPICKAXE = registerItem("crude_iron_pickaxe", new CrudeIronPickaxe());
    public static final Item CRUDEIRONAXE = registerItem("crude_iron_axe", new CrudeIronAxe());
    public static final Item CRUDEIRONSWORD = registerItem("crude_iron_sword", new CrudeIronSword());
    public static final Item CRUDEIRONSHOVEL = registerItem("crude_iron_shovel", new CrudeIronShovel());
    public static final Item CRUDEIRONHOE = registerItem("crude_iron_hoe", new CrudeIronHoe());

    public static final Item SULFUR = registerItem("sulfur", new Sulfur());
    public static final Item SULFURICCOMPOUND = registerItem("sulfuric_compound", new SulfuricCompound());

    public static final Item LEADINGOT = registerItem("lead_ingot", new LeadIngot());

    public static final Item TININGOT = registerItem("tin_ingot", new TinIngot());

    public static final Item BRONZEINGOT = registerItem("bronze_ingot", new BronzeIngot());

    public static final Item STEELINGOT = registerItem("steel_ingot", new SteelIngot());

    public static final Item SILVERINGOT = registerItem("silver_ingot", new SilverIngot());
    public static final Item RAWSILVER = registerItem("raw_silver", new RawSilver());

    public static final Item STEELSWORDBLADE = registerItem("steel_sword_blade", new SteelSwordBlade());
    public static final Item UNFINISHEDSTEELSWORDBLADE = registerItem("unfinished_steel_sword_blade", new UnfinishedSteelSwordBlade());

    public static final Item STEELARMGUARD = registerItem("steel_armguard", new SteelArmGuard());
    public static final Item UNFINISHEDSTEELARMGUARD = registerItem("unfinished_steel_armguard", new UnfinishedSteelArmGuard());

    public static final Item LEADHOOK = registerItem("lead_hook", new LeadHook());
    public static final Item UNFINISHEDLEADHOOK = registerItem("unfinished_lead_hook", new UnfinishedLeadHook());

    public static final Item SMITHINGMANUAL = registerItem("smithing_manual", new SmithingManual());

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CMT.MODID, name), item);
    }

    public static void registerModItems() {

    }
}
