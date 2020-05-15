package com.legendgamer.realism.world.gen.layer.newlayer;

import com.legendgamer.realism.reg.RegBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;

public class RGenLayerShore extends RGenLayer
{
    public RGenLayerShore(long seed, RGenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed(var7 + par1, var8 + par2);
                int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
                int var10;
                int var11;
                int var12;
                int var13;

                if (!RegBiomes.isOceanicBiome(var9) && var9 != Biome.getIdForBiome(RegBiomes.RIVER) && var9 != Biome.getIdForBiome(RegBiomes.SEA))
                {
                    var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
                    var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
                    var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
                    var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

                    if (!RegBiomes.isOceanicBiome(var10) && !RegBiomes.isOceanicBiome(var11) && !RegBiomes.isOceanicBiome(var12) && !RegBiomes.isOceanicBiome(var13))
                        var6[var8 + var7 * par3] = var9;
                    else
                    {
                        int beachid = Biome.getIdForBiome(RegBiomes.BEACH);
                        if (RegBiomes.isMountainBiome(var9)) beachid = Biome.getIdForBiome(RegBiomes.GRAVEL_BEACH);
                        var6[var8 + var7 * par3] = beachid;
                    }
                }
                else
                {
                    var6[var8 + var7 * par3] = var9;
                }
            }
        }
        return var6;
    }
}
