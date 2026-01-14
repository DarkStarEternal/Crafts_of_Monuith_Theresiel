package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.CMT;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;

public class UnfinishedShortHandleBuiltinRenderer implements BuiltinItemRenderer {

    // Thickness slices for depth
    private static final int DEPTH_SLICES = 4;

    @Override
    public void render(ItemStack stack, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        // --- Read material from NBT ---
        String material = "default";
        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtCompound nbt = stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt();
            if (nbt != null && nbt.contains("Material", NbtElement.STRING_TYPE)) {
                material = nbt.getString("Material");
            }
        }

        // --- Resolve texture ---
        Identifier texture = Identifier.of(CMT.MODID,
                "textures/item/smithing/handle/short/" + material + "_unfinished.png");

        MinecraftClient.getInstance().getTextureManager().bindTexture(texture);

        matrices.push();

        // --- Determine render context ---
        RenderContextType type = detectRenderContext(stack);

        applyTransforms(type, matrices);

        // --- Vertex consumer ---
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(texture));
        MatrixStack.Entry entry = matrices.peek();

        // --- Render quads with depth ---
        float zStep = 0.005f; // depth spacing
        for (int i = 0; i < DEPTH_SLICES; i++) {
            float z = i * zStep;
            renderQuad(consumer, entry, -0.5f, -0.5f, 0.5f, 0.5f, z, light, overlay);
        }

        matrices.pop();
    }

    private void renderQuad(VertexConsumer consumer, MatrixStack.Entry entry,
                            float x0, float y0, float x1, float y1,
                            float z, int light, int overlay) {
        consumer.vertex(entry.getPositionMatrix(), x0, y0, z)
                .color(1f, 1f, 1f, 1f)
                .texture(0f, 1f)
                .overlay(overlay)
                .light(light)
                .normal(entry, 0f, 0f, 1f);

        consumer.vertex(entry.getPositionMatrix(), x1, y0, z)
                .color(1f, 1f, 1f, 1f)
                .texture(1f, 1f)
                .overlay(overlay)
                .light(light)
                .normal(entry, 0f, 0f, 1f);

        consumer.vertex(entry.getPositionMatrix(), x1, y1, z)
                .color(1f, 1f, 1f, 1f)
                .texture(1f, 0f)
                .overlay(overlay)
                .light(light)
                .normal(entry, 0f, 0f, 1f);

        consumer.vertex(entry.getPositionMatrix(), x0, y1, z)
                .color(1f, 1f, 1f, 1f)
                .texture(0f, 0f)
                .overlay(overlay)
                .light(light)
                .normal(entry, 0f, 0f, 1f);
    }

    private void applyTransforms(RenderContextType type, MatrixStack matrices) {
        switch (type) {
            case GUI:
                matrices.translate(0.5f, 0.5f, 0f);
                matrices.scale(1f, 1f, 1f);
                matrices.multiply(new Quaternionf().rotateLocalX(-30f * (float)Math.PI/180f));
                matrices.multiply(new Quaternionf().rotateLocalY(45f * (float)Math.PI/180f));
                break;
            case HAND:
                matrices.translate(0.5f, 1.0f, 0.5f);
                matrices.scale(0.75f, 0.75f, 0.75f);
                matrices.multiply(new Quaternionf().rotateLocalX(0f));
                matrices.multiply(new Quaternionf().rotateLocalY(30f * (float)Math.PI/180f));
                break;
            case GROUND:
                matrices.translate(0.5f, 0.25f, 0.5f);
                matrices.scale(0.5f, 0.5f, 0.5f);
                matrices.multiply(new Quaternionf().rotateLocalX(190f * (float)Math.PI/180f));
                break;
        }
    }

    private RenderContextType detectRenderContext(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null) {
            ItemStack main = client.player.getMainHandStack();
            ItemStack off = client.player.getOffHandStack();
            if (stack == main || stack == off) return RenderContextType.HAND;
        }

        return RenderContextType.GUI;
    }

    private enum RenderContextType { GUI, HAND, GROUND }
}
