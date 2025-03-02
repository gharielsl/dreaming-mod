package com.ghariel.dreaming_mod.block.bed;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DreamingBedBlock extends BedBlock {
    private final String dreamLevel;

    public DreamingBedBlock(DyeColor dyeColor, String dreamLevel) {
        super(dyeColor, Properties.copy(Blocks.BLACK_BED).sound(SoundType.AMETHYST));
        this.dreamLevel = dreamLevel;
        registerDefaultState(getStateDefinition().any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false));
    }

    public String getDreamLevel() {
        return dreamLevel;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new DreamingBedBlockEntity(pos, blockState);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 4;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState blockState){
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction dir = getConnectedDirection(state).getOpposite();
        switch (dir) {
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
                return EAST_SHAPE;
            }
        }
    }
}
