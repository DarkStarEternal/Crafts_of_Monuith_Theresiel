package com.dark.cmt.block.knowledgestones;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class KnowledgeStoneRenderer implements BlockEntityRenderer<BlacksmithFurnaceBlockEntity> {
    @Override
    public void render(BlacksmithFurnaceBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        if (world == null) return;

        ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();

        for (int i = 0; i < entity.getInventory().size(); i++) {
            ItemStack stack = entity.getInventory().get(i);
            if (stack.isEmpty()) continue;

            matrices.push();
            matrices.translate(0.25f + i * 0.5f, 1.15f, 0.5f); // position items left/right
            matrices.scale(0.5f, 0.5f, 0.5f);

            int packedLight = LightmapTextureManager.pack(
                    world.getLightLevel(LightType.BLOCK, entity.getPos()),
                    world.getLightLevel(LightType.SKY, entity.getPos())
            );

            renderer.renderItem(stack, ModelTransformationMode.FIXED, packedLight, OverlayTexture.DEFAULT_UV,
                    matrices, vertexConsumers, world, 0);

            matrices.pop();
        }
    }
}
