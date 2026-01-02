package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CMTItemGroups {
    public static final ItemGroup CMTGROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(CMT.MODID, "cmt"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(CMTItems.SULFURICCOMPOUND))
            .displayName(Text.translatable("itemgroup.cmt.cmt"))
            .entries(((displayContext, entries) -> {

                entries.add(CMTBlocks.BURNT_TERRACOTTA);
                entries.add(CMTBlocks.ORNATE_BURNT_TERRACOTTA);

                entries.add(CMTBlocks.BLACKSMITHFURNACEBASE);

                entries.add(CMTBlocks.SMITHINGANVIL);

                entries.add(CMTBlocks.LEADORE);
                entries.add(CMTBlocks.SULFURICORE);
                entries.add(CMTBlocks.SULFURICDEEPSLATEORE);
                entries.add(CMTBlocks.SULFURICNETHERORE);
                entries.add(CMTBlocks.TINORE);
                entries.add(CMTBlocks.SILVERORE);
                entries.add(CMTBlocks.SILVERDEEPSLATEORE);
                entries.add(CMTBlocks.SILVERBLOCK);

                entries.add(CMTItems.SULFUR);
                entries.add(CMTItems.SULFURICCOMPOUND);

                entries.add(CMTItems.TININGOT);

                entries.add(CMTItems.LEADINGOT);

                entries.add(CMTItems.STEELINGOT);

                entries.add(CMTItems.SILVERINGOT);
                entries.add(CMTItems.RAWSILVER);

                entries.add(Items.IRON_INGOT);
                entries.add(Items.COPPER_INGOT);
                entries.add(Items.GOLD_INGOT);
                entries.add(Items.NETHERITE_INGOT);

                entries.add(CMTItems.BASICMANUAL);
                entries.add(CMTItems.HEPHAISTONMANUAL);
            }))
            .build());


    public static void registerItemGroups() {

    }
}
