package com.legendgamer.realism.capability.world_cap;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public final class DateProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IDate.class)
    public static final Capability<IDate> DATE = null;

    private IDate instance = DATE.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == DATE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == DATE ? DATE.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return DATE.getStorage().writeNBT(DATE, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        DATE.getStorage().readNBT(DATE, this.instance, null, nbt);
    }
}