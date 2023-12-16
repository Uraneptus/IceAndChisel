package com.uraneptus.ice_and_chisel.core.events;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import com.uraneptus.ice_and_chisel.common.blocks.IceSculptureBlock;
import com.uraneptus.ice_and_chisel.common.blocks.IceSculptureBlockEntity;
import com.uraneptus.ice_and_chisel.common.items.SculptureTemplate;
import com.uraneptus.ice_and_chisel.core.registry.IACBlocks;
import com.uraneptus.ice_and_chisel.core.registry.IACItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = IceAndChisel.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IACEntityEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();
        Block block = level.getBlockState(pos).getBlock();
        ItemStack itemInMainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack itemInOffHand = player.getOffhandItem();
        //ItemStack sculptureItem = new ItemStack(IACItems.SCULPTURE_TEMPLATE.get());

        if (block instanceof IceBlock && itemInMainHand.is(IACItems.CHISEL.get()) && itemInOffHand.is(IACItems.SCULPTURE_TEMPLATE.get())) {
            IceSculptureBlock newBlock = (IceSculptureBlock) IACBlocks.ICE_SCULPTURE_BLOCK.get();

            level.setBlockAndUpdate(pos, newBlock.defaultBlockState());
            IceSculptureBlockEntity blockEntity = (IceSculptureBlockEntity) newBlock.newBlockEntity(pos, newBlock.defaultBlockState());
            if (blockEntity != null) {
                SculptureTemplate item = ((SculptureTemplate)itemInOffHand.getItem());
                blockEntity.setResourceLocation(new ResourceLocation(item.getEntityNamespace(itemInOffHand), item.getEntityPath(itemInOffHand)));
                level.setBlockEntity(blockEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onItemUseTick(LivingEntityUseItemEvent.Tick event) {
        LivingEntity livingEntity = event.getEntity();

        if (livingEntity instanceof Player player && player.isScoping()) {
            ItemStack itemInOtherHand = player.getItemInHand(decideForHand(player.getUsedItemHand()));
            if (itemInOtherHand.is(Items.PAPER)) {
                EntityHitResult entityHitResult = getLookedAtEntity(event);
                if (entityHitResult != null) {
                    ItemStack sculptureTemplate = new ItemStack(IACItems.SCULPTURE_TEMPLATE.get());
                    sculptureTemplate.getOrCreateTag().putString("EntityNamespaceI", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entityHitResult.getEntity().getType())).getNamespace());
                    sculptureTemplate.getOrCreateTag().putString("EntityPathI", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entityHitResult.getEntity().getType())).getPath());
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
