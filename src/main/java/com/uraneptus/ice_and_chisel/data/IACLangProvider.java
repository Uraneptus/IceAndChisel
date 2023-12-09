package com.uraneptus.ice_and_chisel.data;

import com.uraneptus.ice_and_chisel.IceAndChisel;
import com.uraneptus.ice_and_chisel.core.registry.IACItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class IACLangProvider extends LanguageProvider {

    public IACLangProvider(PackOutput output) {
        super(output, IceAndChisel.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        IACItems.ITEMS.getEntries().forEach(this::forItem);
    }

    public void forItem(Supplier<? extends Item> item) {
        addItem(item, createTranslation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())).getPath()));
    }

    public static String createTranslation(String path) {
        var translation = "";
        List<String> translationParts = Lists.newArrayList();
        var splitList = path.split("_");
        for (String split : splitList) {
            var capitalized = firstToUpperCase(split);
            translationParts.add(capitalized);
        }
        translation = String.join(" ", translationParts);
        return translation;
    }

    public static String firstToUpperCase(String string) {
        var firstLetter = string.charAt(0);
        return string.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
    }
}
