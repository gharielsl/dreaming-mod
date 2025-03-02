package com.ghariel.dreaming_mod.block.bed;

import com.ghariel.dreaming_mod.DreamingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class StarBed extends DreamingBedBlock {
    public StarBed(DyeColor dyeColor) {
        super(dyeColor, "star", new ResourceLocation(DreamingMod.MOD_ID, "stardust_infused_silk"));
    }
}
