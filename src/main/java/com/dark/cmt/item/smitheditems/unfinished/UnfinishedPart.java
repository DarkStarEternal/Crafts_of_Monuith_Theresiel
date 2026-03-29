package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.CMT;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedPart;
import net.minecraft.util.Identifier;

public class UnfinishedPart extends UnfinishedSmithedPart {

    public UnfinishedPart(String path, String itemType) {
        super(
                new Settings().maxCount(1),

                path,

                Identifier.of(CMT.MODID, "part"),

                itemType
        );
    }
}
