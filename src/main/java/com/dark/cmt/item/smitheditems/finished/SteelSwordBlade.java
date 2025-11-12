package com.dark.cmt.item.smitheditems.finished;

import com.dark.cmt.item.smitheditems.SmithedItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class SteelSwordBlade extends SmithedItem {
    public SteelSwordBlade() {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON));
    }
}
