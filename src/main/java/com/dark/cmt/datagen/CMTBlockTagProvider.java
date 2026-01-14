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

                .add(CMTBlocks.SULFURICORE)
                .add(CMTBlocks.SULFURICNETHERORE)
                .add(CMTBlocks.SULFURICDEEPSLATEORE)

                .add(CMTBlocks.PHOSPHORICSTONE)
                .add(CMTBlocks.LEADORE)
                .add(CMTBlocks.TINORE)

                .add(CMTBlocks.SILVERBLOCK)
                .add(CMTBlocks.SILVERDEEPSLATEORE)
                .add(CMTBlocks.SILVERORE);

        getOrCreateTagBuilder(BlockTags.STAIRS);
    }
}
