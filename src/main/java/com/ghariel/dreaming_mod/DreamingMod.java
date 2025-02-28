package com.ghariel.dreaming_mod;

import com.ghariel.dreaming_mod.block.ModBlockEntities;
import com.ghariel.dreaming_mod.block.ModBlocks;
import com.ghariel.dreaming_mod.item.ModCreativeTabs;
import com.ghariel.dreaming_mod.item.ModItems;
import com.ghariel.dreaming_mod.renderer.DreamingBedRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DreamingMod.MOD_ID)
@Mod.EventBusSubscriber(modid = DreamingMod.MOD_ID)
public class DreamingMod {
    public static final String MOD_ID = "dreaming_mod";

    public DreamingMod(FMLJavaModLoadingContext context) {
        ModBlocks.BLOCKS.register(context.getModEventBus());
        ModBlockEntities.BLOCK_ENTITIES.register(context.getModEventBus());
        ModItems.ITEMS.register(context.getModEventBus());
        ModCreativeTabs.CREATIVE_MODE_TABS.register(context.getModEventBus());
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(ModBlockEntities.DREAMING_BED_BLOCK_ENTITY_TYPE.get(), DreamingBedRenderer::new);
        }
    }
}
