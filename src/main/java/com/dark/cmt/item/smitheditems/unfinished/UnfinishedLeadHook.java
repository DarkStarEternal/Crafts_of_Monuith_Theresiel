package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.registry.CMTItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.ArrayList;

public class UnfinishedLeadHook extends UnfinishedSmithedItem {

    public UnfinishedLeadHook() {
        super(new Settings().maxCount(1), new ArrayList<>(), CMTItems.LEADHOOK.asItem());
        this.addToCommands("B");

        this.createCompletedList();
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        System.out.println(this.completedCommands);
        System.out.println(this.commands);

        this.temperature = 200f;
        return ActionResult.SUCCESS_NO_ITEM_USED;
    }
}
