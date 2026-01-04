package com.dark.cmt.client;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceRenderer;

import com.dark.cmt.registry.*;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class CMTClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CMTSmithingMaterials.register();

        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.BLACKSMITHFURNACEBASE, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.SMITHINGANVIL, RenderLayer.getCutout());

        BlockEntityRendererFactories.register(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, BlacksmithFurnaceRenderer::new);

        HandledScreens.register(CMTScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);
    }
}
