package com.ghariel.dreaming_mod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DescriptionItem extends Item {
    private final String description;

    public DescriptionItem(String description, Properties properties) {
        super(properties);
        this.description = description;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable(description));
        super.appendHoverText(itemStack, level, components, flag);
    }
}
