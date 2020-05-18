package com.legendgamer.realism.API.metautils;

import com.legendgamer.realism.API.BasicItem.BasicItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Collection;

public abstract class BasicMetadataItem<T extends Enum<T> & IItemEnum> extends BasicItem {

    private final Class<T> clazz;
    public final ItemPropertyEnum<T> propertyValues;

    public BasicMetadataItem(String name, int maxStackSize, CreativeTabs tab) {
        super(name, maxStackSize, tab);
        setHasSubtypes(true);
        clazz = setEnum();
        propertyValues = new ItemPropertyEnum<>("type", clazz, setAllowedValues());
    }

    protected abstract Class<T> setEnum();
    protected Collection<T> setAllowedValues() {
        return Arrays.asList(clazz.getEnumConstants());
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        return super.getUnlocalizedName(stack) + "." + propertyValues.getStringValueFromMeta(meta);
    }

    public ResourceLocation getRegistryName(int meta) {
        return new ResourceLocation(super.getRegistryName() + "." + propertyValues.getStringValueFromMeta(meta));
    }

    protected T getBeamType(ItemStack stack) {
        return propertyValues.getValueFromMeta(stack.getMetadata());
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    public final int getMetaCount() {
        return propertyValues.getAllowedValues().size();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) for (int i = 0; i < getMetaCount(); ++i) items.add(new ItemStack(this, 1, i));
    }
}