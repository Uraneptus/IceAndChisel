package com.uraneptus.ice_and_chisel.data;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import com.uraneptus.ice_and_chisel.core.registry.IACItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class IACItemModelsProvider extends ItemModelProvider {
    public IACItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IceAndChisel.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        IACItems.ITEMS.getEntries().forEach(this::basicItem);
    }

    private void basicItem(Supplier<? extends Item> item) {
        basicItem(item.get());
    }
}
