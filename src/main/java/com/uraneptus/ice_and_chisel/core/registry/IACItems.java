package com.uraneptus.ice_and_chisel.core.registry;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import com.uraneptus.ice_and_chisel.common.items.ChiselItem;
import com.uraneptus.ice_and_chisel.common.items.SculptureTemplate;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = IceAndChisel.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IACItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IceAndChisel.MODID);

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel", () -> new ChiselItem(new Item.Properties()));
    public static final RegistryObject<Item> SCULPTURE_TEMPLATE = ITEMS.register("sculpture_template", () -> new SculptureTemplate(new Item.Properties()));
}
