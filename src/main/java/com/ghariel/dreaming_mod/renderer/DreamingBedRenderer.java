package com.ghariel.dreaming_mod.renderer;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.block.ModBlockEntities;
import com.ghariel.dreaming_mod.block.bed.DreamingBedBlock;
import com.ghariel.dreaming_mod.block.bed.DreamingBedBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class DreamingBedRenderer implements BlockEntityRenderer<DreamingBedBlockEntity> {
    private final ModelPart headRoot;
    private final ModelPart footRoot;

    public DreamingBedRenderer(BlockEntityRendererProvider.Context context) {
        this.headRoot = context.bakeLayer(ModelLayers.BED_HEAD);
        this.footRoot = context.bakeLayer(ModelLayers.BED_FOOT);
    }

    @Override
    public void render(DreamingBedBlockEntity bed, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Material material = new Material(Sheets.BED_SHEET, new ResourceLocation(DreamingMod.MOD_ID, "entity/bed/" + bed.getDreamLevel()));
        Level level = bed.getLevel();
        if (level != null) {
            BlockState blockstate = bed.getBlockState();
            DoubleBlockCombiner.NeighborCombineResult<? extends DreamingBedBlockEntity> combineResult =
                    DoubleBlockCombiner.combineWithNeigbour(
                            ModBlockEntities.DREAMING_BED_BLOCK_ENTITY_TYPE.get(),
                            DreamingBedBlock::getBlockType,
                            DreamingBedBlock::getConnectedDirection,
                            HorizontalDirectionalBlock.FACING,
                            blockstate, level,
                            bed.getBlockPos(),
                            (levelAccessor, pos) -> false);
            int i = combineResult.apply(new BrightnessCombiner<>()).get(combinedLight);
            this.renderPiece(poseStack, buffer, blockstate.getValue(DreamingBedBlock.PART) ==
                    BedPart.HEAD ? this.headRoot : this.footRoot, blockstate.getValue(HorizontalDirectionalBlock.FACING), material, i, combinedOverlay, false);
        } else {
            this.renderPiece(poseStack, buffer, this.headRoot, Direction.SOUTH, material, combinedLight, combinedOverlay, false);
            this.renderPiece(poseStack, buffer, this.footRoot, Direction.SOUTH, material, combinedLight, combinedOverlay, true);
        }
    }

    private void renderPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, ModelPart model, Direction direction, Material material, int packedLight, int packedOverlay, boolean foot) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.5625, foot ? -1.0 : 0.0);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
        poseStack.translate(-0.5, -0.5, -0.5);
        VertexConsumer vertexconsumer = material.buffer(multiBufferSource, RenderType::entitySolid);
        model.render(poseStack, vertexconsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }
}
