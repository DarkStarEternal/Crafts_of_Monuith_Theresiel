package com.dark.cmt.item.smitheditems.finished;

import com.dark.cmt.item.smitheditems.SmithedItem;
import net.minecraft.util.Rarity;

public class PickaxeHead extends SmithedItem {
    public PickaxeHead() {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON));
    }
}
