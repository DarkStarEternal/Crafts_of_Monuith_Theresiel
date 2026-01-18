package com.dark.cmt.item.gemstones;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class GemstoneItem extends Item {

    List<GemstoneEffect> effects = new ArrayList<>();

    public GemstoneItem(Settings settings, GemstoneEffect effect) {
        super(settings);
        this.effects.add(effect);
    }
}
