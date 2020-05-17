package com.legendgamer.realism.API.metautils;

public interface IBlockType<T extends Enum<T> & IItemEnum> {
    T getBlockType(int meta);
}