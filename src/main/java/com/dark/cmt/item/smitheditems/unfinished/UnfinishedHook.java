package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.init.CMTItems;

import java.util.List;

public class UnfinishedHook extends UnfinishedSmithedItem {

    public UnfinishedHook() {
        super(
                new Settings().maxCount(1),

                List.of("B"),

                CMTItems.HOOK.asItem()
        );
    }
}
