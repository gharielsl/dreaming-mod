package com.ghariel.dreaming_mod.util;

import com.sk89q.jnbt.NBTConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureUtil {
    public static Map<String, List<BlockPos>> getMarkers(StructureTemplate template) {
        CompoundTag templateNBT = template.save(new CompoundTag());
        ListTag entities = templateNBT.getList("entities", NBTConstants.TYPE_COMPOUND);
        Map<String, List<BlockPos>> markers = new HashMap<>();

        for (int i = 0; i < entities.size(); i++) {
            CompoundTag entityTag = entities.getCompound(i);
            CompoundTag entityNbt = entityTag.getCompound("nbt");
            if (entityNbt.getString("id").equals("minecraft:armor_stand")) {
                ListTag pos = entityTag.getList("pos", NBTConstants.TYPE_DOUBLE);
                ListTag tags = entityNbt.getList("Tags", NBTConstants.TYPE_STRING);
                BlockPos blockPos = new BlockPos(
                        (int) pos.getDouble(0),
                        (int) pos.getDouble(1),
                        (int) pos.getDouble(2));
                String key = "default";
                if (!tags.isEmpty()) {
                    key = tags.getString(0);
                }
                if (!markers.containsKey(key)) {
                    markers.put(key, new ArrayList<>());
                }
                markers.get(key).add(blockPos);
            }
        }

        return markers;
    }
}
