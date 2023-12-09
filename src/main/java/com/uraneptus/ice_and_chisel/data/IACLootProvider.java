package com.uraneptus.ice_and_chisel.data;

import com.uraneptus.ice_and_chisel.core.registry.IACBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class IACLootProvider extends LootTableProvider {

    public IACLootProvider(PackOutput pOutput) {
        super(pOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)));
    }

    public static class BlockLoot extends BlockLootSubProvider {

        protected BlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return IACBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }

        @Override
        protected void generate() {

        }

    }
}
