package com.dark.cmt.item;

import com.dark.cmt.CMT;
import com.dark.cmt.registry.CMTBlocks;
import com.dark.cmt.registry.CMTItems;
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
                entries.add(Items.IRON_INGOT);
                entries.add(Items.COPPER_INGOT);
                entries.add(Items.GOLD_INGOT);
                entries.add(Items.NETHERITE_INGOT);

                entries.add(CMTItems.CRUDEIRONINGOT);
                entries.add(CMTItems.CRUDEIRONAXE);
                entries.add(CMTItems.CRUDEIRONPICKAXE);
                entries.add(CMTItems.CRUDEIRONSWORD);
                entries.add(CMTItems.CRUDEIRONSHOVEL);
                entries.add(CMTItems.CRUDEIRONHOE);

                entries.add(CMTItems.SULFUR);
                entries.add(CMTItems.SULFURICCOMPOUND);

                entries.add(CMTBlocks.CASTSTONE);
                entries.add(CMTBlocks.CASTSTONEBRICKS);
                entries.add(CMTBlocks.CASTSTONETILES);
                entries.add(CMTBlocks.CASTSTONETILESSTAIRS);

                entries.add(CMTBlocks.BLACKSMITHFURNACEBASE);

                entries.add(CMTBlocks.SMITHINGANVIL);
            }))
            .build());


    public static void registerItemGroups() {

    }
}
