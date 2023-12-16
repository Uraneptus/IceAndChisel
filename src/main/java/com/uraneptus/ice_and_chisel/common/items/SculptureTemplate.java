package com.uraneptus.ice_and_chisel.common.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class SculptureTemplate extends Item {
    private ResourceLocation storedEntityLocation;

    public SculptureTemplate(Properties pProperties) {
        super(pProperties);
    }

    /*
    public void setStoredEntityLocation(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("EntityNamespace") || tag.contains("EntityPath")) {
            this.storedEntityLocation = new ResourceLocation(tag.getString("EntityNamespace"), tag.getString("EntityPath"));
        } else {
            this.storedEntityLocation = EntityType.getKey(EntityType.PIG);
        }
    }

     */

    public String getEntityNamespace(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("EntityNamespaceI")) {
            return tag.getString("EntityNamespaceI");
        }
        return "minecraft";
    }

    public String getEntityPath(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("EntityPathI")) {
            return tag.getString("EntityPathI");
        }
        return "pig";
    }

    public ResourceLocation getStoredEntityLocation(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("EntityNamespace") || tag.contains("EntityPath")) {
            return new ResourceLocation(tag.getString("EntityNamespace"), tag.getString("EntityPath"));
        } else {
            return EntityType.getKey(EntityType.PIG);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        Tag tag = stack.getOrCreateTag().get("EntityNamespaceI");
        Tag tag1 = stack.getOrCreateTag().get("EntityPathI");
        if (tag != null && tag1 != null) {
            tooltip.add(Component.literal(tag.getAsString() + ':' + tag1.getAsString()));
        }
    }
}
