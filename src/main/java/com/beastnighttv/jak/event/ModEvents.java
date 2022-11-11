package com.beastnighttv.jak.event;

import java.util.List;
import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.item.ModItems;
import com.beastnighttv.jak.networking.ModMessages;
import com.beastnighttv.jak.networking.packet.ThirstDataSyncS2CPacket;
import com.beastnighttv.jak.thirst.PlayerThirst;
import com.beastnighttv.jak.thirst.PlayerThirstProvider;
import com.beastnighttv.jak.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
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

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(JAKMinecraftMod.MODID, "properties"), new PlayerThirstProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyForm(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerThirst.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                if (thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) {
                    thirst.subThirst(1);
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), (ServerPlayer) event.player);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            }
        }
    }
}
