package com.dark.cmt.item.smitheditems.unfinished;


import com.dark.cmt.CMT;
import com.dark.cmt.registry.custom.MetalMaterialRegistry;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class UnfinishedHookBakedModels {

    public static class HookUnbakedModel implements UnbakedModel {

        private final List<SpriteIdentifier> materials =
                MetalMaterialRegistry.getAllLayers();

        @Override
        public Collection<Identifier> getModelDependencies() {
            return List.of();
        }

        @Override
        public void setParents(Function<Identifier, UnbakedModel> modelLoader) {}

        @Override
        public BakedModel bake(
                Baker baker,
                Function<SpriteIdentifier, Sprite> textureGetter,
                ModelBakeSettings settings
        ) {
            Map<String, Sprite> sprites = new HashMap<>();

            for (SpriteIdentifier id : materials) {
                // path end ("lead_unfinished") used as material key
                String key = id.getTextureId().getPath()
                        .substring(id.getTextureId().getPath().lastIndexOf('/') + 1);

                sprites.put(key, textureGetter.apply(id));
            }

            return new HookBakedModel(sprites);
        }
    }


    public static class HookBakedModel implements BakedModel, FabricBakedModel {

        private final Map<String, Sprite> sprites;

        public HookBakedModel(Map<String, Sprite> sprites) {
            this.sprites = sprites;
        }

        @Override
        public void emitItemQuads(
                ItemStack stack,
                Supplier<Random> randomSupplier,
                RenderContext context
        ) {
            String material = "lead";

            if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
                NbtCompound nbt = stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt();
                if (nbt != null && nbt.contains("Material")) {
                    material = nbt.getString("Material") + "_unfinished";
                }
            }

            Sprite sprite = sprites.getOrDefault(
                    material,
                    sprites.values().iterator().next()
            );

            QuadEmitter e = context.getEmitter();

            // FRONT
            e.square(Direction.NORTH, 0, 0, 1, 1, 0);
            e.spriteBake(sprite, QuadEmitter.BAKE_LOCK_UV);
            e.color(-1, -1, -1, -1);
            e.emit();

            // BACK (for thickness)
            e.square(Direction.SOUTH, 0, 0, 1, 1, 0);
            e.spriteBake(sprite, QuadEmitter.BAKE_LOCK_UV);
            e.color(-1, -1, -1, -1);
            e.emit();

            System.out.println("MODEL RENDERING YAAAAAAA");
        }

        // --- block path unused ---
        @Override
        public List<BakedQuad> getQuads(
                @Nullable BlockState state,
                @Nullable Direction face,
                Random random
        ) {
            return List.of();
        }

        // --- required flags ---
        @Override public boolean useAmbientOcclusion() { return true; }
        @Override public boolean hasDepth() { return true; }
        @Override public boolean isSideLit() { return false; }
        @Override public boolean isBuiltin() { return false; }

        @Override
        public Sprite getParticleSprite() {
            return sprites.values().iterator().next();
        }

        @Override
        public ModelTransformation getTransformation() {
            return null;
        }

        @Override
        public ModelOverrideList getOverrides() {
            return null;
        }
    }

}
