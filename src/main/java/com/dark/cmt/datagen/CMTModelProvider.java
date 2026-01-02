package com.dark.cmt.datagen;

import com.dark.cmt.registry.CMTBlocks;
import com.dark.cmt.registry.CMTItems;
import com.dark.cmt.registry.custom.MetalMaterialRegistry;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CMTModelProvider extends FabricModelProvider {
    public CMTModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SULFURICORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SULFURICDEEPSLATEORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SULFURICNETHERORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.TINORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.LEADORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SILVERORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SILVERDEEPSLATEORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SILVERBLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(CMTItems.SULFUR, Models.GENERATED);
        itemModelGenerator.register(CMTItems.SULFURICCOMPOUND, Models.GENERATED);
        itemModelGenerator.register(CMTItems.BASICMANUAL, Models.GENERATED);
        itemModelGenerator.register(CMTItems.HEPHAISTONMANUAL, Models.GENERATED);
        itemModelGenerator.register(CMTItems.SILVERINGOT, Models.GENERATED);
        itemModelGenerator.register(CMTItems.RAWSILVER, Models.GENERATED);
    }
}
