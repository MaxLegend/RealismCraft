package com.legendgamer.realism.API.metautils;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.properties.PropertyEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemPropertyEnum<T extends Enum<T> & IItemEnum> extends PropertyEnum<T> {

    private final ImmutableMap<Integer, T> valueMap;
    protected ItemPropertyEnum(String name, Class<T> valueClass, Collection<T> allowedValues) {
        super(name, valueClass, allowedValues);
        Map<Integer, T> tempMap = new HashMap<>();
        for (T allowedValue : allowedValues) tempMap.put(allowedValue.getMetadata(), allowedValue);
        valueMap = ImmutableMap.copyOf(tempMap);
    }

    public String getStringValueFromMeta(int meta) {
        return valueMap.get(meta).getName();
    }

    public T getValueFromMeta(int meta) {
        return valueMap.get(meta);
    }
}
