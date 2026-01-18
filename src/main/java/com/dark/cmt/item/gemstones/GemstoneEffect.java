package com.dark.cmt.item.gemstones;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class GemstoneEffect {

    Identifier name;

    public GemstoneEffect(Identifier id) {
        this.name = id;
    }

    public void applyEffect(LivingEntity wielder, ItemStack stack) {
    }
}
