package com.dark.cmt.item.gemstones;

import com.dark.cmt.CMT;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class HeartOfTheMountainEffect extends GemstoneEffect {

    public HeartOfTheMountainEffect() {
        super(Identifier.of(CMT.MODID, "heart_of_the_mountain"));
    }

    @Override
    public void applyEffect(LivingEntity wielder, ItemStack stack) {
        super.applyEffect(wielder, stack);
    }
}
