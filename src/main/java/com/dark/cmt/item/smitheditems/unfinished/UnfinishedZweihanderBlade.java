package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.init.CMTItems;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;

import java.util.List;

public class UnfinishedZweihanderBlade extends UnfinishedSmithedItem {

    public UnfinishedZweihanderBlade() {
        super(
                new Settings().maxCount(1),

                List.of("L", "L", "F", "B", "L", "L"),

                CMTItems.ZWEIHANDERBLADE.asItem()
        );
    }
}
