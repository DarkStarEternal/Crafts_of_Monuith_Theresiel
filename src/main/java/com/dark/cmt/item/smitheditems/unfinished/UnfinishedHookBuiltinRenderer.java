package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.CMT;
import com.dark.cmt.registry.CMTSpriteAtlases;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.joml.Quaterniond;
import org.joml.Quaternionf;

public class UnfinishedHookBuiltinRenderer implements BuiltinItemRenderer {

    @Override
    public void render(ItemStack stack,
                       MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       int overlay) {


        // ---- Read material from NBT ----
        String material = "default";
        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtCompound nbt = stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt();
            if (nbt == null) {System.out.println("nbt is null");}
            if (nbt != null && nbt.contains("Material", NbtElement.STRING_TYPE)) {
                material = nbt.getString("Material");
            }
        }

        // ---- Resolve sprite ----
        Identifier spriteId = Identifier.of(
                CMT.MODID,
                "textures/item/smithing/rounded/hook/head/" + material + "_unfinished.png"
        );

        MinecraftClient.getInstance().getTextureManager().bindTexture(spriteId);

        matrices.push();

        boolean isHandRendering = false;
        boolean isGroundRendering = false;

        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null) {
            ItemStack main = client.player.getMainHandStack();
            ItemStack off = client.player.getOffHandStack();

            // Only items actually in the player's hands are "hand rendering"
            if (stack == main || stack == off) {
                isHandRendering = true;
            } else {
                isGroundRendering = true;
            }
        } else {
            // fallback if no player exists
            isGroundRendering = true;
        }



        // ---- Apply transforms based on context ----
        if (isHandRendering) {
            // Hand: rotate and scale
            matrices.translate(0.5f, 1f, 0.5f);
            matrices.scale(0.75f, 0.75f, 0.75f);

            matrices.multiply(new Quaternionf().rotateLocalX(0f));
            matrices.multiply(new Quaternionf().rotateLocalY(30f));
        } else if (isGroundRendering) {
            // Ground/dropped item: scale smaller
            matrices.translate(0.5f, 0.25f, 0.5f);
            matrices.scale(0.5f, 0.5f, 0.5f);
            matrices.multiply(new Quaternionf().rotateLocalX(190f));
        }

        // ---- Vertex consumer ----
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(spriteId));
        MatrixStack.Entry entry = matrices.peek();

        float z = 0.0f;

        // ---- Quad ----
        putVertex(consumer, entry, -0.5f, -0.5f, z, 0f, 1f, light, overlay);
        putVertex(consumer, entry,  0.5f, -0.5f, z, 1f, 1f, light, overlay);
        putVertex(consumer, entry,  0.5f,  0.5f, z, 1f, 0f, light, overlay);
        putVertex(consumer, entry, -0.5f,  0.5f, z, 0f, 0f, light, overlay);

        matrices.pop();
    }

    private static void putVertex(VertexConsumer consumer,
                                  MatrixStack.Entry entry,
                                  float x, float y, float z,
                                  float u, float v,
                                  int light, int overlay) {

        consumer.vertex(entry.getPositionMatrix(), x, y, z)
                .color(1f, 1f, 1f, 1f)
                .texture(u, v)
                .overlay(overlay)
                .light(light)
                .normal(entry, 0f, 0f, 1f);
    }

}
