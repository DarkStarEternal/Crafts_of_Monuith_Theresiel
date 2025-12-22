/*package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.registry.custom.MetalMaterialRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CMTTextureLoader {
    public static void loadMaterials() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(
                new SimpleSynchronousResourceReloadListener() {
                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of(CMT.MODID, "smithing_material_textures");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        MinecraftClient client = MinecraftClient.getInstance();
                        if (client == null) return;

                        for (SmithingMaterial mat : MetalMaterialRegistry.MATERIALS) {
                            for (Identifier tex : mat.textureIdentifiers) {
                                try {
                                    client.getTextureManager().registerTexture(tex, new ResourceTexture(tex));
                                } catch (Exception e) {
                                    System.err.println("Failed to load texture: " + tex);
                                }
                            }
                        }
                    }
                }
        );
    }
    public static void preload() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(
                new SimpleSynchronousResourceReloadListener() {
                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of("cmt", "smithing_material_textures");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        MinecraftClient client = MinecraftClient.getInstance();
                        if (client == null) return;

                        for (SmithingMaterial mat : MetalMaterialRegistry.MATERIALS) {
                            for (Identifier tex : mat.textureIdentifiers) {
                                try {
                                    client.getTextureManager().registerTexture(tex, new ResourceTexture(tex));
                                } catch (Exception e) {
                                    System.err.println("Failed to preload texture: " + tex);
                                }
                            }
                        }
                    }
                }
        );
    }
}
*/