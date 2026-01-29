package com.dark.cmt;

import com.dark.cmt.entity.custom.KhyninOverlordEntity;
import com.dark.cmt.init.CMTNetwork;
import com.dark.cmt.init.*;
import com.dark.cmt.world.gen.CMTWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CMT implements ModInitializer {

    public static final String MODID = "cmt";
    public static final Logger LOGGER = LogManager.getLogger("cmt");

    @Override
    public void onInitialize() {
        CMTItems.registerModItems();
        CMTEntities.register();
        CMTDataComponents.register();
        CMTBlocks.registerModBlocks();
        CMTBlockEntities.registerModBlockEntities();
        CMTWorldGeneration.generateCMTWorldGen();
        CMTItemGroups.registerItemGroups();
        CMTScreenHandlers.registerScreenHandlers();
        CMTSmithingManualRecipes.register();
        CMTNetwork.register();
        CMTSmithingMaterials.register();
        CMTStructures.init();

        FabricDefaultAttributeRegistry.register(CMTEntities.KHYNIN_OVERLORD, KhyninOverlordEntity.createAttributes());
        LOGGER.info("Diggy diggy hole!");
    }
}
