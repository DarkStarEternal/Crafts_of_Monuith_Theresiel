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
import org.joml.Quaternionf;

public class BlacksmithFurnaceRenderer implements BlockEntityRenderer<BlacksmithFurnaceBlockEntity> {
    public BlacksmithFurnaceRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(BlacksmithFurnaceBlockEntity entity, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        World world = entity.getWorld();
        if (world == null) return;

        ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack stack = entity.getItems().get(0);

        matrices.push();
        matrices.translate(1f, 1.25f, 0.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(new Quaternionf(0f, 1f, 0f, 0f));

        renderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();

        ItemStack stack2 = entity.getItems().get(1);

        matrices.push();
        matrices.translate(1f, 1.25f, 1.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(new Quaternionf(0f, 1f, 0f, 0f));

        renderer.renderItem(stack2, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

}