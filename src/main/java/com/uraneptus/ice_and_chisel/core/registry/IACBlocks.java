package com.uraneptus.ice_and_chisel.core.registry;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = IceAndChisel.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IACBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IceAndChisel.MODID);

    private static RegistryObject<Block> registerBlockWithItem(String name, Supplier<Block> block) {
        return registerBlockWithItem(name, block, new Item.Properties());
    }

    private static RegistryObject<Block> registerBlockWithItem(String name, Supplier<Block> block, Item.Properties properties) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        RegistryObject<Item> itemRegistryObject = IACItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(), properties));
        return registryObject;
    }

}
