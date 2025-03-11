package com.ghariel.dreaming_mod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DescriptionBlockItem extends BlockItem {
    private final String description;

    public DescriptionBlockItem(String description, Block block, Properties properties) {
        super(block, properties);
        this.description = description;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable(description));
        super.appendHoverText(itemStack, level, components, flag);
    }
}
