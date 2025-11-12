package com.dark.cmt.item.crudeiron;

import com.dark.cmt.materials.CrudeIronMaterial;
import net.minecraft.item.HoeItem;
import net.minecraft.item.PickaxeItem;

public class CrudeIronHoe extends HoeItem {
    public CrudeIronHoe() {
        super(CrudeIronMaterial.MATERIAL, new Settings().maxCount(1));
    }
}
