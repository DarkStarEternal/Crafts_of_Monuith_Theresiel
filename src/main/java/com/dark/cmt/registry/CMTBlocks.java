package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.item.crudeiron.*;
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

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CMT.MODID, name), item);
    }

    public static void registerModItems() {

    }
}
