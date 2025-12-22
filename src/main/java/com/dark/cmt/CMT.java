package com.dark.cmt;

import com.dark.cmt.registry.CMTNetwork;
import com.dark.cmt.registry.*;
import com.dark.cmt.world.gen.CMTWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CMT implements ModInitializer {

    public static final String MODID = "cmt";
    public static final Logger LOGGER = LogManager.getLogger("cmt");

    @Override
    public void onInitialize() {
        CMTItems.registerModItems();
        CMTBlocks.registerModBlocks();
        CMTBlockEntities.registerModBlockEntities();
        CMTWorldGeneration.generateCMTWorldGen();
        CMTItemGroups.registerItemGroups();
        CMTScreenHandlers.registerScreenHandlers();
        CMTNetwork.register();
        CMTSmithingMaterials.register();
        LOGGER.info("Diggy diggy hole!");
    }
}
