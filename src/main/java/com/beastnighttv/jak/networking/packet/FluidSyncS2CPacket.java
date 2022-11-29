package com.beastnighttv.jak.networking.packet;

import java.util.function.Supplier;
import com.beastnighttv.jak.block.entity.GemInfusingStationBlockEntity;
import com.beastnighttv.jak.screen.GemInfusingStationMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2CPacket(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        Minecraft minecraft = Minecraft.getInstance();

        context.enqueueWork(() -> {
            if(minecraft.level.getBlockEntity(pos) instanceof GemInfusingStationBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(minecraft.player.containerMenu instanceof GemInfusingStationMenu menu &&
                    menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
        });

        return true;
    }
}

