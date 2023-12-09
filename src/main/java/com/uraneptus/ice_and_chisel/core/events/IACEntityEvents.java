package com.uraneptus.ice_and_chisel.core.events;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import com.uraneptus.ice_and_chisel.core.registry.IACItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IceAndChisel.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IACEntityEvents {

    @SubscribeEvent
    public static void onItemUseTick(LivingEntityUseItemEvent.Tick event) {
        LivingEntity livingEntity = event.getEntity();

        if (livingEntity instanceof Player player && player.isScoping()) {
            ItemStack itemInOtherHand = player.getItemInHand(decideForHand(player.getUsedItemHand()));
            if (itemInOtherHand.is(Items.PAPER)) {
                EntityHitResult entityHitResult = getLookedAtEntity(event);
                if (entityHitResult != null) {
                    ItemStack sculptureTemplate = new ItemStack(IACItems.SCULPTURE_TEMPLATE.get());
                    sculptureTemplate.getOrCreateTag().putString("EntityTemplate", entityHitResult.getEntity().getType().getDescriptionId());
                    if (!player.getInventory().contains(sculptureTemplate)) {
                        if (!player.getInventory().add(sculptureTemplate)) {
                            player.drop(sculptureTemplate, false);
                        }
                        if (!player.getAbilities().instabuild) {
                            itemInOtherHand.shrink(1);
                        }
                    }
                }
            }
        }
    }

    private static InteractionHand decideForHand(InteractionHand itemUseHand) {
        if (itemUseHand.equals(InteractionHand.MAIN_HAND)) {
            return InteractionHand.OFF_HAND;
        } else {
            return InteractionHand.MAIN_HAND;
        }
    }

    private static EntityHitResult getLookedAtEntity(LivingEntityUseItemEvent event) {
        double distance = 100;
        if (event.getEntity() instanceof Player player) {
            Vec3 viewVector = player.getViewVector(100);
            Vec3 playerLookingPos = player.getEyePosition();
            Vec3 lookingAtVector = playerLookingPos.add(viewVector.x() * distance, viewVector.y() * distance, viewVector.z() * distance);
            return ProjectileUtil.getEntityHitResult(player, playerLookingPos, lookingAtVector, new AABB(playerLookingPos, lookingAtVector), EntitySelector.LIVING_ENTITY_STILL_ALIVE, 0F);
        }
        return null;
    }


}
