package com.beastnighttv.jak.networking;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.networking.packet.DrinkWaterC2SPacket;
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
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
