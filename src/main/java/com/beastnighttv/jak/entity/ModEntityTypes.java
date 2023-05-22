package com.beastnighttv.jak.entity;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.entity.custom.ChomperEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JAKMinecraftMod.MODID);

    public static final RegistryObject<EntityType<ChomperEntity>> CHOMPER = ENTITY_TYPES.register(
            "chomper",
            () -> EntityType.Builder.of(ChomperEntity::new, MobCategory.MONSTER).sized(0.4f, 1.5f)
                    .build(new ResourceLocation(JAKMinecraftMod.MODID, "chomper").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
