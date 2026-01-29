package com.dark.cmt.datagen;

import com.dark.cmt.CMT;
import com.dark.cmt.init.CMTBlocks;
import com.dark.cmt.init.CMTItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;


public class CMTItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static final TagKey<Item> AMBER_HEART_BASE = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "amber_heart_base")
    );

    public static final TagKey<Item> BLACKSMITH_FURNACE_FUEL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "blacksmith_furnace_fuel")
    );
    public static final TagKey<Item> BF_FUEL_LOW = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "blacksmith_furnace_fuel_low_quality")
    );
    public static final TagKey<Item> BF_FUEL_MEDIUM = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "blacksmith_furnace_fuel_medium_quality")
    );
    public static final TagKey<Item> BF_FUEL_HIGH = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "blacksmith_furnace_fuel_high_quality")
    );

    public static final TagKey<Item> TIN_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "tin_ingots")
    );
    public static final TagKey<Item> DWARF_GOLD_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "dwarf_gold_ingots")
    );
    public static final TagKey<Item> BRONZE_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "bronze_ingots")
    );
    public static final TagKey<Item> LEAD_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "lead_ingots")
    );
    public static final TagKey<Item> STEEL_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "steel_ingots")
    );
    public static final TagKey<Item> SILVER_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "silver_ingots")
    );
    public static final TagKey<Item> NETHERITE_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "netherite_ingots")
    );
    public static final TagKey<Item> GOLD_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "gold_ingots")
    );
    public static final TagKey<Item> IRON_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "iron_ingots")
    );
    public static final TagKey<Item> COPPER_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "copper_ingots")
    );

    public static final TagKey<Item> TIN_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "tin_material")
    );
    public static final TagKey<Item> SILVER_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "silver_material")
    );
    public static final TagKey<Item> LEAD_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "lead_material")
    );
    public static final TagKey<Item> STEEL_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "steel_material")
    );
    public static final TagKey<Item> BRONZE_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "bronze_material")
    );
    public static final TagKey<Item> GOLD_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "gold_material")
    );
    public static final TagKey<Item> IRON_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "iron_material")
    );
    public static final TagKey<Item> COPPER_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "copper_material")
    );
    public static final TagKey<Item> NETHERITE_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "netherite_material")
    );
    public static final TagKey<Item> DWARF_GOLD_MATERIAL = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "dwarf_gold_material")
    );

    public static final TagKey<Item> INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "ingots")
    );
    public static final TagKey<Item> NUGGETS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "nuggets")
    );
    public static final TagKey<Item> BLOCKS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of(CMT.MODID, "blocks")
    );

    public CMTItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(TIN_INGOTS)
                .add(CMTItems.TININGOT);
        getOrCreateTagBuilder(BRONZE_INGOTS)
                .add(CMTItems.BRONZEINGOT);
        getOrCreateTagBuilder(LEAD_INGOTS)
                .add(CMTItems.LEADINGOT);
        getOrCreateTagBuilder(STEEL_INGOTS)
                .add(CMTItems.STEELINGOT);
        getOrCreateTagBuilder(SILVER_INGOTS)
                .add(CMTItems.SILVERINGOT);
        getOrCreateTagBuilder(NETHERITE_INGOTS)
                .add(Items.NETHERITE_INGOT);
        getOrCreateTagBuilder(GOLD_INGOTS)
                .add(Items.GOLD_INGOT);
        getOrCreateTagBuilder(IRON_INGOTS)
                .add(Items.IRON_INGOT);
        getOrCreateTagBuilder(COPPER_INGOTS)
                .add(Items.COPPER_INGOT);
        getOrCreateTagBuilder(DWARF_GOLD_INGOTS)
                .add(CMTItems.DWARFGOLDINGOT);

        getOrCreateTagBuilder(AMBER_HEART_BASE)
                .add(Blocks.MAGMA_BLOCK.asItem());

        getOrCreateTagBuilder(BLACKSMITH_FURNACE_FUEL)
                .add(Items.COAL)
                .add(Items.BLAZE_ROD)
                .add(Items.COAL_BLOCK)
                .add(Items.BIRCH_LOG)
                .add(Items.CHERRY_LOG)
                .add(Items.OAK_LOG)
                .add(Items.DARK_OAK_LOG)
                .add(Items.JUNGLE_LOG)
                .add(Items.MANGROVE_LOG)
                .add(Items.ACACIA_LOG)
                .add(Items.BIRCH_WOOD)
                .add(Items.CHERRY_WOOD)
                .add(Items.OAK_WOOD)
                .add(Items.DARK_OAK_WOOD)
                .add(Items.JUNGLE_WOOD)
                .add(Items.MANGROVE_WOOD)
                .add(Items.ACACIA_WOOD);

        getOrCreateTagBuilder(BF_FUEL_LOW)
                .add(Items.BIRCH_LOG)
                .add(Items.CHERRY_LOG)
                .add(Items.OAK_LOG)
                .add(Items.DARK_OAK_LOG)
                .add(Items.JUNGLE_LOG)
                .add(Items.MANGROVE_LOG)
                .add(Items.ACACIA_LOG)
                .add(Items.BIRCH_WOOD)
                .add(Items.CHERRY_WOOD)
                .add(Items.OAK_WOOD)
                .add(Items.DARK_OAK_WOOD)
                .add(Items.JUNGLE_WOOD)
                .add(Items.MANGROVE_WOOD)
                .add(Items.ACACIA_WOOD);

        getOrCreateTagBuilder(BF_FUEL_MEDIUM)
                .add(Items.COAL)
                .add(Items.COAL_BLOCK);

        getOrCreateTagBuilder(BF_FUEL_HIGH)
                .add(Items.BLAZE_ROD);

        getOrCreateTagBuilder(COPPER_MATERIAL)
                .add(Items.COPPER_INGOT)
                .add(Items.COPPER_BLOCK)
                .add(CMTItems.COPPERNUGGET);
        getOrCreateTagBuilder(NETHERITE_MATERIAL)
                .add(Items.NETHERITE_INGOT)
                .add(Items.NETHERITE_BLOCK)
                .add(CMTItems.NETHERITENUGGET);
        getOrCreateTagBuilder(IRON_MATERIAL)
                .add(Items.IRON_INGOT)
                .add(Items.IRON_BLOCK)
                .add(Items.IRON_NUGGET);
        getOrCreateTagBuilder(GOLD_MATERIAL)
                .add(Items.GOLD_INGOT)
                .add(Items.GOLD_BLOCK)
                .add(Items.GOLD_NUGGET);
        getOrCreateTagBuilder(STEEL_MATERIAL)
                .add(CMTItems.STEELINGOT)
                .add(CMTBlocks.STEELBLOCK.asItem())
                .add(CMTItems.STEELNUGGET);
        getOrCreateTagBuilder(TIN_MATERIAL)
                .add(CMTItems.TININGOT)
                .add(CMTBlocks.TINBLOCK.asItem())
                .add(CMTItems.TINNUGGET);
        getOrCreateTagBuilder(SILVER_MATERIAL)
                .add(CMTItems.SILVERINGOT)
                .add(CMTBlocks.SILVERBLOCK.asItem())
                .add(CMTItems.SILVERNUGGET);
        getOrCreateTagBuilder(LEAD_MATERIAL)
                .add(CMTItems.LEADINGOT)
                .add(CMTBlocks.LEADBLOCK.asItem())
                .add(CMTItems.LEADNUGGET);
        getOrCreateTagBuilder(BRONZE_MATERIAL)
                .add(CMTItems.BRONZEINGOT)
                .add(CMTBlocks.BRONZEBLOCK.asItem())
                .add(CMTItems.BRONZENUGGET);
        getOrCreateTagBuilder(DWARF_GOLD_MATERIAL)
                .add(CMTItems.DWARFGOLDINGOT)
                .add(CMTItems.DWARFGOLDNUGGET);

        getOrCreateTagBuilder(INGOTS)
                .add(CMTItems.SILVERINGOT)
                .add(CMTItems.TININGOT)
                .add(CMTItems.BRONZEINGOT)
                .add(CMTItems.DWARFGOLDINGOT)
                .add(CMTItems.STEELINGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.IRON_INGOT)
                .add(Items.GOLD_INGOT)
                .add(Items.NETHERITE_INGOT)
                .add(CMTItems.LEADINGOT);

        getOrCreateTagBuilder(NUGGETS)
                .add(Items.GOLD_NUGGET)
                .add(CMTItems.LEADNUGGET)
                .add(CMTItems.BRONZENUGGET)
                .add(CMTItems.DWARFGOLDNUGGET)
                .add(CMTItems.SILVERNUGGET)
                .add(CMTItems.TINNUGGET)
                .add(CMTItems.STEELNUGGET)
                .add(CMTItems.NETHERITENUGGET)
                .add(CMTItems.COPPERNUGGET)
                .add(Items.IRON_NUGGET);

        getOrCreateTagBuilder(BLOCKS)
                .add(Items.GOLD_BLOCK)
                .add(CMTBlocks.LEADBLOCK.asItem())
                .add(CMTBlocks.BRONZEBLOCK.asItem())
                .add(CMTBlocks.SILVERBLOCK.asItem())
                .add(CMTBlocks.TINBLOCK.asItem())
                .add(CMTBlocks.STEELBLOCK.asItem())
                .add(Items.COPPER_BLOCK)
                .add(Items.NETHERITE_BLOCK)
                .add(Items.IRON_BLOCK);
    }
}