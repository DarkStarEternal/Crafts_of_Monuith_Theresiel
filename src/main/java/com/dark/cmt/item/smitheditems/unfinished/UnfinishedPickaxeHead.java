package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.init.CMTItems;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;

import java.util.List;

public class UnfinishedPickaxeHead extends UnfinishedSmithedItem {

    public UnfinishedPickaxeHead() {
        super(
                new Settings().maxCount(1),

                List.of("L", "B", "L"),

                CMTItems.PICKAXEHEAD.asItem(),

                "Pickaxe Head"
        );
    }
}
