package com.ghariel.dreaming_mod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ChocolateBushBlock extends DeadBushBlock {
    public ChocolateBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return blockState.getBlock() == ModBlocks.PANCAKE_BLOCK.get();
    }
}
