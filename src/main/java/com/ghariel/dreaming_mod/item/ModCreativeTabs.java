package com.ghariel.dreaming_mod.item;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DreamingMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("dreaming_mod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STARDUST_INFUSED_SILK.get()))
                    .title(Component.translatable("creativetab.dreaming_mod.dreaming_mod_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.STARDUST_INFUSED_SILK.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("dreaming_mod_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STAR_BED.get()))
                    .withTabsBefore(ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.dreaming_mod.dreaming_mod_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CAKE_BLOCK.get());
                        output.accept(ModBlocks.PANCAKE_BLOCK.get());
                        output.accept(ModBlocks.CHOCOLATE_BLOCK.get());
                        output.accept(ModBlocks.CANDY_BLOCK.get());
                        output.accept(ModBlocks.CANDY_TREE_SAPLING.get());
                        output.accept(ModBlocks.RICH_TREE_SAPLING.get());
                        output.accept(ModBlocks.CANDY_JUNGLE_TREE_SAPLING.get());
                        output.accept(ModBlocks.CHOCOLATE_BUSH_BLOCK.get());
                        output.accept(ModBlocks.NO_DREAM_BED_BLOCK.get());
                        output.accept(ModBlocks.STAR_BED_BLOCK.get());

                    }).build());
}