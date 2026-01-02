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

public class UnfinishedHookBuiltinRenderer implements BuiltinItemRenderer {

    @Override
    public void render(ItemStack stack,
                       MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       int overlay) {


        // ---- Read material from NBT ----
        String material = "lead";
        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtCompound nbt = stack.get(DataComponentTypes.CUSTOM_DATA).getNbt();
            if (nbt != null && nbt.contains("material", NbtElement.STRING_TYPE)) {
                material = nbt.getString("material");
            }
        }

        // ---- Resolve sprite ----
        Identifier spriteId = Identifier.of(
                CMT.MODID,
                "rounded/hook/head/" + material + "_unfinished"
        );

        System.out.println(
                "Looking for sprite at: textures/item/smithing/" + spriteId.getPath() + ".png"
        );
        Sprite sprite = MinecraftClient.getInstance()
                .getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)
                .apply(spriteId);
        if (sprite == null) {
            sprite = MinecraftClient.getInstance()
                    .getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)
                    .apply(Identifier.of(
                            CMT.MODID,
                            "fallback"
                    )

            );

        }
        System.out.println(sprite);

        if (sprite == null) {
            return; // nothing to render
        }

        // ---- Setup transforms ----
        matrices.push();

        matrices.translate(0.5f, 0.5f, 0.0f);
        matrices.scale(1.0f, -1.0f, 1.0f);

        MatrixStack.Entry entry = matrices.peek();

        // ---- Vertex consumer ----
        VertexConsumer consumer = vertexConsumers.getBuffer(
                RenderLayer.getItemEntityTranslucentCull(sprite.getAtlasId())
        );

        float minU = sprite.getMinU();
        float maxU = sprite.getMaxU();
        float minV = sprite.getMinV();
        float maxV = sprite.getMaxV();

        float z = 0.0f;

        // ---- Quad ----
        putVertex(consumer, entry, -0.5f, -0.5f, z, minU, maxV, light, overlay);
        putVertex(consumer, entry,  0.5f, -0.5f, z, maxU, maxV, light, overlay);
        putVertex(consumer, entry,  0.5f,  0.5f, z, maxU, minV, light, overlay);
        putVertex(consumer, entry, -0.5f,  0.5f, z, minU, minV, light, overlay);

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
