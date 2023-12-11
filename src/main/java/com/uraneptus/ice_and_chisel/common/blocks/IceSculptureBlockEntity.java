package com.uraneptus.ice_and_chisel.common.blocks;

import com.uraneptus.ice_and_chisel.core.registry.IACBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IceSculptureBlockEntity extends BlockEntity {

    public IceSculptureBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(IACBlockEntity.ICE_SCULPTURE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}
