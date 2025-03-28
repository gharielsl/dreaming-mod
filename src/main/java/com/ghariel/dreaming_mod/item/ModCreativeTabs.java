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

    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("dreaming_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STAR_BED.get()))
                    .title(Component.translatable("creativetab.dreaming_mod.dreaming_mod_tab"))
                    .displayItems((itemDisplayParameters, output) -> {output.accept(ModItems.STARDUST_INFUSED_SILK.get());
                        output.accept(ModItems.SKY_ESSENCE.get());
                        output.accept(ModItems.VOLCANIC_ASH_THREAD.get());
                        output.accept(ModItems.AETHER_VINE.get());
                        output.accept(ModBlocks.CAKE_BLOCK.get());
                        output.accept(ModBlocks.PANCAKE_BLOCK.get());
                        output.accept(ModBlocks.CHOCOLATE_BLOCK.get());
                        output.accept(ModBlocks.CANDY_TREE_LOG.get());
                        output.accept(ModBlocks.CANDY_TREE_PLANKS.get());
                        output.accept(ModBlocks.CANDY_TREE_SLAB.get());
                        output.accept(ModBlocks.CANDY_TREE_STAIRS.get());
                        output.accept(ModBlocks.CANDY_BLOCK.get());
                        output.accept(ModBlocks.CANDY_TREE_SAPLING.get());
                        output.accept(ModBlocks.RICH_TREE_SAPLING.get());
                        output.accept(ModBlocks.CANDY_JUNGLE_TREE_SAPLING.get());
                        output.accept(ModBlocks.CHOCOLATE_BUSH_BLOCK.get());
                        output.accept(ModBlocks.NO_DREAM_BED_BLOCK.get());
                        output.accept(ModBlocks.STAR_BED_BLOCK.get());
                        output.accept(ModBlocks.CLOUD_BED_BLOCK.get());
                        output.accept(ModBlocks.ARCHITECT_BED_BLOCK.get());
                        output.accept(ModBlocks.PIRATE_BED_BLOCK.get());

                    }).build());
}