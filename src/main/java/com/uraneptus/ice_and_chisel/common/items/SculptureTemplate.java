package com.uraneptus.ice_and_chisel.common.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SculptureTemplate extends Item {

    public SculptureTemplate(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        Tag tag = stack.getOrCreateTag().get("id");
        if (tag != null) {
            tooltip.add(Component.literal(tag.getAsString()));
        }
    }
}
