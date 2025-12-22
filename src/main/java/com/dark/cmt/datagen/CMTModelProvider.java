package com.dark.cmt.datagen;

import com.dark.cmt.registry.CMTBlocks;
import com.dark.cmt.registry.CMTItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class CMTModelProvider extends FabricModelProvider {
    public CMTModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool castStoneTilesPool = blockStateModelGenerator.registerCubeAllModelTexturePool(CMTBlocks.CASTSTONETILES);
        castStoneTilesPool.stairs(CMTBlocks.CASTSTONETILESSTAIRS);
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
