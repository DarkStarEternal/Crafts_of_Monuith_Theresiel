package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.registry.CMTItems;
import net.minecraft.item.ItemStack;

import java.util.*;

public class UnfinishedSteelSwordBlade extends UnfinishedSmithedItem {

    public UnfinishedSteelSwordBlade() {
        super(new Settings().maxCount(1), new ArrayList<>(), CMTItems.STEELSWORDBLADE.asItem());
        this.addToCommands("L");
        this.addToCommands("L");
        this.addToCommands("F");
        this.addToCommands("S");
    }
}
