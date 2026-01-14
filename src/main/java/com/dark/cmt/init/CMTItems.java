package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.item.BasicManual;
import com.dark.cmt.item.CreativeManual;
import com.dark.cmt.item.HephaistonManual;
import com.dark.cmt.item.bronze.BronzeIngot;
import com.dark.cmt.item.lead.LeadIngot;
import com.dark.cmt.item.lead.RawLead;
import com.dark.cmt.item.phosphorus.Phosphorus;
import com.dark.cmt.item.silver.RawSilver;
import com.dark.cmt.item.silver.SilverIngot;
import com.dark.cmt.item.smitheditems.finished.*;

import com.dark.cmt.item.smitheditems.unfinished.*;
import com.dark.cmt.item.steel.SteelIngot;
import com.dark.cmt.item.sulfur.Sulfur;
import com.dark.cmt.item.sulfur.SulfuricCompound;
import com.dark.cmt.item.tin.RawTin;
import com.dark.cmt.item.tin.TinIngot;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTItems {

    public static final Item PHOSPHORUS  = registerItem("phosphorus", new Phosphorus());

    public static final Item SULFUR = registerItem("sulfur", new Sulfur());
    public static final Item SULFURICCOMPOUND = registerItem("sulfuric_compound", new SulfuricCompound());

    public static final Item LEADINGOT = registerItem("lead_ingot", new LeadIngot());
    public static final Item RAWLEAD = registerItem("raw_lead", new RawLead());

    public static final Item TININGOT = registerItem("tin_ingot", new TinIngot());
    public static final Item RAWTIN = registerItem("raw_tin", new RawTin());

    public static final Item BRONZEINGOT = registerItem("bronze_ingot", new BronzeIngot());

    public static final Item STEELINGOT = registerItem("steel_ingot", new SteelIngot());

    public static final Item SILVERINGOT = registerItem("silver_ingot", new SilverIngot());
    public static final Item RAWSILVER = registerItem("raw_silver", new RawSilver());

    public static final Item HOOK = registerItem("hook", new Hook());
    public static final Item UNFINISHEDHOOK = registerItem("unfinished_hook", new UnfinishedHook());
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
