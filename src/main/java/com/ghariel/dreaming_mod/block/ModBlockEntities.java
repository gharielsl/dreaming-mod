package com.ghariel.dreaming_mod.block;

import com.ghariel.dreaming_mod.DreamingMod;
import com.ghariel.dreaming_mod.block.bed.DreamingBedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DreamingMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DreamingBedBlockEntity>> DREAMING_BED_BLOCK_ENTITY_TYPE =
            BLOCK_ENTITIES.register("dreaming_bed_block_entity", () ->
                    BlockEntityType.Builder.of(DreamingBedBlockEntity::new,
                            ModBlocks.NO_DREAM_BED_BLOCK.get()
                    ).build(null));
}
