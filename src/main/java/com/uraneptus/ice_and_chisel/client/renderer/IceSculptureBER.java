package com.uraneptus.ice_and_chisel.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.uraneptus.ice_and_chisel.common.blocks.IceSculptureBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(Dist.CLIENT)
public class IceSculptureBER<T extends IceSculptureBlockEntity> implements BlockEntityRenderer<T> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public IceSculptureBER(BlockEntityRendererProvider.Context pContext) {
        entityRenderDispatcher = pContext.getEntityRenderer();
    }

    @Override
    public void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(pBlockEntity.getResourceLocation());
        if (entityType != null) {
            Entity entity = entityType.create(pBlockEntity.getLevel());
            if (entity != null) {
                pPoseStack.pushPose();

                float s = 0.5F;

                renderMobStatic(entity, s, entityRenderDispatcher, pPoseStack, pPartialTick, pBuffer, pPackedLight);

                pPoseStack.popPose();
            }
        }


    }

    public static void renderMobStatic(Entity mob, float scale, EntityRenderDispatcher renderer, PoseStack matrixStack, float partialTicks, MultiBufferSource bufferIn, int combinedLightIn) {
        double y = Mth.lerp(partialTicks, mob.yOld, mob.getY());
        double x = mob.getX();
        double z = mob.getZ();

        //y = relativeOffset(y);
        //x = relativeOffset(x);
        //z = relativeOffset(z);

        matrixStack.translate(x, y, z);

        matrixStack.scale(scale, scale, scale);

        renderer.setRenderShadow(false);
        renderer.render(mob, 0, 0, 0, 0.0F, partialTicks, matrixStack, bufferIn, combinedLightIn);
    }

}
