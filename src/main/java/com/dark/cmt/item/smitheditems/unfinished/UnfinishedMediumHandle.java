package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.init.CMTItems;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;

import java.util.List;

public class UnfinishedMediumHandle extends UnfinishedSmithedItem {

    public UnfinishedMediumHandle() {
        super(
                new Settings().maxCount(1),

                List.of("L", "L", "F", "L"),

                CMTItems.MEDIUMHANDLE.asItem(),

                "Medium Handle"
        );
    }
}
