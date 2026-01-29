package com.dark.cmt.datagen;

import com.dark.cmt.init.CMTBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class CMTBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public CMTBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(CMTBlocks.BLACKSMITHFURNACEBASE)
                .add(CMTBlocks.SMITHINGANVIL)

                .add(CMTBlocks.BURNT_TERRACOTTA)
                .add(CMTBlocks.ORNATE_BURNT_TERRACOTTA)
                .add(CMTBlocks.PEDESTAL)

                .add(CMTBlocks.SULFURICORE)
                .add(CMTBlocks.SULFURICNETHERORE)
                .add(CMTBlocks.SULFURICDEEPSLATEORE)

                .add(CMTBlocks.PHOSPHORICSTONE)

                .add(CMTBlocks.BRONZEBLOCK)

                .add(CMTBlocks.GOLDCOINSTACK)
                .add(CMTBlocks.STEELCOINSTACK)
                .add(CMTBlocks.MIXEDCOINSTACK)

                .add(CMTBlocks.STEELBLOCK)

                .add(CMTBlocks.LEADORE)
                .add(CMTBlocks.DEEPSLATELEADORE)
                .add(CMTBlocks.LEADBLOCK)

                .add(CMTBlocks.TINORE)
                .add(CMTBlocks.DEEPSLATETINORE)
                .add(CMTBlocks.TINBLOCK)

                .add(CMTBlocks.JADEBLOCK)

                .add(CMTBlocks.SILVERBLOCK)
                .add(CMTBlocks.DEEPSLATESILVERORE)
                .add(CMTBlocks.SILVERORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(CMTBlocks.SULFURICORE)
                .add(CMTBlocks.SULFURICNETHERORE)
                .add(CMTBlocks.SULFURICDEEPSLATEORE)

                .add(CMTBlocks.PHOSPHORICSTONE)

                .add(CMTBlocks.BRONZEBLOCK)

                .add(CMTBlocks.GOLDCOINSTACK)
                .add(CMTBlocks.STEELCOINSTACK)
                .add(CMTBlocks.MIXEDCOINSTACK)

                .add(CMTBlocks.STEELBLOCK)

                .add(CMTBlocks.LEADORE)
                .add(CMTBlocks.DEEPSLATELEADORE)
                .add(CMTBlocks.LEADBLOCK)

                .add(CMTBlocks.TINORE)
                .add(CMTBlocks.DEEPSLATETINORE)
                .add(CMTBlocks.TINBLOCK)

                .add(CMTBlocks.JADEBLOCK)

                .add(CMTBlocks.SILVERBLOCK)
                .add(CMTBlocks.DEEPSLATESILVERORE)
                .add(CMTBlocks.SILVERORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(CMTBlocks.BLACKSMITHFURNACEBASE)
                .add(CMTBlocks.SMITHINGANVIL);
    }
}
