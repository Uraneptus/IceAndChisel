package com.uraneptus.ice_and_chisel.common.blocks;

import com.uraneptus.ice_and_chisel.core.registry.IACBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

public class IceSculptureBlockEntity extends BlockEntity {
    private String entityNamespace;
    private String entityPath;
    private ResourceLocation location;

    public IceSculptureBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(IACBlockEntity.ICE_SCULPTURE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    //TODO Fix: This doesn't safe correctly. Values are null when reloading world.
    //TODO maybe store resource location
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("EntityRL", writeResourceLocation(this.getResourceLocation()));
        /*
        pTag.putString("EntityNamespace", this.getEntityNamespace());
        pTag.putString("EntityPath", this.getEntityPath());

         */
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("EntityRL")) {
            this.location = readResourceLocation(pTag.getCompound("EntityRL"));
        }

        /*
        this.setEntityNamespace(pTag.getString("EntityNamespace"));
        this.setEntityPath(pTag.getString("EntityPath"));

         */
    }

    public static ResourceLocation readResourceLocation(CompoundTag pTag) {
        return new ResourceLocation(pTag.getString("EntityNamespace"), pTag.getString("EntityPath"));
    }

    public static CompoundTag writeResourceLocation(ResourceLocation location) {
        CompoundTag compoundtag = new CompoundTag();
        compoundtag.putString("EntityNamespace", location.getNamespace());
        compoundtag.putString("EntityPath", location.getPath());
        return compoundtag;
    }

    public ResourceLocation getResourceLocation() {
        return location;
    }

    public void setResourceLocation(ResourceLocation rl) {
        this.location = rl;
    }

    public String getEntityNamespace() {
        return entityNamespace;
    }

    public void setEntityNamespace(String entityNamespace) {
        this.entityNamespace = entityNamespace;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }
}
