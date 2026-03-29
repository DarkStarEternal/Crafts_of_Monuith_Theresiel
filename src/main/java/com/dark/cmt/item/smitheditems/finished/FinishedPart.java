package com.dark.cmt.item.smitheditems.finished;

import com.dark.cmt.item.smitheditems.SmithedPart;
import net.minecraft.util.Rarity;

public class FinishedPart extends SmithedPart {
    public FinishedPart(String path, String itemType) {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON), path, itemType);
    }
}
