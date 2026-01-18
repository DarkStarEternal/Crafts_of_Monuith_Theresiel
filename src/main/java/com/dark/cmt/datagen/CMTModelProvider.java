package com.dark.cmt.datagen;

import com.dark.cmt.init.CMTBlocks;
import com.dark.cmt.init.CMTItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

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
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.DEEPSLATETINORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.TINBLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.LEADORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.DEEPSLATELEADORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.LEADBLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.STEELBLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.BRONZEBLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.PHOSPHORICSTONE);

        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SILVERORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.DEEPSLATESILVERORE);
        blockStateModelGenerator.registerSimpleCubeAll(CMTBlocks.SILVERBLOCK);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(CMTItems.SULFUR, Models.GENERATED);
        itemModelGenerator.register(CMTItems.SULFURICCOMPOUND, Models.GENERATED);

        itemModelGenerator.register(CMTItems.PHOSPHORUS, Models.GENERATED);

        itemModelGenerator.register(CMTItems.BRONZEINGOT, Models.GENERATED);
        itemModelGenerator.register(CMTItems.SILVERINGOT, Models.GENERATED);
        itemModelGenerator.register(CMTItems.LEADINGOT, Models.GENERATED);
        itemModelGenerator.register(CMTItems.STEELINGOT, Models.GENERATED);
        itemModelGenerator.register(CMTItems.TININGOT, Models.GENERATED);
        itemModelGenerator.register(CMTItems.DWARFGOLDINGOT, Models.GENERATED);

        itemModelGenerator.register(CMTItems.NETHERITENUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.BRONZENUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.COPPERNUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.DWARFGOLDNUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.LEADNUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.STEELNUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.TINNUGGET, Models.GENERATED);
        itemModelGenerator.register(CMTItems.SILVERNUGGET, Models.GENERATED);

        itemModelGenerator.register(CMTItems.BASICMANUAL, Models.GENERATED);
        itemModelGenerator.register(CMTItems.HEPHAISTONMANUAL, Models.GENERATED);

        itemModelGenerator.register(CMTItems.RAWSILVER, Models.GENERATED);
        itemModelGenerator.register(CMTItems.RAWLEAD, Models.GENERATED);
        itemModelGenerator.register(CMTItems.RAWTIN, Models.GENERATED);
    }
}
