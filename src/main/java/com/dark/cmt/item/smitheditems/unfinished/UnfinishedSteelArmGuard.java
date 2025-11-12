package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.registry.CMTItems;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class UnfinishedSteelArmGuard extends UnfinishedSmithedItem {

    public UnfinishedSteelArmGuard() {
        super(new Settings().maxCount(1), new ArrayList<>(), CMTItems.STEELARMGUARD.asItem());
        this.addToCommands("B");
        this.addToCommands("F");
        this.addToCommands("L");
        this.addToCommands("F");
        this.addToCommands("L");
    }
}
