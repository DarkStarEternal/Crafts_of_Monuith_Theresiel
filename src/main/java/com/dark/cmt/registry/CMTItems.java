package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.item.BasicManual;
import com.dark.cmt.item.HephaistonManual;
import com.dark.cmt.item.SmithingManual;
import com.dark.cmt.item.bronze.BronzeIngot;
import com.dark.cmt.item.lead.LeadIngot;
import com.dark.cmt.item.silver.RawSilver;
import com.dark.cmt.item.silver.SilverIngot;
import com.dark.cmt.item.smitheditems.finished.Hook;

import com.dark.cmt.item.smitheditems.unfinished.UnfinishedHook;
import com.dark.cmt.item.steel.SteelIngot;
import com.dark.cmt.item.sulfur.Sulfur;
import com.dark.cmt.item.sulfur.SulfuricCompound;
import com.dark.cmt.item.tin.TinIngot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTItems {

    public static final Item SULFUR = registerItem("sulfur", new Sulfur());
    public static final Item SULFURICCOMPOUND = registerItem("sulfuric_compound", new SulfuricCompound());

    public static final Item LEADINGOT = registerItem("lead_ingot", new LeadIngot());

    public static final Item TININGOT = registerItem("tin_ingot", new TinIngot());

    public static final Item BRONZEINGOT = registerItem("bronze_ingot", new BronzeIngot());

    public static final Item STEELINGOT = registerItem("steel_ingot", new SteelIngot());

    public static final Item SILVERINGOT = registerItem("silver_ingot", new SilverIngot());
    public static final Item RAWSILVER = registerItem("raw_silver", new RawSilver());

    public static final Item HOOK = registerItem("hook", new Hook());
    public static final Item UNFINISHEDHOOK = registerItem("unfinished_hook", new UnfinishedHook());

    public static final Item HEPHAISTONMANUAL = registerItem("hephaiston_smithing_manual", new HephaistonManual());
    public static final Item BASICMANUAL = registerItem("smithing_manual", new BasicManual());

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CMT.MODID, name), item);
    }

    public static void registerModItems() {

    }
}
