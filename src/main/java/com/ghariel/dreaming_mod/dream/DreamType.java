package com.ghariel.dreaming_mod.dream;

import com.ghariel.dreaming_mod.DreamingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import javax.annotation.Nullable;

public class DreamType {

    public static DreamType[] level0 = new DreamType[]{
            new DreamType(null, null, -1),
            new DreamType(null, GameType.CREATIVE, -1),
            new DreamType("dream_level_00_var_01", null, 128)
    };

    public static DreamType[][] levels = new DreamType[][]{ level0 };

    private final ResourceLocation structure;
    private final GameType gameType;
    private final int spawnHeight;

    public DreamType(@Nullable String structure, @Nullable GameType gameType, int spawnHeight) {
        if (structure != null) {
            this.structure = new ResourceLocation(DreamingMod.MOD_ID, structure);
        } else {
            this.structure = null;
        }
        this.gameType = gameType;
        this.spawnHeight = spawnHeight;
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

    public static DreamType getDreamTypeById(String id) {
        String[] indices = id.replace("L", "").split("i");
        try {
            return levels[Integer.parseInt(indices[0])][Integer.parseInt(indices[1])];
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDreamTypeId(int level, int index) {
        return "L" + level + "i" + index;
    }
}
