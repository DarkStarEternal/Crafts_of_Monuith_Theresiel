package com.dark.cmt;

import com.dark.cmt.datagen.*;
import com.dark.cmt.world.CMTConfiguredFeatures;
import com.dark.cmt.world.CMTPlaceFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class CMTDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(CMTBlockTagProvider::new);
        pack.addProvider(CMTItemTagProvider::new);
        pack.addProvider(CMTModelProvider::new);
        pack.addProvider(CMTRecipeProvider::new);
        pack.addProvider(CMTLootTableProvider::new);
        pack.addProvider(CMTRegistryDataGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, CMTConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, CMTPlaceFeatures::bootstrap);
    }
}
