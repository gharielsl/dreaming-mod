package com.ghariel.dreaming_mod.dream;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.dream.teleporter.DreamTeleporter;
import com.ghariel.dreaming_mod.util.LevelLocation;
import com.ghariel.dreaming_mod.util.NBTUtil;
import com.ghariel.dreaming_mod.util.PlayerUtil;
import com.ghariel.dreaming_mod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static com.ghariel.dreaming_mod.util.PlayerUtil.randomizeInventory;

@Mod.EventBusSubscriber(modid = DreamingMod.MOD_ID)
public class DreamEvents {

    private static DreamSaveData saveData;

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (ModDimensions.isDream(player.level().dimension())) {
                event.setResult(Player.BedSleepingProblem.OTHER_PROBLEM);
                player.kill();
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (event.updateLevel()) {
                return;
            }
            BlockPos bedPos = player.getRespawnPosition();
            BedBlock bed = null;
            if (bedPos != null) {
                Level currentLevel = player.level();
                BlockState state = currentLevel.getBlockState(bedPos);
                if (state.getBlock() instanceof BedBlock bedBlock) {
                    currentLevel.setBlock(bedPos, state.setValue(BedBlock.OCCUPIED, false), 3);
                    bed = bedBlock;
                }
            }
            DreamTeleporter.teleport(player, saveData, bed);
        }
    }

    @SubscribeEvent
    public static void onPlayerEnterPortal(EntityTravelToDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ResourceKey<Level> from = player.level().dimension();
            ResourceKey<Level> to = event.getDimension();
            if (ModDimensions.isDream(to)) {
                PlayerDream playerDream = saveData.getPlayerDream(player.getStringUUID());
                if (playerDream == null) {
                    event.setCanceled(true);
                    return;
                }
                DreamType dreamType = DreamType.getDreamTypeById(playerDream.getDreamTypeId());
                if (dreamType == null) {
                    event.setCanceled(true);
                    return;
                }
                if (dreamType.getGameType() != null) {
                    player.setGameMode(dreamType.getGameType());
                }
                saveData.setIsInDream(player.getStringUUID(), true);
                randomizeInventory(player);
                player.getInventory().setItem(0, new ItemStack(Items.RED_BED, 1));
            } else if (ModDimensions.isDream(from)) {
                ResourceKey<Level> respawnLevel = player.getRespawnDimension();
                if (to != respawnLevel) {
                    event.setCanceled(true);
                }
            }
        }
    }

    private static void handlePlayerWakeUp(ServerPlayer player) {
        PlayerDream dream = saveData.getPlayerDream(player.getStringUUID());
        if (dream == null) {
            return;
        }
        if (dream.isInDream()) {
            player.getInventory().load(dream.getInventory());
            player.setGameMode(dream.getGameType());
            player.setExperiencePoints(dream.getExperience());
            player.extinguishFire();
            saveData.setIsInDream(player.getStringUUID(), false);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            handlePlayerWakeUp(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerDied(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerDream dream = saveData.getPlayerDream(player.getStringUUID());
            if (dream != null) {
                if (dream.isInDream()) {
                    for (ItemStack item : player.getInventory().items) {
                        if (item != null) {
                            boolean keep = false;
                            CompoundTag tag = item.getTag();
                            if (tag != null) {
                                ListTag lores = tag
                                        .getCompound("display").getList("Lore", NBTUtil.TYPE_STRING);
                                for (int i = 0; i < lores.size() && !keep; i++) {
                                    if (lores.getString(i).equals("{\"italic\":false,\"translate\":\"lore.dreaming_mod.keep\"}")) {
                                        lores.remove(i);
                                        keep = true;
                                    }
                                }
                                if (keep) {
                                    item.setTag(tag);
                                }
                            }
                            if (keep) {
                                LevelLocation location = PlayerUtil.getPlayerSpawn(player);
                                location.level().addFreshEntity(
                                        new ItemEntity(
                                                location.level(),
                                                location.pos().x(),
                                                location.pos().y(),
                                                location.pos().z(),
                                                item
                                        ));
                            }
                        }
                    }
                    if (player.level().getLevelData().isHardcore()) {
                        event.setCanceled(true);
                        player.setHealth(player.getMaxHealth());
                        player.getInventory().clearContent();
                        PlayerUtil.teleportToSpawn(player);
                        handlePlayerWakeUp(player);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        saveData = null;
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (saveData == null) {
            if (event.player instanceof ServerPlayer player) {
                MinecraftServer server = player.getServer();
                ServerLevel world = server.getLevel(Level.OVERWORLD);
                if (world == null) {
                    world = server.getAllLevels().iterator().next();
                }
                saveData = world.getDataStorage().computeIfAbsent(
                        DreamSaveData::load,
                        DreamSaveData::new,
                        "player_dreams"
                );
            }
        }
        if (saveData == null) {
            return;
        }
        PlayerDream dream = saveData.getPlayerDream(event.player.getStringUUID());
        if (dream != null) {
            if (dream.isInDream()) {
                dream.update(MinecraftServer.MS_PER_TICK / 1000.0);
//                if (dream.getTimeInDream() > 120) {
//                    event.player.kill();
//                }
            }
        }
    }
}
