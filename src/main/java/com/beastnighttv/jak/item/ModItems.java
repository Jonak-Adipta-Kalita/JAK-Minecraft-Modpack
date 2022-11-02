package com.beastnighttv.jak.item;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.item.custom.EightBallItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JAKMinecraftMod.MODID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register(
        "zircon",
        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.JAK_TAB))
    );
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register(
        "raw_zircon",
        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.JAK_TAB))
    );
    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register(
        "eight_ball",
        () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).stacksTo(1))
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
