package com.ghariel.dreaming_mod.block.bed;

import com.ghariel.dreaming_mod.DreamingMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CloudBed extends DreamingBedBlock {
    public CloudBed(DyeColor dyeColor) {
        super(dyeColor, "cloud", new ResourceLocation(DreamingMod.MOD_ID, "sky_essence"));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter block, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, block, tooltip, flag);
        tooltip.add(Component.literal("Makes dreams last two times as long"));
    }
}
