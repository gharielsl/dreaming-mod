package com.ghariel.dreaming_mod.block.bed;

import com.ghariel.dreaming_mod.DreamingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class CloudBed extends DreamingBedBlock {
    public CloudBed(DyeColor dyeColor) {
        super(dyeColor, "cloud", new ResourceLocation(DreamingMod.MOD_ID, "sky_essence"));
    }
}
