package com.legendgamer.realism.world.gen.layer.newlayer;

import com.legendgamer.realism.reg.RegBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.IntCache;

public class RGenLayerBiomeEdge extends RGenLayer
{
    public RGenLayerBiomeEdge(long seed, RGenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int par1, int par2, int xSize, int zSize)
    {
        int[] inCache = this.parent.getInts(par1 - 1, par2 - 1, xSize + 2, zSize + 2);
//        validateIntArray(inCache, xSize + 2, zSize + 2);
        int[] outCache = IntCache.getIntCache(xSize * zSize);
        int var10;
        int var11;
        int var12;
        int var13;

        for (int z = 0; z < zSize; ++z)
        {
            for (int x = 0; x < xSize; ++x)
            {
                this.initChunkSeed(x + par1, z + par2);
                int thisID = inCache[x + 1 + (z + 1) * (xSize + 2)];

                var10 = inCache[x + 1 + (z + 1 - 1) * (xSize + 2)];
                var11 = inCache[x + 1 + 1 + (z + 1) * (xSize + 2)];
                var12 = inCache[x + 1 - 1 + (z + 1) * (xSize + 2)];
                var13 = inCache[x + 1 + (z + 1 + 1) * (xSize + 2)];

                if (thisID == Biome.getIdForBiome(RegBiomes.MAGMATIC_HILLS))
                {
                    if (var10 == Biome.getIdForBiome(RegBiomes.MAGMATIC_HILLS) && var11 == Biome.getIdForBiome(RegBiomes.MAGMATIC_HILLS) && var12 == Biome.getIdForBiome(RegBiomes.MAGMATIC_HILLS) && var13 == Biome.getIdForBiome(RegBiomes.MAGMATIC_HILLS))
                        outCache[x + z * xSize] = thisID;
                    else
                        outCache[x + z * xSize] = Biome.getIdForBiome(RegBiomes.MAGMATIC_EDGE);
                }
                else if (thisID == Biome.getIdForBiome(RegBiomes.METAMORPHIC_HILLS))
                {
                    if (var10 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_HILLS) && var11 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_HILLS) && var12 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_HILLS) && var13 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_HILLS))
                        outCache[x + z * xSize] = thisID;
                    else
                        outCache[x + z * xSize] = Biome.getIdForBiome(RegBiomes.METAMORPHIC_EDGE);
                }
                else if (thisID == Biome.getIdForBiome(RegBiomes.MAGMATIC_SWAMP))
                {
                    if (var10 == Biome.getIdForBiome(RegBiomes.MAGMATIC_SWAMP) && var11 == Biome.getIdForBiome(RegBiomes.MAGMATIC_SWAMP) && var12 == Biome.getIdForBiome(RegBiomes.MAGMATIC_SWAMP) && var13 == Biome.getIdForBiome(RegBiomes.MAGMATIC_SWAMP))
                        outCache[x + z * xSize] = thisID;
                    else
                        outCache[x + z * xSize] = Biome.getIdForBiome(RegBiomes.MAGMATIC_PLAINS);
                }
                else if (thisID == Biome.getIdForBiome(RegBiomes.METAMORPHIC_SWAMP))
                {
                    if (var10 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_SWAMP) && var11 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_SWAMP) && var12 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_SWAMP) && var13 == Biome.getIdForBiome(RegBiomes.METAMORPHIC_SWAMP))
                        outCache[x + z * xSize] = thisID;
                    else
                        outCache[x + z * xSize] = Biome.getIdForBiome(RegBiomes.METAMORPHIC_PLAINS);
                }
                else if (thisID == Biome.getIdForBiome(RegBiomes.SEDIMENTARY_SWAMP))
                {
                    if (var10 == Biome.getIdForBiome(RegBiomes.SEDIMENTARY_SWAMP) && var11 == Biome.getIdForBiome(RegBiomes.SEDIMENTARY_SWAMP) && var12 == Biome.getIdForBiome(RegBiomes.SEDIMENTARY_SWAMP) && var13 == Biome.getIdForBiome(RegBiomes.SEDIMENTARY_SWAMP))
                        outCache[x + z * xSize] = thisID;
                    else
                        outCache[x + z * xSize] = Biome.getIdForBiome(RegBiomes.SEDIMENTARY_PLAINS);
                }
              
                else
                {
                    outCache[x + z * xSize] = thisID;
                }
            }
        }
        return outCache;
    }
}