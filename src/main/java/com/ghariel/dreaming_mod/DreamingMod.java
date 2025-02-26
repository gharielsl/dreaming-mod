package com.ghariel.dreaming_mod;

import com.ghariel.dreaming_mod.block.ModBlocks;
import com.ghariel.dreaming_mod.item.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DreamingMod.MOD_ID)
@Mod.EventBusSubscriber(modid = DreamingMod.MOD_ID)
public class DreamingMod {
    public static final String MOD_ID = "dreaming_mod";

    public DreamingMod(FMLJavaModLoadingContext context) {
        ModBlocks.BLOCKS.register(context.getModEventBus());
        ModItems.ITEMS.register(context.getModEventBus());
    }
}
