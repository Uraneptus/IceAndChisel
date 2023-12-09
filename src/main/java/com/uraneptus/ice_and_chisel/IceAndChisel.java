package com.uraneptus.ice_and_chisel;

import com.uraneptus.ice_and_chisel.core.registry.IACBlocks;
import com.uraneptus.ice_and_chisel.core.registry.IACItems;
import com.uraneptus.ice_and_chisel.data.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

@Mod(IceAndChisel.MODID)
@Mod.EventBusSubscriber(modid = IceAndChisel.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IceAndChisel {
    public static final String MODID = "ice_and_chisel";

    public IceAndChisel() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::gatherData);

        IACBlocks.BLOCKS.register(bus);
        IACItems.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new IACItemModelsProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new IACLangProvider(packOutput));
        IACBlockTagsProvider blockTagsProvider = new IACBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        generator.addProvider(includeServer, blockTagsProvider);
        generator.addProvider(includeServer, new IACItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), fileHelper));
        generator.addProvider(includeServer, new IACLootProvider(packOutput));
        generator.addProvider(includeServer, new IACRecipeProvider(packOutput));
    }

}
