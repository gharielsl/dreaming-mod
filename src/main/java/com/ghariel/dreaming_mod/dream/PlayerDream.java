package com.ghariel.dreaming_mod.dream;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

public class PlayerDream {
    private ListTag inventory;
    private final int experience;
    private final GameType gameType;
    private double timeInDream = 0;
    private boolean isInDream;
    private final String dreamTypeId;

    public PlayerDream(ServerPlayer player, String dreamTypeId) {
        inventory = new ListTag();
        inventory = player.getInventory().save(inventory);
        experience = player.totalExperience;
        gameType = player.gameMode.getGameModeForPlayer();
        this.dreamTypeId = dreamTypeId;
        this.isInDream = false;
    }

    public PlayerDream(ListTag inventory, GameType gameType, int experience, double timeInDream, boolean isInDream, String dreamTypeId) {
        this.inventory = inventory;
        this.gameType = gameType;
        this.experience = experience;
        this.timeInDream = timeInDream;
        this.isInDream = isInDream;
        this.dreamTypeId = dreamTypeId;
    }

    public ListTag getInventory() {
        return inventory;
    }

    public int getExperience() {
        return experience;
    }

    public GameType getGameType() {
        return gameType;
    }

    public double getTimeInDream() {
        return timeInDream;
    }

    public void update(double delta) {
        timeInDream += delta;
    }

    public boolean isInDream() {
        return isInDream;
    }

    public String getDreamTypeId() {
        return dreamTypeId;
    }

    public void setInDream(boolean inDream) {
        isInDream = inDream;
    }

    public static PlayerDream fromNBT(CompoundTag tag) {
        ListTag inventory = tag.getList("inventory", Tag.TAG_COMPOUND);
        int experience = tag.getInt("experience");
        GameType gameType = GameType.byId(tag.getInt("gameType"));
        double timeInDream = tag.getDouble("timeInDream");
        boolean isInDream = tag.getBoolean("isInDream");
        String dreamTypeId = tag.getString("dreamTypeId");
        return new PlayerDream(inventory, gameType, experience, timeInDream, isInDream, dreamTypeId);
    }

    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("inventory", inventory);
        tag.putInt("experience", experience);
        tag.putInt("gameType", gameType.getId());
        tag.putDouble("timeInDream", timeInDream);
        tag.putBoolean("isInDream", isInDream);
        tag.putString("dreamTypeId", dreamTypeId);
        return tag;
    }
}
