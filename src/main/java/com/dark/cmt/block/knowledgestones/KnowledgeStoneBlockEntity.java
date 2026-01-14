package com.dark.cmt.block.knowledgestones;

import com.dark.cmt.init.CMTBlockEntities;
import com.dark.cmt.init.custom.SmithingManualRecipes;
import com.dark.cmt.recipe.SmithingManualRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class KnowledgeStoneBlockEntity extends BlockEntity {

    private Identifier presetId;
    private boolean initialized = false;

    public KnowledgeStoneBlockEntity(BlockPos pos, BlockState state) {
        super(CMTBlockEntities.KNOWLEDGESTONEBLOCKENTITY, pos, state);
    }

    public void initIfNeeded() {
        if (world == null || world.isClient || initialized) return;

        Random rand = new Random();

        int randomID = rand.nextInt(SmithingManualRecipes.RECIPES.size());
        SmithingManualRecipe recipe = SmithingManualRecipes.RECIPES.get(randomID);
        if (recipe.isKnowledgeStoneRecipe) {
            presetId = Identifier.of(recipe.getName());
            initialized = true;
        } else {
            initialized = false;
        }
        markDirty();
    }

    public Identifier getPresetId() {
        return presetId;
    }

    public boolean hasPreset(Identifier id) {
        return presetId != null && presetId.equals(id);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("Initialized")) {
            initialized = nbt.getBoolean("Initialized");
        }

        if (nbt.contains("Preset")) {
            presetId = Identifier.of(nbt.getString("Preset"));
        }

        // FIRST LOAD: structure or fresh placement
        if (!initialized && world != null && !world.isClient) {
            initIfNeeded();
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putBoolean("Initialized", initialized);

        if (presetId != null) {
            nbt.putString("Preset", presetId.toString());
        }
    }
}
