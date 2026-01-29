package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.init.CMTItems;

import java.util.List;

public class UnfinishedShortHandle extends UnfinishedSmithedItem {

    public UnfinishedShortHandle() {
        super(
                new Settings().maxCount(1),

                List.of("L", "L", "F"),

                CMTItems.SHORTHANDLE.asItem(),

                "Short Handle"
        );
    }
}
