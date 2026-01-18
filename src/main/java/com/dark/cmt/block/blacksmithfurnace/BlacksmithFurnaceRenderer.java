package com.dark.cmt.block.blacksmithfurnace;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class BlacksmithFurnaceRenderer implements BlockEntityRenderer<BlacksmithFurnaceBlockEntity> {
    public BlacksmithFurnaceRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(BlacksmithFurnaceBlockEntity entity, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        World world = entity.getWorld();
        if (world == null) return;

        ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();

        for (int i = 0; i < entity.getInventory().size(); i++) {
            ItemStack stack = entity.getItems().get(i);

            matrices.push();
            matrices.translate(0f + i * 0.5f, 1.15f, 0f); // position items left/right
            matrices.scale(0.5f, 0.5f, 0.5f);

            renderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                    entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

}