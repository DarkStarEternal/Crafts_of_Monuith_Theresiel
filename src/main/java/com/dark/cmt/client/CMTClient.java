package com.dark.cmt.client;

import com.dark.cmt.CMT;
import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceRenderer;
import com.dark.cmt.registry.CMTBlockEntities;
import com.dark.cmt.registry.CMTBlocks;
import com.dark.cmt.registry.CMTScreenHandlers;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CMTClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.of(CMT.MODID, "cmtingotoverride"),
                FabricLoader.getInstance().getModContainer(CMT.MODID).orElseThrow(),
                Text.literal("Custom Ingot Models and textures to blend them and the according Tools into CMT"),
                ResourcePackActivationType.DEFAULT_ENABLED
        );
        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.BLACKSMITHFURNACEBASE, RenderLayer.getSolid());
        BlockEntityRendererFactories.register(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, BlacksmithFurnaceRenderer::new);


        HandledScreens.register(CMTScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);
    }
}
