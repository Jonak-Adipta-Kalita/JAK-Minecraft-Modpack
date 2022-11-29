package com.beastnighttv.jak.networking;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.networking.packet.DrinkWaterC2SPacket;
import com.beastnighttv.jak.networking.packet.EnergySyncS2CPacket;
import com.beastnighttv.jak.networking.packet.FluidSyncS2CPacket;
import com.beastnighttv.jak.networking.packet.ThirstDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry
            .ChannelBuilder
            .named(
                new ResourceLocation(JAKMinecraftMod.MODID, "messages")
            )
            .networkProtocolVersion(() -> "0.0.1")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();
        
        INSTANCE = net;
        
        net.messageBuilder(DrinkWaterC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
            .decoder(DrinkWaterC2SPacket::new)
            .encoder(DrinkWaterC2SPacket::toBytes)
            .consumerMainThread(DrinkWaterC2SPacket::handle)
            .add();

        net.messageBuilder(ThirstDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder(ThirstDataSyncS2CPacket::new)
            .encoder(ThirstDataSyncS2CPacket::toBytes)
            .consumerMainThread(ThirstDataSyncS2CPacket::handle)
            .add();

        net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder(EnergySyncS2CPacket::new)
            .encoder(EnergySyncS2CPacket::toBytes)
            .consumerMainThread(EnergySyncS2CPacket::handle)
            .add();
        
        net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
            .decoder(FluidSyncS2CPacket::new)
            .encoder(FluidSyncS2CPacket::toBytes)
            .consumerMainThread(FluidSyncS2CPacket::handle)
            .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
