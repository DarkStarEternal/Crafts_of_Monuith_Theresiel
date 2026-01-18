package com.dark.cmt.world;

import com.dark.cmt.CMT;
import com.dark.cmt.init.CMTStructures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class KnowledgeStoneStructure extends Structure {

    public static final MapCodec<KnowledgeStoneStructure> CODEC =
            Structure.createCodec(KnowledgeStoneStructure::new);

    public KnowledgeStoneStructure(Config config) {
        super(config);
    }

    @Override
    public Optional<StructurePosition> getStructurePosition(
            Context context) {

        return getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG,
                collector -> addPieces(collector, context));
    }

    private void addPieces(StructurePiecesCollector collector, Context context) {
        BlockPos pos = new BlockPos(
                context.chunkPos().getStartX(),
                context.chunkGenerator().getHeight(
                        context.chunkPos().getStartX(),
                        context.chunkPos().getStartZ(),
                        Heightmap.Type.WORLD_SURFACE_WG,
                        context.world(),
                        context.noiseConfig()
                ),
                context.chunkPos().getStartZ()
        );

        System.out.println("Adding KnowledgeStonePiece at " + pos);

        collector.addPiece(
                new KnowledgeStonePiece(
                        context.structureTemplateManager(),
                        Identifier.of(CMT.MODID, "knowledge_stone"),
                        pos,
                        BlockRotation.NONE
                )
        );
    }


    @Override
    public StructureType<?> getType() {
        return CMTStructures.KNOWLEDGE_STONE_STRUCTURE;
    }
}