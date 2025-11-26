package com.dark.cmt;

import com.dark.cmt.networking.CMTNetwork;
import com.dark.cmt.registry.*;
import com.dark.cmt.world.gen.CMTWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.packet.CustomPayload;

public class CMT implements ModInitializer {

    public static final String MODID = "cmt";

    @Override
    public void onInitialize() {
        CMTItems.registerModItems();
        CMTBlocks.registerModBlocks();
        CMTBlockEntities.registerModBlockEntities();
        CMTWorldGeneration.generateCMTWorldGen();
        CMTItemGroups.registerItemGroups();
        CMTScreenHandlers.registerScreenHandlers();
        CMTNetwork.register();
    }
}
