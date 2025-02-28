package com.ghariel.dreaming_mod.renderer;

import com.ghariel.dreaming_mod.block.bed.DreamingBedBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class DreamingBedItemRenderer extends BlockEntityWithoutLevelRenderer {

    public DreamingBedItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        DreamingBedBlockEntity bed = new DreamingBedBlockEntity(BlockPos.ZERO,
                ((BlockItem) itemStack.getItem()).getBlock().defaultBlockState());

        Minecraft.getInstance().getBlockEntityRenderDispatcher()
                .renderItem(bed, poseStack, bufferSource, combinedLight, combinedOverlay);
    }
}
