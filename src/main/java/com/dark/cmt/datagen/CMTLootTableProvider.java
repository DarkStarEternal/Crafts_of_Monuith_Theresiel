package com.dark.cmt.datagen;

import com.dark.cmt.init.CMTBlocks;
import com.dark.cmt.init.CMTItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class CMTLootTableProvider extends FabricBlockLootTableProvider {

    public CMTLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(CMTBlocks.SULFURICORE, multipleOreDrops(CMTBlocks.SULFURICORE, CMTItems.SULFUR, 1,2));
        addDrop(CMTBlocks.SULFURICDEEPSLATEORE, multipleOreDrops(CMTBlocks.SULFURICDEEPSLATEORE, CMTItems.SULFUR, 2,4));
        addDrop(CMTBlocks.SULFURICNETHERORE, multipleOreDrops(CMTBlocks.SULFURICNETHERORE, CMTItems.SULFUR, 6,9));

        addDrop(CMTBlocks.SILVERORE, multipleOreDrops(CMTBlocks.SILVERORE, CMTItems.RAWSILVER, 1,2));
        addDrop(CMTBlocks.SILVERDEEPSLATEORE, multipleOreDrops(CMTBlocks.SILVERDEEPSLATEORE, CMTItems.RAWSILVER, 2,3));

        addDrop(CMTBlocks.PHOSPHORICSTONE, multipleOreDrops(CMTBlocks.PHOSPHORICSTONE, CMTItems.PHOSPHORUS, 2,6));

        addDrop(CMTBlocks.LEADORE, multipleOreDrops(CMTBlocks.LEADORE, CMTItems.RAWLEAD, 2,4));

        addDrop(CMTBlocks.TINORE, multipleOreDrops(CMTBlocks.TINORE, CMTItems.RAWTIN, 1,2));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
