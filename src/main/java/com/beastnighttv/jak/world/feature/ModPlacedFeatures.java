package com.beastnighttv.jak.world.feature;

import java.util.List;
import com.beastnighttv.jak.JAKMinecraftMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(
        Registry.PLACED_FEATURE_REGISTRY,
        JAKMinecraftMod.MODID
    );

    public static final RegistryObject<PlacedFeature> OVERWORLD_ZIRCON_ORE_PLACED = PLACED_FEATURES.register(
        "overworld_zircon_ore_placed",
        () -> new PlacedFeature(
            ModConfiguredFeatures.OVERWORLD_ZIRCON_ORE.getHolder().get(),
            commonOrePlacement(
                7,
                HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)
                )
            )
        )
    );
    public static final RegistryObject<PlacedFeature> END_ZIRCON_ORE_PLACED = PLACED_FEATURES.register(
        "end_zircon_ore_placed",
        () -> new PlacedFeature(
            ModConfiguredFeatures.END_ZIRCON_ORE.getHolder().get(),
            commonOrePlacement(
                7,
                HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)
                )
            )
        )
    );
    public static final RegistryObject<PlacedFeature> NETHER_ZIRCON_ORE_PLACED = PLACED_FEATURES.register(
        "nether_zircon_ore_placed",
        () -> new PlacedFeature(
            ModConfiguredFeatures.NETHER_ZIRCON_ORE.getHolder().get(),
            commonOrePlacement(
                7,
                HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)
                )
            )
        )
    );

    public static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier2) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome());
     }
  
    public static List<PlacementModifier> commonOrePlacement(int veinsPerChunk, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(veinsPerChunk), placementModifier);
    }
  
    public static List<PlacementModifier> rareOrePlacement(int veinsPerChunk, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(veinsPerChunk), placementModifier);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
