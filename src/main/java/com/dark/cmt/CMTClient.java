package com.dark.cmt;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceRenderer;

import com.dark.cmt.registry.*;
import com.dark.cmt.registry.custom.MetalMaterialRegistry;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.FabricBakedModelManager;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class CMTClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.BLACKSMITHFURNACEBASE, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.SMITHINGANVIL, RenderLayer.getCutout());

        CMTSmithingMaterials.register();

        BlockEntityRendererFactories.register(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, BlacksmithFurnaceRenderer::new);

        HandledScreens.register(CMTScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);
    }
}
