package com.ghariel.dreaming_mod.block;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.item.ModItems;
import com.ghariel.dreaming_mod.worldgen.feature.tree.CandyTreeGrower;
import com.ghariel.dreaming_mod.worldgen.feature.tree.RichTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DreamingMod.MOD_ID);

    public static final RegistryObject<Block> CAKE_BLOCK = registerBlock("cake_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> PANCAKE_BLOCK = registerBlock("pancake_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> CANDY_TREE_SAPLING = registerBlock("candy_tree_sapling",
            () -> new CandyTreeSapling(new CandyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> RICH_TREE_SAPLING = registerBlock("rich_tree_sapling",
            () -> new RichTreeSapling(new RichTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> CHOCOLATE_BUSH_BLOCK = registerBlock("chocolate_bush",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject);
        return registryObject;
    }

    private static <T extends Block> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
