package com.beastnighttv.jak.block.custom;

import javax.annotation.Nullable;
import com.beastnighttv.jak.block.entity.GemInfusingStationBlockEntity;
import com.beastnighttv.jak.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class GemInfusingStationBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public GemInfusingStationBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 10, 16);

    @Override
    public VoxelShape getShape(
        BlockState blockState,
        BlockGetter BlockGetter,
        BlockPos BlockPos,
        CollisionContext CollisionContext
    ) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean pIsMoving) {
        if (blockState.getBlock() != blockState2.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GemInfusingStationBlockEntity) {
                ((GemInfusingStationBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(blockState, level, blockPos, blockState2, pIsMoving);
    }

    @Override
    public InteractionResult use(
        BlockState blockState,
        Level level,
        BlockPos blockPos,
        Player player,
        InteractionHand interactionHand,
        BlockHitResult blockHitResult
    ) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(blockPos);
            if (entity instanceof GemInfusingStationBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), (GemInfusingStationBlockEntity) entity, blockPos);
            } else {
                throw new IllegalStateException("Our container provider is illegal.");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GemInfusingStationBlockEntity(blockPos, blockState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        Level level,
        BlockState blockState,
        BlockEntityType<T> blockEntityType) {
            return createTickerHelper(
                blockEntityType,
                ModBlockEntities.GEM_INFUSING_STATION.get(),
                GemInfusingStationBlockEntity::tick
            );
        }
}
