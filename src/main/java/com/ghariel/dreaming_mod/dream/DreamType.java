package com.ghariel.dreaming_mod.dream;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.worldgen.dimension.ModDimensions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;

public class DreamType {

    public static DreamType[] noDream = new DreamType[]{};

    public static DreamType[] defaultLevel = new DreamType[]{
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140, null),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1,140, null),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1,140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, GameType.CREATIVE, -1,140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, "dream_default_01", null, 128,130, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, "dream_default_02", GameType.ADVENTURE, 128,10000, null)
    };

    public static DreamType[] starLevel = new DreamType[]{
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140, null),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1,140, null),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1,140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, GameType.CREATIVE, -1,140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, "dream_star_01", null, 128,140, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, "dream_star_02", GameType.ADVENTURE, 128,10000, null)
    };

    public static DreamType[] cloudLevel = new DreamType[]{
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140*2, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140*2, null),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1,140*2, null),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1,140*2, null),
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, GameType.CREATIVE, -1,140*2, null)
    };

    public static DreamType[] architectBed = new DreamType[]{
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, GameType.CREATIVE, -1, 140*2, null)
    };

    public static DreamType[] pirateBed = new DreamType[]{
            new DreamType(ModDimensions.DREAM_LEVEL_KEY, null, null, -1, 140*2, (player) -> {
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 100000, 0));
            }),
            new DreamType(ModDimensions.NIGHTMARE_LEVEL_KEY, null, null, -1, 140*2, (player) -> {
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 100000, 0));
            })
    };

    public static Map<String, DreamType[]> levels = Map.of(
            "no_dream", noDream,
            "default", defaultLevel,
            "star", starLevel,
            "cloud", cloudLevel,
            "architect", architectBed,
            "pirate", pirateBed
    );

    private final ResourceKey<Level> dimension;
    private final ResourceLocation structure;
    private final GameType gameType;
    private final int spawnHeight;
    private final int defaultTime;
    private final Consumer<ServerPlayer> onDreamStart;

    public DreamType(ResourceKey<Level> dimension, @Nullable String structure, @Nullable GameType gameType, int spawnHeight, int defaultTime, @Nullable Consumer<ServerPlayer> onDreamStart) {
        this.dimension = dimension;
        if (structure != null) {
            this.structure = new ResourceLocation(DreamingMod.MOD_ID, structure);
        } else {
            this.structure = null;
        }
        this.gameType = gameType;
        this.spawnHeight = spawnHeight;
        this.defaultTime = defaultTime;
        this.onDreamStart = onDreamStart;
    }

    public ResourceKey<Level> getDimension() {
        return dimension;
    }

    public ResourceLocation getStructure() {
        return structure;
    }

    public GameType getGameType() {
        return gameType;
    }

    public int getSpawnHeight() {
        return spawnHeight;
    }

    public int getDefaultTime() {
        return defaultTime;
    }

    public static DreamType getDreamTypeById(String id) {
        String[] indices = id.replace("[L]", "").split("\\[i]");
        try {
            return levels.get(indices[0])[Integer.parseInt(indices[1])];
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDreamTypeId(String level, int index) {
        return "[L]" + level + "[i]" + index;
    }

    public Consumer<ServerPlayer> getOnDreamStart() {
        return onDreamStart;
    }
}
