package com.dark.cmt.block.blacksmithfurnace;

import com.dark.cmt.block.knowledgestones.KnowledgeStoneBlockEntity;
import com.dark.cmt.datagen.CMTItemTagProvider;
import com.dark.cmt.init.CMTBlockEntities;
import com.mojang.serialization.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class BlacksmithFurnaceBase extends BlockWithEntity implements BlockEntityProvider {

    public static final EnumProperty<MultiblockPart> MULTIBLOCKPART = EnumProperty.of("part", MultiblockPart.class);
    public static final BooleanProperty FILLED = BooleanProperty.of("filled");
    public static final BooleanProperty CONTROLLER = BooleanProperty.of("controller");


    public BlacksmithFurnaceBase() {
        super(FabricBlockSettings
                .copyShallow(Blocks.STONE)
                .hardness(3f)
                .resistance(3f)
                .strength(3f)
                .requiresTool()
        );
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(MULTIBLOCKPART, MultiblockPart.INDIVIDUAL_BLOCK).with(FILLED, false).with(CONTROLLER, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MULTIBLOCKPART, FILLED, CONTROLLER);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return new MapCodec<BlockWithEntity>() {
            @Override
            public <T> Stream<T> keys(DynamicOps<T> ops) {
                return Stream.empty();
            }

            @Override
            public <T> DataResult<BlockWithEntity> decode(DynamicOps<T> ops, MapLike<T> input) {
                return null;
            }

            @Override
            public <T> RecordBuilder<T> encode(BlockWithEntity input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
                return null;
            }
        };
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlacksmithFurnaceBlockEntity(pos, state);
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
        if (!(be instanceof BlacksmithFurnaceBlockEntity furnace)) return ItemActionResult.FAIL;

        ItemStack held = player.getStackInHand(hand);
        if (!held.isIn(CMTItemTagProvider.BLACKSMITH_FURNACE_FUEL)) {
            if (player.isSneaking() && held.isEmpty()) {
                player.setStackInHand(hand, furnace.getItemToController());
                return ItemActionResult.SUCCESS;
            } else if (!held.isEmpty()){
                furnace.sendItemToController(held);
                player.getMainHandStack().decrement(1);
                return ItemActionResult.SUCCESS;
            }
        } else if (held.isIn(CMTItemTagProvider.BLACKSMITH_FURNACE_FUEL)) {
            furnace.sendItemToControllerFuel(held);
            player.getMainHandStack().decrement(1);
        }
        return ItemActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            tryFormMultiblock(world, pos);
        }
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    private void tryFormMultiblock(World world, BlockPos pos) {
        // Check nearby 2x2 area
        for (int dx = -1; dx <= 0; dx++) {
            for (int dz = -1; dz <= 0; dz++) {
                BlockPos origin = pos.add(dx, 0, dz);
                if (isValidStructure(world, origin)) {
                    formMultiblock(world, origin);
                    return;
                }
            }
        }
    }

    private boolean isValidStructure(World world, BlockPos origin) {
        for (int x = 0; x < 2; x++) {
            for (int z = 0; z < 2; z++) {
                BlockPos check = origin.add(x, 0, z);
                BlockState state = world.getBlockState(check);
                if (!(state.getBlock() instanceof BlacksmithFurnaceBase)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (w, pos, s, be) -> BlacksmithFurnaceBlockEntity.tick(w, pos, s, (BlacksmithFurnaceBlockEntity) be);
    }

    private void formMultiblock(World world, BlockPos origin) {
        // Assign parts for 2x2
        BlockPos[][] positions = new BlockPos[][]{
                {origin, origin.add(1, 0, 0)},
                {origin.add(0, 0, 1), origin.add(1, 0, 1)}
        };

        MultiblockPart[][] parts = new MultiblockPart[][]{
                {MultiblockPart.TOP_LEFT, MultiblockPart.TOP_RIGHT},
                {MultiblockPart.BOTTOM_LEFT, MultiblockPart.BOTTOM_RIGHT}
        };

        for (int x = 0; x < 2; x++) {
            for (int z = 0; z < 2; z++) {
                BlockPos pos = positions[x][z];
                BlockState newState = world.getBlockState(pos).with(MULTIBLOCKPART, parts[x][z]);
                world.setBlockState(pos, newState, 3);

                // Set controller reference in BlockEntity
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof BlacksmithFurnaceBlockEntity storage) {
                    storage.setController(origin);
                }
            }
        }
        BlockState controllerState = world.getBlockState(origin).with(CONTROLLER, true);
        world.setBlockState(origin, controllerState, 3);
    }

    public enum MultiblockPart implements StringIdentifiable {
        TOP_LEFT("top_left"),
        TOP_RIGHT("top_right"),
        BOTTOM_LEFT("bottom_left"),
        BOTTOM_RIGHT("bottom_right"),
        INDIVIDUAL_BLOCK("individual");

        private final String name;

        MultiblockPart(String name) {
            this.name = name;
        }
        @Override
        public String asString() {
            return name;
        }
    }
}
