package com.dark.cmt.item;

import com.dark.cmt.materials.CrudeIronMaterial;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class CrudeIronPickaxe extends PickaxeItem {
    public CrudeIronPickaxe(ToolMaterial material, Settings settings) {
        super(CrudeIronMaterial, settings);
    }
}
