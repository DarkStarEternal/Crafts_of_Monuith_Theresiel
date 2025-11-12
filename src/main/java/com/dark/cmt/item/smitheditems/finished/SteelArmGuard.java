package com.dark.cmt.item.smitheditems.finished;

import com.dark.cmt.item.smitheditems.SmithedItem;
import net.minecraft.util.Rarity;

public class SteelArmGuard extends SmithedItem {
    public SteelArmGuard() {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON));
    }
}
