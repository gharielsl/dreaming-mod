package com.ghariel.dreaming_mod.dream.teleporter;

import com.ghariel.dreaming_mod.block.bed.DreamingBedBlock;
import com.ghariel.dreaming_mod.dream.DreamType;
import com.ghariel.dreaming_mod.dream.DreamSaveData;
import com.ghariel.dreaming_mod.dream.PlayerDream;
import com.ghariel.dreaming_mod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BedBlock;

public class DreamTeleporter {

    private static final int MAX_SPAWN_DISTANCE = 100000;

    public static void teleport(ServerPlayer player, DreamSaveData saveData, BedBlock bed) {
        RandomSource random = player.getRandom();
        String level = "default";
        if (bed instanceof DreamingBedBlock dreamingBed) {
            level = dreamingBed.getDreamLevel();
        }
        DreamType[] dreamTypes = DreamType.levels.get(level);
        if (dreamTypes.length == 0) {
            return;
        }

        int dreamIndex = random.nextInt(dreamTypes.length);
        String dreamTypeId = DreamType.getDreamTypeId(level, dreamIndex);
        DreamType dream = DreamType.getDreamTypeById(dreamTypeId);
        if (dream == null) {
            return;
        }

        MinecraftServer server = player.getServer();
        ServerLevel dim = server.getLevel(dream.getDimension());
        if (dim == null) {
            return;
        }

        saveData.setPlayerDream(
                player.getStringUUID(), new PlayerDream(player, dreamTypeId));
        int x = random.nextInt(2 * MAX_SPAWN_DISTANCE + 1) - MAX_SPAWN_DISTANCE;
        int z = random.nextInt(2 * MAX_SPAWN_DISTANCE + 1) - MAX_SPAWN_DISTANCE;

        if (level.equals("architect")) {
            x = 0;
            z = 0;
        }

        player.changeDimension(dim, new DreamLevelTeleporter(new BlockPos(x, dream.getSpawnHeight(), z), dream));
    }
}
