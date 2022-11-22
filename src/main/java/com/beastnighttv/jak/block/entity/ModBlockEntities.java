package com.beastnighttv.jak.block.entity;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
        ForgeRegistries.BLOCK_ENTITY_TYPES,
        JAKMinecraftMod.MODID
    );

    public static final RegistryObject<BlockEntityType<GemInfusingStationBlockEntity>> GEM_INFUSING_STATION = BLOCK_ENTITIES.register(
        "gem_infusing_station",
        () -> BlockEntityType.Builder.of(
            GemInfusingStationBlockEntity::new,
            ModBlocks.GEM_INFUSING_STATION.get()
        )
        .build(null)
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
