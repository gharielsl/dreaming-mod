package com.ghariel.dreaming_mod.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BedPart;

public class DreamingBedBlock extends BedBlock {
    private final int dreamLevel;

    public DreamingBedBlock(DyeColor dyeColor, int dreamLevel) {
        super(dyeColor, Properties.copy(Blocks.BLACK_BED).sound(SoundType.AMETHYST));
        this.dreamLevel = dreamLevel;
        registerDefaultState(getStateDefinition().any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false));
    }

    public int getDreamLevel() {
        return dreamLevel;
    }
}
