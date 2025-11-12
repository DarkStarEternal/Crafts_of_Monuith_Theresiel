package com.dark.cmt.item.crudeiron;

import com.dark.cmt.materials.CrudeIronMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;

public class CrudeIronAxe extends AxeItem {
    public CrudeIronAxe() {
        super(CrudeIronMaterial.MATERIAL, new Settings().maxCount(1));
    }
}
