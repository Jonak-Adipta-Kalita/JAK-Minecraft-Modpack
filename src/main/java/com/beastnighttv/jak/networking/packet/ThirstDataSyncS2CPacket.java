package com.beastnighttv.jak.networking.packet;

import java.util.function.Supplier;
import com.beastnighttv.jak.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ThirstDataSyncS2CPacket {
    private final int thirst;

    public ThirstDataSyncS2CPacket(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ClientThirstData.set(thirst);
        });

        return true;
    }
}
