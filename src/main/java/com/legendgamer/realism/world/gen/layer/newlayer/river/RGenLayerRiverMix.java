package com.legendgamer.realism.world.gen.layer.newlayer.river;

import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.gen.layer.newlayer.RGenLayer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class RGenLayerRiverMix extends RGenLayer
{
    private final GenLayer biomePatternGeneratorChain;
    private final GenLayer riverPatternGeneratorChain;
    private int[] layerBiomes;
    private int[] layerRivers;
    private int[] layerOut;
    private int xn;
    private int xp;
    private int zn;
    private int zp;

    public RGenLayerRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer)
    {
        super(par1);
        this.biomePatternGeneratorChain = par3GenLayer;
        this.riverPatternGeneratorChain = par4GenLayer;
    }

    public void removeRiver(int index, int biomeToReplaceWith)
    {
        if (layerOut[index] == Biome.getIdForBiome(RegBiomes.RIVER))
        {
            if (xn >= 0 && layerBiomes[xn] == biomeToReplaceWith)
            {
                layerOut[index] = biomeToReplaceWith;
            }
            if (zn >= 0 && layerBiomes[zn] == biomeToReplaceWith)
            {
                layerOut[index] = biomeToReplaceWith;
            }
            if (xp < layerBiomes.length && layerBiomes[xp] == biomeToReplaceWith)
            {
                layerOut[index] = biomeToReplaceWith;
            }
            if (zp < layerBiomes.length && layerBiomes[zp] == biomeToReplaceWith)
            {
                layerOut[index] = biomeToReplaceWith;
            }
        }
    }

    public boolean inBounds(int index, int[] array)
    {
        return index < array.length && index >= 0;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    @Override
    public void initWorldGenSeed(long par1)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.riverPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    @Override

    public int[] getInts(int x, int z, int xSize, int zSize)
    {
        layerBiomes = this.biomePatternGeneratorChain.getInts(x, z, xSize, zSize);
        layerRivers = this.riverPatternGeneratorChain.getInts(x, z, xSize, zSize);
        layerOut = IntCache.getIntCache(xSize * zSize);

        for (int zElement = 0; zElement < zSize; ++zElement)
        {
            for (int xElement = 0; xElement < xSize; ++xElement)
            {
                int index = xElement + zElement * xSize;
                int b = layerBiomes[index];
                int r = layerRivers[index];

                xn = index - 1;
                xp = index + 1;
                zn = index - zSize;
                zp = index + zSize;

                if (RegBiomes.isOceanicBiome(b))
                    layerOut[index] = b;
                else if (r > 0)
                {
                    layerOut[index] = r;

                    //Here we make sure that rivers dont run along ocean/beach splits. We turn the river into oceans.
                    if (RegBiomes.isBeachBiome(b))
                    {
                        layerOut[index] = Biome.getIdForBiome(RegBiomes.OCEAN);
                        if (inBounds(xn, layerOut) && layerOut[xn] == Biome.getIdForBiome(RegBiomes.RIVER))
                        {
                            layerOut[xn] = Biome.getIdForBiome(RegBiomes.OCEAN);
                        }
                        if (inBounds(zn, layerOut) && layerOut[zn] == Biome.getIdForBiome(RegBiomes.RIVER))
                        {
                            layerOut[zn] = Biome.getIdForBiome(RegBiomes.OCEAN);
                        }
                        if (inBounds(zp, layerOut) && RegBiomes.isOceanicBiome(layerBiomes[zp]) && layerRivers[zp] == 0)
                        {
                            layerOut[index] = b;
                        }
                        if (inBounds(zn, layerOut) && RegBiomes.isOceanicBiome(layerBiomes[zn]) && layerRivers[zn] == 0)
                        {
                            layerOut[index] = b;
                        }
                        if (inBounds(xn, layerOut) && RegBiomes.isOceanicBiome(layerBiomes[xn]) && layerRivers[xn] == 0)
                        {
                            layerOut[index] = b;
                        }
                        if (inBounds(xp, layerOut) && RegBiomes.isOceanicBiome(layerBiomes[xp]) && layerRivers[xp] == 0)
                        {
                            layerOut[index] = b;
                        }
                    }
                }
                else
                    layerOut[index] = b;

                //Similar to above, if we're near a lake, we turn the river into lake.
                removeRiver(index, Biome.getIdForBiome(RegBiomes.LAKE));
            }
        }
        return layerOut.clone();
    }
}
