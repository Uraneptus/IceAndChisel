package com.uraneptus.ice_and_chisel.core.registry;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import com.uraneptus.ice_and_chisel.common.blocks.IceSculptureBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IACBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IceAndChisel.MODID);

    public static final RegistryObject<BlockEntityType<IceSculptureBlockEntity>> ICE_SCULPTURE_BLOCK_ENTITY = BLOCK_ENTITY_TYPE.register("ice_sculpture", () -> BlockEntityType.Builder.of(IceSculptureBlockEntity::new, new Block[]{IACBlocks.ICE_SCULPTURE_BLOCK.get()}).build(null));


}
