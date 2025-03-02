package com.ghariel.dreaming_mod.block.bed;

import com.ghariel.dreaming_mod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DreamingBedBlock extends BedBlock {
    private final String dreamLevel;
    private final ResourceLocation drops;

    public DreamingBedBlock(DyeColor dyeColor, String dreamLevel, ResourceLocation drops) {
        super(dyeColor, Properties.copy(Blocks.BLACK_BED).sound(SoundType.AMETHYST));
        this.dreamLevel = dreamLevel;
        this.drops = drops;
        registerDefaultState(getStateDefinition().any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false));
    }

    public String getDreamLevel() {
        return dreamLevel;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new DreamingBedBlockEntity(pos, blockState);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if (this instanceof NoDreamBed) {
            return super.getLightEmission(state, level, pos);
        }
        return 4;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState blockState){
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction dir = getConnectedDirection(state).getOpposite();
        switch (dir) {
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
                return EAST_SHAPE;
            }
        }
    }

    private void handleBlockDestroyed(BlockPos pos, Level level) {
        Item drop = null;
        if (drops != null) {
            drop = ForgeRegistries.ITEMS.getValue(drops);
        }
        if (ModDimensions.isDream(level.dimension()) && drop != null) {
            ItemEntity entity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(drop));
            level.addFreshEntity(entity);
        } else {
            ItemEntity entity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Item.BY_BLOCK.get(this)));
            level.addFreshEntity(entity);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid)) {
            handleBlockDestroyed(pos, level);
            return true;
        }
        return false;
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(state, level, pos, explosion);
        handleBlockDestroyed(pos, level);
    }
}
