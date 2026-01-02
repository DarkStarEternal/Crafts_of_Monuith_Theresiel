package com.dark.cmt;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceRenderer;

import com.dark.cmt.item.smitheditems.unfinished.UnfinishedHookBuiltinRenderer;
import com.dark.cmt.registry.*;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class CMTClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CMTSmithingMaterials.register();

        BuiltinItemRendererRegistry.INSTANCE.register(
                CMTItems.UNFINISHEDHOOK,
                (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                    new UnfinishedHookBuiltinRenderer().render(stack, matrices, vertexConsumers, light, overlay);
                }
        );

        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.BLACKSMITHFURNACEBASE, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.SMITHINGANVIL, RenderLayer.getCutout());

        BlockEntityRendererFactories.register(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, BlacksmithFurnaceRenderer::new);

        HandledScreens.register(CMTScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);
    }
}
