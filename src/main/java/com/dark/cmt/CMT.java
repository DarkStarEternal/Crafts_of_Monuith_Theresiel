package com.dark.cmt;

import com.dark.cmt.registry.*;
import com.dark.cmt.world.gen.CMTWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;


public class CMT implements ModInitializer {

    public static final String MODID = "cmt";

    public static final Identifier UPDATE_GUI_SLOT = Identifier.of(MODID, "update_gui_slot");


    @Override
    public void onInitialize() {
        CMTItems.registerModItems();
        CMTBlocks.registerModBlocks();
        CMTBlockEntities.registerModBlockEntities();
        CMTWorldGeneration.generateCMTWorldGen();
        CMTItemGroups.registerItemGroups();
        CMTScreenHandlers.registerScreenHandlers();
    }
}
