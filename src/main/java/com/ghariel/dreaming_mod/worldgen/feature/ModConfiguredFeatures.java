package com.ghariel.dreaming_mod.worldgen.feature;

import com.ghariel.dreaming_mod.DreamingMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModConfiguredFeatures {

    public static ResourceKey<ConfiguredFeature<?, ?>> CANDY_TREE = registerKey("candy_tree");
    public static ResourceKey<ConfiguredFeature<?, ?>> RICH_TREE = registerKey("rich_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(DreamingMod.MOD_ID, name));
    }
}
