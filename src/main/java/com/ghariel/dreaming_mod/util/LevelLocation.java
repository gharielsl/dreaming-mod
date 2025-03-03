package com.ghariel.dreaming_mod.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public record LevelLocation(ServerLevel level, Vec3 pos) {
}
