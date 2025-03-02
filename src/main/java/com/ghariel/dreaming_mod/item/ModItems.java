package com.ghariel.dreaming_mod.item;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DreamingMod.MOD_ID);

    public static final RegistryObject<BlockItem> NO_DREAM_BED = ITEMS.register("no_dream_bed",
            () -> new NoDreamBedItem(ModBlocks.NO_DREAM_BED_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> STAR_BED = ITEMS.register("star_bed",
            () -> new NoDreamBedItem(ModBlocks.STAR_BED_BLOCK.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<BlockItem> CLOUD_BED = ITEMS.register("cloud_bed",
            () -> new NoDreamBedItem(ModBlocks.CLOUD_BED_BLOCK.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> STARDUST_INFUSED_SILK = ITEMS.register("stardust_infused_silk",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> SKY_ESSENCE = ITEMS.register("sky_essence",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
}
