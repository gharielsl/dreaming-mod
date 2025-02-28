package com.ghariel.dreaming_mod.item;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DreamingMod.MOD_ID);

    public static final RegistryObject<BlockItem> NO_DREAM_BED = ITEMS.register("no_dream_bed",
            () -> new NoDreamBedItem(ModBlocks.NO_DREAM_BED_BLOCK.get(), new Item.Properties()));
}
