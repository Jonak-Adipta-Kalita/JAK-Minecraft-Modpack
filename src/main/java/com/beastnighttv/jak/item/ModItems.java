package com.beastnighttv.jak.item;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.ModCreativeModeTab;
import com.beastnighttv.jak.block.ModBlocks;
import com.beastnighttv.jak.fluid.ModFluids;
import com.beastnighttv.jak.item.custom.EightBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<EightBallItem> EIGHT_BALL = ITEMS.register(
        "eight_ball",
        () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).stacksTo(1))
    );
    public static final RegistryObject<ItemNameBlockItem> BLUEBERRY_SEEDS = ITEMS.register(
        "blueberry_seeds",
        () -> new ItemNameBlockItem(ModBlocks.BLUEBERYY_CROP.get(), new Item.Properties().tab(ModCreativeModeTab.JAK_TAB))
    );
    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register(
        "blueberry",
        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build()))
    );
    public static final RegistryObject<BucketItem> SOAP_WATER_BUCKET = ITEMS.register(
        "soap_water_bucket",
        () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER, new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).craftRemainder(Items.BUCKET).stacksTo(1))
    );
    public static final RegistryObject<SwordItem> ZIRCON_SWORD = ITEMS.register(
        "zircon_sword",
        () -> new SwordItem(Tiers.DIAMOND, 10, 1.6f, new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).stacksTo(1))
    );
    public static final RegistryObject<PickaxeItem> ZIRCON_PICKAXE = ITEMS.register(
        "zircon_pickaxe",
        () -> new PickaxeItem(Tiers.DIAMOND, 10, 1.6f, new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).stacksTo(1))
    );
	public static final RegistryObject<AxeItem> ZIRCON_AXE = ITEMS.register(
        "zircon_axe",
        () -> new AxeItem(Tiers.DIAMOND, 10, 1.6f, new Item.Properties().tab(ModCreativeModeTab.JAK_TAB).stacksTo(1))
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
