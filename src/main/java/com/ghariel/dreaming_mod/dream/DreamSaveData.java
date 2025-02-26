package com.ghariel.dreaming_mod.dream;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DreamSaveData extends SavedData {
    private final Map<String, PlayerDream> playerDreams = new HashMap<>();

    public static DreamSaveData load(CompoundTag tag) {
        DreamSaveData data = new DreamSaveData();
        CompoundTag dreamsTag = tag.getCompound("playerDreams");
        for (String key : dreamsTag.getAllKeys()) {
            CompoundTag dreamTag = dreamsTag.getCompound(key);
            PlayerDream playerDream = PlayerDream.fromNBT(dreamTag);
            data.playerDreams.put(key, playerDream);
        }
        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag) {
        CompoundTag dreamsTag = new CompoundTag();
        for (Map.Entry<String, PlayerDream> entry : playerDreams.entrySet()) {
            CompoundTag dreamTag = entry.getValue().toNBT();
            dreamsTag.put(entry.getKey(), dreamTag);
        }
        tag.put("playerDreams", dreamsTag);
        return tag;
    }

    public void setPlayerDream(String playerId, PlayerDream dream) {
        playerDreams.put(playerId, dream);
        setDirty();
    }

    public PlayerDream getPlayerDream(String playerId) {
        return this.playerDreams.get(playerId);
    }

    public void setIsInDream(String playerId, boolean isInDream) {
        PlayerDream dream = playerDreams.get(playerId);
        if (dream == null) {
            return;
        }
        dream.setInDream(isInDream);
        setDirty();
    }
}
