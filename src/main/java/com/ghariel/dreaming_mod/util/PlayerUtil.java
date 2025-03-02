package com.ghariel.dreaming_mod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PlayerUtil {
    public static void randomizeInventory(ServerPlayer player) {
        Inventory inventory = player.getInventory();
        inventory.clearContent();
        Random random = new Random();

        List<Item> allItems = ForgeRegistries.ITEMS.getValues().stream().toList();

        int slotsCount = random.nextInt(4, 9);

        for (int i = 0; i < slotsCount; i++) {
            int slot;
            do {
                slot = random.nextInt(Inventory.getSelectionSize() + inventory.items.size());
            } while (!inventory.getItem(slot).isEmpty());

            Item item = allItems.get(random.nextInt(allItems.size()));
            ItemStack stack = new ItemStack(item, 1);
            int stackSize = stack.getMaxStackSize();
            int count = stackSize > 1 ? random.nextInt(1, Math.min(stackSize, 16)) : 1;
            stack.setCount(count);

            inventory.setItem(slot, stack);
        }
    }

    public static void teleportToSpawn(ServerPlayer player) {
        BlockPos pos = player.getRespawnPosition();
        MinecraftServer server = player.getServer();
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);
        if (pos == null) {
            pos = overworld.getSharedSpawnPos();
        }
        float angle = player.getRespawnAngle();
        ServerLevel respawnLevel = server.getLevel(player.getRespawnDimension());
        if (respawnLevel == null) {
            respawnLevel = overworld;
        }
        Vec3 respawnPos = pos.getCenter();
        BlockState state = respawnLevel.getBlockState(pos);
        if (state.getBlock() instanceof BedBlock bed) {
            Optional<Vec3> v = bed.getRespawnPosition(state, player.getType(), respawnLevel, pos, angle, player);
            if (v.isPresent()) {
                respawnPos = v.get();
            }
        }
        player.teleportTo(respawnLevel,
                respawnPos.x(), respawnPos.y(), respawnPos.z(), angle, 0);
    }
}
