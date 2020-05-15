package com.legendgamer.realism.world.gen.layer.newlayer.river;

import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.gen.layer.newlayer.RGenLayer;

import net.minecraft.world.gen.layer.IntCache;

public class RGenLayerRiverInit extends RGenLayer
{
    public RGenLayerRiverInit(long par1, RGenLayer par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int xCoord, int zCoord, int xSize, int zSize)
    {
        int[] parentCache = this.parent.getInts(xCoord, zCoord, xSize, zSize);
        int[] outCache = IntCache.getIntCache(xSize * zSize);

        for (int z = 0; z < zSize; ++z)
        {
            for (int x = 0; x < xSize; ++x)
            {
                this.initChunkSeed(x + xCoord, z + zCoord);
                int index = x + z * xSize;
                //int xn = index-1;
                //int xp = index+1;
                //int zn = index-zSize;
                //int zp = index+zSize;
                int id = parentCache[index];
                outCache[index] = !RegBiomes.isOceanicBiome(id) && !RegBiomes.isMountainBiome(id) ? 1 : 0;
            }
        }
        return outCache;
    }
}
