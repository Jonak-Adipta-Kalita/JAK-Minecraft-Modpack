package com.beastnighttv.jak;

import com.beastnighttv.jak.block.ModBlocks;
import com.beastnighttv.jak.block.entity.ModBlockEntities;
import com.beastnighttv.jak.entity.ModEntityTypes;
import com.beastnighttv.jak.entity.client.ChomperRenderer;
import com.beastnighttv.jak.item.ModItems;
import com.beastnighttv.jak.networking.ModMessages;
import com.beastnighttv.jak.painting.ModPaintings;
import com.beastnighttv.jak.recipe.ModRecipes;
import com.beastnighttv.jak.screen.GemInfusingStationScreen;
import com.beastnighttv.jak.screen.ModMenuTypes;
import com.beastnighttv.jak.villager.ModVillagers;
import com.beastnighttv.jak.world.feature.ModConfiguredFeatures;
import com.beastnighttv.jak.world.feature.ModPlacedFeatures;
import com.beastnighttv.jak.fluid.ModFluidTypes;
import com.beastnighttv.jak.fluid.ModFluids;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(JAKMinecraftMod.MODID)

public class JAKMinecraftMod {
    public static final String MODID = "jak";

    public JAKMinecraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEntityTypes.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
            ModVillagers.registerPOIs();
        });
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(),
                    RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(),
                    RenderType.translucent());

            MenuScreens.register(ModMenuTypes.GEM_INFUSING_STATION_MENU.get(),
                    GemInfusingStationScreen::new);

            EntityRenderers.register(ModEntityTypes.CHOMPER.get(), ChomperRenderer::new);
        }
    }
}
