package com.legendgamer.realism.capability.world_cap;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

public final class DateStorage implements Capability.IStorage<IDate>, Callable<IDate>
{
    public static final DateStorage INSTANCE = new DateStorage();

    @Override
    public NBTBase writeNBT(Capability<IDate> capability, IDate instance, EnumFacing side)
    {
    	
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("day", instance.getDay()); 
        compound.setByte("month", instance.getMonth());
        compound.setInteger("year", instance.getYear());
        compound.setBoolean("isSnow", instance.getEnableSnow());
        return compound;
    }

    @Override
    public void readNBT(Capability<IDate> capability, IDate instance, EnumFacing side, NBTBase nbt)
    {
        NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setDay(compound.getByte("day"));
        instance.setMonth(compound.getByte("month"));
        instance.setYear(compound.getInteger("year"));
        instance.setEnableSnow(compound.getBoolean("isSnow"));
    }

    @Override
    public IDate call()
    {
        return new Date();
    }
}