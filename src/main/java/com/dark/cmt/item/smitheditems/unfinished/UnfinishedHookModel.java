package com.dark.cmt.item.smitheditems.unfinished;

import com.dark.cmt.CMT;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class UnfinishedHookModel {

    public static class Unbaked implements UnbakedModel {

        @Override
        public Collection<Identifier> getModelDependencies() {
            return List.of(); // no parent models
        }

        @Override
        public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
            // no parents
        }

        @Override
        public @Nullable BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer) {
            return new Baked(textureGetter);
        }
    }

    public static class Baked implements BakedModel {

        private final Function<SpriteIdentifier, Sprite> spriteGetter;

        public Baked(Function<SpriteIdentifier, Sprite> spriteGetter) {
            this.spriteGetter = spriteGetter;
        }

        @Override
        public void emitItemQuads(ItemStack stack, Supplier<Random> random, RenderContext context) {
            // Default material
            String material = "default";

            NbtCompound tag = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
            if (tag != null && tag.contains("material")) {
                material = tag.getString("material");
            }

            // Build texture path
            SpriteIdentifier spriteId = new SpriteIdentifier(
                    SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE,
                    Identifier.of(CMT.MODID, "item/rounded/hook/head/lead_unfinished")
            );

            Sprite sprite = spriteGetter.apply(spriteId);
            if (sprite == null) return;

            QuadEmitter emitter = context.getEmitter();
            emitter.square(Direction.NORTH, 0f, 0f, 1f, 1f, 0f);
            emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(255, 255, 255, 255); // full opacity
            emitter.emit();
        }

        @Override
        public boolean isVanillaAdapter() { return false; }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
            return List.of();
        }

        @Override
        public boolean useAmbientOcclusion() { return false; }
        @Override public boolean hasDepth() { return true; }
        @Override public boolean isSideLit() { return true; }
        @Override public boolean isBuiltin() { return false; }

        @Override
        public Sprite getParticleSprite() {
            return spriteGetter.apply(new SpriteIdentifier(
                    SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE,
                    Identifier.of(CMT.MODID, "item/rounded/hook/head/default_unfinished")
            ));
        }

        @Override
        public ModelTransformation getTransformation() {
            return null;
        }

        @Override
        public ModelOverrideList getOverrides() {
            return ModelOverrideList.EMPTY; // use default overrides
        }
    }
}