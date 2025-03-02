package com.ghariel.dreaming_mod.worldgen.dimension;

import com.ghariel.dreaming_mod.DreamingMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class ModDimensions {
    public static final ResourceKey<LevelStem> DREAM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DreamingMod.MOD_ID, "dream"));
    public static final ResourceKey<Level> DREAM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DreamingMod.MOD_ID, "dream"));
    public static final ResourceKey<DimensionType> DREAM_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DreamingMod.MOD_ID, "dream_type"));

    public static final ResourceKey<LevelStem> NIGHTMARE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(DreamingMod.MOD_ID, "nightmare"));
    public static final ResourceKey<Level> NIGHTMARE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DreamingMod.MOD_ID, "nightmare"));
    public static final ResourceKey<DimensionType> NIGHTMARE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(DreamingMod.MOD_ID, "nightmare_type"));

    public static boolean isDream(ResourceKey<Level> dim) {
        return dim == DREAM_LEVEL_KEY || dim == NIGHTMARE_LEVEL_KEY;
    }
}
