package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.block.knowledgestones.KnowledgeStoneBlockEntity;
import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceBlockEntity;
import com.dark.cmt.block.pedestal.PedestalBlockEntity;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTBlockEntities {
    public static final BlockEntityType BLACKSMITHFURNACEBLOCKENTITY = Registry.register
            (Registries.BLOCK_ENTITY_TYPE, Identifier.of(CMT.MODID, "blacksmith_furnace_base"), FabricBlockEntityTypeBuilder.create(BlacksmithFurnaceBlockEntity::new, CMTBlocks.BLACKSMITHFURNACEBASE).build());
    public static final BlockEntityType KNOWLEDGESTONEBLOCKENTITY = Registry.register
            (Registries.BLOCK_ENTITY_TYPE, Identifier.of(CMT.MODID, "knowledge_stone_base"), FabricBlockEntityTypeBuilder.create(KnowledgeStoneBlockEntity::new, CMTBlocks.KNOWLEDGESTONE).build());
    public static final BlockEntityType SMITHINGANVILBLOCKENTITY = Registry.register
            (Registries.BLOCK_ENTITY_TYPE, Identifier.of(CMT.MODID, "smithing_anvil"), FabricBlockEntityTypeBuilder.create(SmithingAnvilBlockEntity::new, CMTBlocks.SMITHINGANVIL).build());
    public static final BlockEntityType PEDESTALBLOCKENTITY = Registry.register
            (Registries.BLOCK_ENTITY_TYPE, Identifier.of(CMT.MODID, "smithing_anvil"), FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, CMTBlocks.PEDESTAL).build());

    public static void registerModBlockEntities() {

    }
}
