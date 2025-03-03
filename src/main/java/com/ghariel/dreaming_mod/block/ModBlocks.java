package com.ghariel.dreaming_mod.block;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.block.bed.CloudBed;
import com.ghariel.dreaming_mod.block.bed.NoDreamBed;
import com.ghariel.dreaming_mod.block.bed.StarBed;
import com.ghariel.dreaming_mod.item.ModItems;
import com.ghariel.dreaming_mod.worldgen.feature.tree.CandyJungleTreeGrower;
import com.ghariel.dreaming_mod.worldgen.feature.tree.CandyTreeGrower;
import com.ghariel.dreaming_mod.worldgen.feature.tree.RichTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

    public static final RegistryObject<Block> CANDY_TREE_SAPLING = registerRareBlock("candy_tree_sapling",
            () -> new CandyTreeSapling(new CandyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), Rarity.UNCOMMON);

    public static final RegistryObject<Block> RICH_TREE_SAPLING = registerRareBlock("rich_tree_sapling",
            () -> new RichTreeSapling(new RichTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), Rarity.EPIC);

    public static final RegistryObject<Block> CANDY_JUNGLE_TREE_SAPLING = registerBlock("candy_jungle_tree_sapling",
            () -> new CandyJungleTreeSapling(new CandyJungleTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> CHOCOLATE_BUSH_BLOCK = registerBlock("chocolate_bush",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final RegistryObject<RotatedPillarBlock> CANDY_TREE_LOG = registerBlock("candy_tree_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> CANDY_TREE_PLANKS = registerBlock("candy_tree_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> CANDY_BLOCK = registerBlock("candy_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> CHOCOLATE_BLOCK = registerBlock("chocolate_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> NO_DREAM_BED_BLOCK =
            BLOCKS.register("no_dream_bed", () -> new NoDreamBed(DyeColor.WHITE));

    public static final RegistryObject<Block> STAR_BED_BLOCK =
            BLOCKS.register("star_bed", () -> new StarBed(DyeColor.WHITE));

    public static final RegistryObject<Block> CLOUD_BED_BLOCK =
            BLOCKS.register("cloud_bed", () -> new CloudBed(DyeColor.WHITE));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject, new Item.Properties());
        return registryObject;
    }

    private static <T extends Block> RegistryObject<T> registerRareBlock(String name, Supplier<T> block, Rarity rarity) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject, new Item.Properties().rarity(rarity));
        return registryObject;
    }

    private static <T extends Block> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<T> block, Item.Properties properties) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
}
