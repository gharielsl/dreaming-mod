package com.ghariel.dreaming_mod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class NBTUtil {
    public static final int TYPE_END = 0;
    public static final int TYPE_BYTE = 1;
    public static final int TYPE_SHORT = 2;
    public static final int TYPE_INT = 3;
    public static final int TYPE_LONG = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_BYTE_ARRAY = 7;
    public static final int TYPE_STRING = 8;
    public static final int TYPE_LIST = 9;
    public static final int TYPE_COMPOUND = 10;
    public static final int TYPE_INT_ARRAY = 11;
    public static final int TYPE_LONG_ARRAY = 12;

    public static CompoundTag saveEffects(Player player) {
        CompoundTag tag = new CompoundTag();
        ListTag effectsList = new ListTag();

        for (MobEffectInstance effect : player.getActiveEffects()) {
            CompoundTag effectTag = new CompoundTag();
            effect.save(effectTag);
            effectsList.add(effectTag);
        }

        tag.put("ActiveEffects", effectsList);
        return tag;
    }

    public static void loadEffects(Player player, CompoundTag tag) {
        if (tag.contains("ActiveEffects", TYPE_LIST)) {
            ListTag effectsList = tag.getList("ActiveEffects", TYPE_COMPOUND);
            for (int i = 0; i < effectsList.size(); i++) {
                CompoundTag effectTag = effectsList.getCompound(i);
                MobEffectInstance effect = MobEffectInstance.load(effectTag);
                if (effect != null) {
                    player.addEffect(effect);
                }
            }
        }
    }
}
