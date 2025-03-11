package com.ghariel.dreaming_mod.block.bed;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArchitectBed extends DreamingBedBlock {
    public ArchitectBed(DyeColor dyeColor) {
        super(dyeColor, "architect", null);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter block, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, block, tooltip, flag);
        tooltip.add(Component.literal("Makes you dream the same dream each time"));
    }
}
