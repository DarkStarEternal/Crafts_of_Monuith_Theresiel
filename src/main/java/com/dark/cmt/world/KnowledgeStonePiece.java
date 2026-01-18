package com.dark.cmt.world;

import com.dark.cmt.init.CMTStructures;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class KnowledgeStonePiece extends StructurePiece {

    private final Identifier templateId;
    private final BlockRotation rotation;
    private BlockPos pos;

    public KnowledgeStonePiece(
            StructureTemplateManager manager,
            Identifier templateId,
            BlockPos pos,
            BlockRotation rotation
    ) {
        // Initially set a valid bounding box (can be any small box)
        super(CMTStructures.KNOWLEDGE_STONE_PIECE, 0, new BlockBox(pos.getX(), pos.getY(), pos.getZ(),
                pos.getX(), pos.getY(), pos.getZ()));
        this.pos = pos;
        this.templateId = templateId;
        this.rotation = rotation;
    }

    public KnowledgeStonePiece(StructureContext context, NbtCompound nbt) {
        super(CMTStructures.KNOWLEDGE_STONE_PIECE, nbt);
        this.templateId = Identifier.of(nbt.getString("Template"));
        this.rotation = BlockRotation.valueOf(nbt.getString("Rot"));
    }

    @Override
    protected void writeNbt(StructureContext context, NbtCompound nbt) {
        nbt.putString("Template", this.templateId.toString());
        nbt.putString("Rot", this.rotation.name());
    }

    @Override
    public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        StructureTemplateManager manager = world.getServer().getStructureTemplateManager();
        StructureTemplate template = manager.getTemplate(this.templateId).orElse(null);

        if (template == null) {
            return;
        }

        StructurePlacementData placementData = new StructurePlacementData()
                .setRotation(this.rotation)
                .setIgnoreEntities(false);

        template.place(
                world,
                this.pos,
                this.pos,
                placementData,
                random,
                2
        );
        System.out.println("Placing template at: " + this.pos + " with template " + this.templateId);
    }
}
