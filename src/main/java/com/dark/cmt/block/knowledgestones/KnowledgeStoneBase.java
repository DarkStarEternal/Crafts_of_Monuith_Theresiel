package com.dark.cmt.block.knowledgestones;

import com.dark.cmt.block.blacksmithfurnace.BlacksmithFurnaceBase;
import com.dark.cmt.block.pedestal.PedestalBlock;
import com.dark.cmt.init.CMTBlockEntities;
import com.dark.cmt.item.SmithingManual;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KnowledgeStoneBase extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<KnowledgeStoneBase.Design> DESIGN = EnumProperty.of("design", KnowledgeStoneBase.Design.class);
    public static final MapCodec<KnowledgeStoneBase> CODEC = KnowledgeStoneBase.createCodec(KnowledgeStoneBase::new);

    public KnowledgeStoneBase(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(DESIGN, Design.NORMAL));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, DESIGN);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KnowledgeStoneBlockEntity(pos, state);
    }

    @Override
    public ItemActionResult onUseWithItem(
            ItemStack stack,
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit
    ) {
        if (world.isClient) return ItemActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof KnowledgeStoneBlockEntity stone)) {
            return ItemActionResult.FAIL;
        }

        if (!stone.isInitialized()) {
            stone.initIfNeeded();
        }

        ItemStack held = player.getStackInHand(hand);

        if (held.getItem() instanceof SmithingManual manual) {

            Identifier preset = stone.getPresetId();
            if (preset == null) {
                return ItemActionResult.FAIL;
            }
            else {
                manual.addAndReturn(held, preset);
                return ItemActionResult.SUCCESS;
            }
        }

        return ItemActionResult.FAIL;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!world.isClient && type == CMTBlockEntities.KNOWLEDGESTONEBLOCKENTITY) {
            return (w, pos, s, be) -> KnowledgeStoneBlockEntity.tick(w, pos, s, (KnowledgeStoneBlockEntity) be);
        }
        return null;
    }

    @Override
    public BlockState getAppearance(BlockState state, BlockRenderView renderView, BlockPos pos, Direction side, @Nullable BlockState sourceState, @Nullable BlockPos sourcePos) {
        return super.getAppearance(state, renderView, pos, side, sourceState, sourcePos);
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }

    public enum Design implements StringIdentifiable {
        NORMAL("normal"),
        BURNT_TERRACOTTA("burnt_terracotta");

        private final String name;

        Design(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }
    }
}
