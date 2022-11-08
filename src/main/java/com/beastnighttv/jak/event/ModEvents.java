package com.beastnighttv.jak.event;

import java.util.List;
import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.item.ModItems;
import com.beastnighttv.jak.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JAKMinecraftMod.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack eightBallStack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);

            trades.get(1).add((trader, rand) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 2),
                eightBallStack,
                12,
                8,
                0.2f
            ));
        }

        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack blueBerryForEmeraldStack = new ItemStack(Items.EMERALD, 1);
            ItemStack blueBerryStack = new ItemStack(ModItems.BLUEBERRY.get(), 15);

            trades.get(1).add((trader, rand) -> new MerchantOffer(
                new ItemStack(ModItems.BLUEBERRY.get(), 20),
                blueBerryForEmeraldStack,
                12,
                8,
                0.2f
            ));
            
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 5),
                blueBerryStack,
                12,
                8,
                0.2f
            ));
        }

        if (event.getType() == ModVillagers.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack zirconIngotStack = new ItemStack(ModItems.ZIRCON.get(), 10);

            trades.get(1).add((trader, rand) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 5),
                zirconIngotStack,
                12,
                8,
                0.2f
            ));
        }
    }
}
