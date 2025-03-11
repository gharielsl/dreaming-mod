package com.ghariel.dreaming_mod.dream.teleporter;

import com.ghariel.dreaming_mod.dream.DreamType;
import com.ghariel.dreaming_mod.util.StructureUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.common.util.ITeleporter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DreamLevelTeleporter implements ITeleporter {
    private BlockPos position;
    private final DreamType dream;

    public DreamLevelTeleporter(BlockPos position, DreamType dream) {
        this.position = position;
        this.dream = dream;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity repositionedEntity = repositionEntity.apply(true);

        if (position.getY() == -1) {
            int entityHeight = (int) Math.ceil(entity.getBoundingBox().getYsize());
            position = position.above(destinationWorld.getMaxBuildHeight() - entityHeight);
            while (destinationWorld.getBlockState(position.below(entityHeight)).isAir() &&
                    position.getY() > destinationWorld.getMinBuildHeight()) {
                position = position.below();
            }
        }

        if (dream.getStructure() != null) { // Spawn structure
            StructureTemplate template =
                    destinationWorld.getStructureManager().get(dream.getStructure()).orElse(null);

            if (template != null) {
                StructurePlaceSettings settings = new StructurePlaceSettings();
                template.placeInWorld(
                        destinationWorld, position, position, settings, destinationWorld.getRandom(), 3);
                Map<String, List<BlockPos>> markers = StructureUtil.getMarkers(template);
                if (markers.containsKey("spawn")) {
                    BlockPos spawn = markers.get("spawn").get(0);
                    position = position.offset(spawn.getX(), spawn.getY(), spawn.getZ());
                }
            } else {
                repositionedEntity.sendSystemMessage(Component.literal("null"));
            }
        }

        repositionedEntity.teleportTo(position.getX() + 0.5, position.getY(), position.getZ() + 0.5);

        if (repositionedEntity instanceof ServerPlayer player && dream.getOnDreamStart() != null) {
            dream.getOnDreamStart().accept(player);
        }

        return repositionedEntity;
    }
}
