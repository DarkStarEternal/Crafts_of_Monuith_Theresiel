package com.dark.cmt.item.smitheditems;

import net.minecraft.item.Item;

import java.util.List;

public class UnfinishedSmithedItem extends Item {

    public List<String> commands;
    public List<String> completedCommands;
    public int currentCommandIndex;

    public UnfinishedSmithedItem(Settings settings, List givenCommands) {
        super(settings);
        this.commands = givenCommands;
    }

    public void validateCommand(String inputCommand) {
        if (this.commands.get(this.currentCommandIndex).equals(inputCommand)) {
            this.completedCommands.add(this.commands.get(this.currentCommandIndex));
            this.currentCommandIndex++;
        }
    }
}
