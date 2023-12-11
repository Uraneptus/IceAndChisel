package com.uraneptus.ice_and_chisel.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.ice_and_chisel.common.blocks.IceSculptureBlockEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceSculptureBER<T extends IceSculptureBlockEntity> implements BlockEntityRenderer<T> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public IceSculptureBER(BlockEntityRendererProvider.Context pContext) {
        entityRenderDispatcher = pContext.getEntityRenderer();
    }

    @Override
    public void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        entityRenderDispatcher.render(EntityType.PIG.create(pBlockEntity.getLevel()),
                pBlockEntity.getBlockPos().getX(),
                pBlockEntity.getBlockPos().getY(),
                pBlockEntity.getBlockPos().getZ(),
                0.0F, 0.0F,
                pPoseStack, pBuffer, pPackedLight);
    }

}
