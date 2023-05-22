package com.beastnighttv.jak.entity.client;

import javax.annotation.Nullable;
import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.entity.custom.ChomperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChomperRenderer extends GeoEntityRenderer<ChomperEntity> {
    public ChomperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ChomperModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ChomperEntity instance) {
        return new ResourceLocation(JAKMinecraftMod.MODID, "textures/entity/chomper_texture.png");
    }

    @Override
    public RenderType getRenderType(ChomperEntity animatable, float partialTicks, PoseStack stack,
            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
            int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(0.8f, 0.8f, 0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }
}
