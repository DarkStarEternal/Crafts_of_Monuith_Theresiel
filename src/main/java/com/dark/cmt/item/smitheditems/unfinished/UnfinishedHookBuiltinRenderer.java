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
                System.out.println(material);
            }
        }

        // ---- Resolve sprite ----
        Identifier spriteId = Identifier.of(
                CMT.MODID,
                "textures/item/smithing/rounded/hook/head/" + material + "_unfinished.png"
        );

        // ---- Setup transforms ----
        matrices.push();

        Quaternionf xRot = new Quaternionf().rotateLocalX(0f);
        Quaternionf yRot = new Quaternionf().rotateLocalY(30f);

        MinecraftClient.getInstance().getTextureManager().bindTexture(spriteId);

        matrices.translate(0.5f, 0.75f, 0.5f);

        matrices.scale(0.75f, 0.75f, 0.75f); // adjust as needed

        matrices.multiply(xRot);
        matrices.multiply(yRot);

        MatrixStack.Entry entry = matrices.peek();

        // ---- Vertex consumer ----
        VertexConsumer consumer = vertexConsumers.getBuffer(
                RenderLayer.getEntityTranslucent(spriteId)
        );


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
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .texture(u, v)
                .overlay(overlay)
                .light(light)
                .normal(entry, 0f, 0f, 1f);
    }

}
