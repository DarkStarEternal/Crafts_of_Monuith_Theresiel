package com.dark.cmt.item.smitheditems;

import net.minecraft.item.Item;

public class SmithedPart extends Item {
    private String path;
    private String itemType;

    public SmithedPart(Settings settings, String path, String itemType) {
        super(settings);
        this.path = path;
        this.itemType = itemType;
    }
}
