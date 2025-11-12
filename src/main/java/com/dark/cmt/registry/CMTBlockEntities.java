package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceBlockEntity;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTBlockEntities {
    public static final BlockEntityType BLACKSMITHFURNACEBLOCKENTITY = Registry.register
            (Registries.BLOCK_ENTITY_TYPE, Identifier.of(CMT.MODID, "blacksmith_furnace_base"), FabricBlockEntityTypeBuilder.create(BlacksmithFurnaceBlockEntity::new, CMTBlocks.BLACKSMITHFURNACEBASE).build());
    public static final BlockEntityType SMITHINGANVILBLOCKENTITY = Registry.register
            (Registries.BLOCK_ENTITY_TYPE, Identifier.of(CMT.MODID, "smithing_anvil"), FabricBlockEntityTypeBuilder.create(SmithingAnvilBlockEntity::new, CMTBlocks.SMITHINGANVIL).build());

    public static void registerModBlockEntities() {

    }
}
