package com.dark.cmt.item.crudeiron;

import com.dark.cmt.materials.CrudeIronMaterial;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;

public class CrudeIronShovel extends ShovelItem {
    public CrudeIronShovel() {
        super(CrudeIronMaterial.MATERIAL, new Settings().maxCount(1));
    }
}
