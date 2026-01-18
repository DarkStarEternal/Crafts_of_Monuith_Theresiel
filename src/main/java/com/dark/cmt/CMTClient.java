package com.dark.cmt;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceRenderer;

import com.dark.cmt.block.pedestal.PedestalBlockEntityRenderer;
import com.dark.cmt.entity.client.KhyninOverlordModel;
import com.dark.cmt.entity.client.KhyninOverlordRenderer;
import com.dark.cmt.item.smitheditems.unfinished.UnfinishedSicleHeadBuiltinRenderer;
import com.dark.cmt.item.smitheditems.unfinished.UnfinishedShortHandleBuiltinRenderer;
import com.dark.cmt.init.*;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class CMTClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CMTSmithingMaterials.register();

        BuiltinItemRendererRegistry.INSTANCE.register(
                CMTItems.UNFINISHEDSICLEHEAD,
                (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                    new UnfinishedSicleHeadBuiltinRenderer().render(stack, matrices, vertexConsumers, light, overlay);
                }
        );
        BuiltinItemRendererRegistry.INSTANCE.register(
                CMTItems.UNFINISHEDSHORTHANDLE,
                (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                    new UnfinishedShortHandleBuiltinRenderer().render(stack, matrices, vertexConsumers, light, overlay);
                }
        );

        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.BLACKSMITHFURNACEBASE, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(CMTBlocks.SMITHINGANVIL, RenderLayer.getCutout());

        BlockEntityRendererFactories.register(CMTBlockEntities.BLACKSMITHFURNACEBLOCKENTITY, BlacksmithFurnaceRenderer::new);

        BlockEntityRendererFactories.register(CMTBlockEntities.PEDESTALBLOCKENTITY, PedestalBlockEntityRenderer::new);

        HandledScreens.register(CMTScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);

        EntityModelLayerRegistry.registerModelLayer(KhyninOverlordModel.KHYNINOVERLORD, KhyninOverlordModel::getTexturedModelData);

        EntityRendererRegistry.register(CMTEntities.KHYNIN_OVERLORD, KhyninOverlordRenderer::new);
    }
}
