package com.uraneptus.ice_and_chisel.common.blocks;

import com.uraneptus.ice_and_chisel.core.registry.IACBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

public class IceSculptureBlockEntity extends BlockEntity {
    @Nullable
    private Entity entity;

    public IceSculptureBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(IACBlockEntity.ICE_SCULPTURE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Nullable
    public static Entity createEntityFromNBT(CompoundTag tag, @Nullable UUID id, Level world) {
        if (tag != null && tag.contains("id")) {
            Entity entity = EntityType.loadEntityRecursive(tag, world, o -> o);
            if (id != null && entity != null) {
                entity.setUUID(id);
            }
            return entity;
        }
        return null;
    }

    public void setEntity(Tag tag, Level level) {
        this.entity = EntityType.loadEntityRecursive((CompoundTag) tag, level, entity1 -> entity1);
    }

    @Nullable
    public Entity getEntity() {
        return this.entity != null ? entity : null;
    }

}
