package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.item.BasicManual;
import com.dark.cmt.item.CreativeManual;
import com.dark.cmt.item.HephaistonManual;
import com.dark.cmt.item.gemstones.HeartOfTheMountain;
import com.dark.cmt.item.smitheditems.finished.*;

import com.dark.cmt.item.smitheditems.unfinished.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTItems {

    public static final Item PHOSPHORUS  = registerItem("phosphorus", new Item(new Item.Settings()));

    public static final Item SULFUR = registerItem("sulfur", new Item(new Item.Settings()));
    public static final Item SULFURICCOMPOUND = registerItem("sulfuric_compound", new Item(new Item.Settings()));

    public static final Item LEADINGOT = registerItem("lead_ingot", new Item(new Item.Settings()));
    public static final Item RAWLEAD = registerItem("raw_lead", new Item(new Item.Settings()));
    public static final Item LEADNUGGET = registerItem("lead_nugget", new Item(new Item.Settings()));

    public static final Item TININGOT = registerItem("tin_ingot", new Item(new Item.Settings()));
    public static final Item RAWTIN = registerItem("raw_tin", new Item(new Item.Settings()));
    public static final Item TINNUGGET = registerItem("tin_nugget", new Item(new Item.Settings()));

    public static final Item BRONZEINGOT = registerItem("bronze_ingot", new Item(new Item.Settings()));
    public static final Item BRONZENUGGET = registerItem("bronze_nugget", new Item(new Item.Settings()));

    public static final Item STEELINGOT = registerItem("steel_ingot", new Item(new Item.Settings()));
    public static final Item STEELNUGGET = registerItem("steel_nugget", new Item(new Item.Settings()));

    public static final Item SILVERINGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item RAWSILVER = registerItem("raw_silver", new Item(new Item.Settings()));
    public static final Item SILVERNUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));

    public static final Item DWARFGOLDINGOT = registerItem("dwarf_gold_ingot", new Item(new Item.Settings()));
    public static final Item DWARFGOLDNUGGET = registerItem("dwarf_gold_nugget", new Item(new Item.Settings()));

    public static final Item COPPERNUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));
    public static final Item NETHERITENUGGET = registerItem("netherite_nugget", new Item(new Item.Settings()));

    public static final Item JADECRUMBLES = registerItem("jade_crumbles", new Item(new Item.Settings()));

    public static final Item HEARTOFTHEMOUNTAIN = registerItem("heart_of_the_mountain", new HeartOfTheMountain());
    public static final Item PURELAPIS = registerItem("pure_lapis_lazuli", new HeartOfTheMountain());
    public static final Item CLEARQUARTZ = registerItem("clear_quartz", new HeartOfTheMountain());
    public static final Item JADEGEMSTONE = registerItem("jade_gemstone", new HeartOfTheMountain());
    public static final Item AMBERHEART = registerItem("amber_heart", new HeartOfTheMountain());
    public static final Item PUREREDSTONE = registerItem("pure_redstone", new HeartOfTheMountain());

    public static final Item SICLEHEAD = registerItem("sicle_head", new SicleHead());
    public static final Item UNFINISHEDSICLEHEAD = registerItem("unfinished_sicle_head", new UnfinishedSicle());
    public static final Item ZWEIHANDERBLADE = registerItem("zweihanderblade", new ZweihanderBlade());
    public static final Item UNFINISHEDZWEIHANDERBLADE = registerItem("unfinished_zweihanderblade", new UnfinishedZweihanderBlade());
    public static final Item MEDIUMHANDLE = registerItem("mediumhandle", new MediumHandle());
    public static final Item UNFINISHEDMEDIUMHANDLE = registerItem("unfinished_mediumhandle", new UnfinishedMediumHandle());
    public static final Item PICKAXEHEAD = registerItem("pickaxehead", new PickaxeHead());
    public static final Item UNFINISHEDPICKAXEHEAD = registerItem("unfinished_pickaxehead", new UnfinishedPickaxeHead());
    public static final Item SHORTHANDLE = registerItem("short_handle", new ShortHandle());
    public static final Item UNFINISHEDSHORTHANDLE = registerItem("unfinished_short_handle", new UnfinishedShortHandle());

    public static final Item CREATIVEMANUAL = registerItem("creative_smithing_manual", new CreativeManual());
    public static final Item HEPHAISTONMANUAL = registerItem("hephaiston_smithing_manual", new HephaistonManual());
    public static final Item BASICMANUAL = registerItem("smithing_manual", new BasicManual());

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CMT.MODID, name), item);
    }

    public static void registerModItems() {

    }
}
