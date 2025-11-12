package com.dark.cmt.datagen;

import com.dark.cmt.registry.CMTBlocks;
import com.dark.cmt.registry.CMTItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.loottable.LootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CMTLootTableProvider extends FabricBlockLootTableProvider {

    public CMTLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(CMTBlocks.CASTSTONETILESSTAIRS);
        addDrop(CMTBlocks.CASTSTONETILES);
        addDrop(CMTBlocks.CASTSTONEBRICKS);
        addDrop(CMTBlocks.CASTSTONE);
        addDrop(CMTBlocks.SULFURICORE, multipleOreDrops(CMTBlocks.SULFURICORE, CMTItems.SULFUR, 1,2));
        addDrop(CMTBlocks.SULFURICDEEPSLATEORE, multipleOreDrops(CMTBlocks.SULFURICDEEPSLATEORE, CMTItems.SULFUR, 2,4));
        addDrop(CMTBlocks.SULFURICNETHERORE, multipleOreDrops(CMTBlocks.SULFURICNETHERORE, CMTItems.SULFUR, 6,9));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
