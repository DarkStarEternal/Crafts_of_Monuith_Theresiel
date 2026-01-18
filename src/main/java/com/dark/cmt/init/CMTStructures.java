package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.world.KnowledgeStonePiece;
import com.dark.cmt.world.KnowledgeStoneStructure;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class CMTStructures {
    public static final StructureType<KnowledgeStoneStructure> KNOWLEDGE_STONE_STRUCTURE =
            Registry.register(
                    Registries.STRUCTURE_TYPE,
                    Identifier.of(CMT.MODID, "knowledge_stone"),
                    () -> KnowledgeStoneStructure.CODEC
            );

    public static final StructurePieceType KNOWLEDGE_STONE_PIECE =
            Registry.register(
                    Registries.STRUCTURE_PIECE,
                    Identifier.of(CMT.MODID, "knowledge_stone"),
                    KnowledgeStonePiece::new
            );

    public static void init() {}
}
