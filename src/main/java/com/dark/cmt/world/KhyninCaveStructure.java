package com.dark.cmt.world;

import com.dark.cmt.CMT;
import com.dark.cmt.init.CMTStructures;
import com.mojang.serialization.MapCodec;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class KhyninCaveStructure extends Structure {

    public static final MapCodec<KhyninCaveStructure> CODEC =
            Structure.createCodec(KhyninCaveStructure::new);

    public KhyninCaveStructure(Config config) {
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
                ) -29,
                context.chunkPos().getStartZ()
        );

        collector.addPiece(
                new KnowledgeStonePiece(
                        context.structureTemplateManager(),
                        Identifier.of(CMT.MODID, "khynin_cave"),
                        pos,
                        BlockRotation.NONE
                )
        );
    }


    @Override
    public StructureType<?> getType() {
        return CMTStructures.KHYNIN_CAVE_STRUCTURE;
    }
}