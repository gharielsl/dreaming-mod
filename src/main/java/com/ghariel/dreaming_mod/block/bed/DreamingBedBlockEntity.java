package com.ghariel.dreaming_mod.block.bed;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.ghariel.dreaming_mod.block.ModBlockEntities.DREAMING_BED_BLOCK_ENTITY_TYPE;


public class DreamingBedBlockEntity extends BlockEntity {

    private final DyeColor color;
    private final String dreamLevel;

    public DreamingBedBlockEntity(BlockPos pos, BlockState blockState) {
        super(DREAMING_BED_BLOCK_ENTITY_TYPE.get(), pos, blockState);
        color = ((BedBlock)blockState.getBlock()).getColor();
        dreamLevel = ((DreamingBedBlock)blockState.getBlock()).getDreamLevel();
    }

    public DyeColor getColor() {
        return color;
    }

    public String getDreamLevel() {
        return dreamLevel;
    }
}
