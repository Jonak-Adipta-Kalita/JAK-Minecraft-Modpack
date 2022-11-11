package com.beastnighttv.jak.client;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(
        JAKMinecraftMod.MODID,
        "textures/thirst/filled_thirst.png"
    );

    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(
        JAKMinecraftMod.MODID,
        "textures/thirst/empty_thirst.png"
    );

    public static final IGuiOverlay HUD_THIRST = (((gui, poseStack, partialTick, width, height) -> {
        Minecraft minecraft = gui.getMinecraft();
        if (!minecraft.options.hideGui && gui.shouldDrawSurvivalElements()) {
            int x = width / 2;
            int y = height;
    
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, EMPTY_THIRST);
    
            for (int i = 0; i < 10; i++) {
                GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
            }
    
            RenderSystem.setShaderTexture(0, FILLED_THIRST);
    
            for (int i = 0; i < 10; i++) {
                if(ClientThirstData.getPlayerThirst() > i) {
                    GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
                } else {
                    break;
                }
            }
        }
    }));
}
