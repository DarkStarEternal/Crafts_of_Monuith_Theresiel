package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.init.CMTItems;

import java.util.List;

public class UnfinishedSicle extends UnfinishedSmithedItem {

    public UnfinishedSicle() {
        super(
                new Settings().maxCount(1),

                List.of("B", "B", "F"),

                CMTItems.SICLEHEAD.asItem(),

                "Sicle Blade"
        );
    }
}
