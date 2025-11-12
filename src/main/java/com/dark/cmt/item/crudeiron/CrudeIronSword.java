package com.dark.cmt.item.crudeiron;

import com.dark.cmt.materials.CrudeIronMaterial;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;

public class CrudeIronSword extends SwordItem {
    public CrudeIronSword() {
        super(CrudeIronMaterial.MATERIAL, new Settings().maxCount(1));
    }
}
