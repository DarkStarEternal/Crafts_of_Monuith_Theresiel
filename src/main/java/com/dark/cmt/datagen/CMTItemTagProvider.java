package com.dark.cmt.datagen;

import com.dark.cmt.registry.CMTItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;


public class CMTItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static final TagKey<Item> TIN_INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "tin_ingots")
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

    public static final TagKey<Item> INGOTS = TagKey.of(
            net.minecraft.registry.RegistryKeys.ITEM,
            Identifier.of("c", "ingots")
    );

    public CMTItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        // Add your mod’s items to common tag c:iron_ingots
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

        getOrCreateTagBuilder(INGOTS)
                .add(CMTItems.SILVERINGOT)
                .add(CMTItems.TININGOT)
                .add(CMTItems.BRONZEINGOT)
                .add(CMTItems.STEELINGOT)
                .add(Items.COPPER_INGOT)
                .add(Items.IRON_INGOT)
                .add(Items.GOLD_INGOT)
                .add(Items.NETHERITE_INGOT)
                .add(CMTItems.LEADINGOT);
    }
}