package com.legendgamer.realism.world.gen.chunk;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;

public class TerrainGenerator {
	
	public void generateHeightmap(RealismChunkGenerator rcg, int x, int y, int z)
	{
		rcg.depthRegion = rcg.depthNoise.generateNoiseOctaves(rcg.depthRegion, x, z, 5, 5, (double)rcg.settings.depthNoiseScaleX, (double)rcg.settings.depthNoiseScaleZ, (double)rcg.settings.depthNoiseScaleExponent);
	float f = rcg.settings.coordinateScale;
	float f1 = rcg.settings.heightScale;
	rcg.mainNoiseRegion = rcg.mainPerlinNoise.generateNoiseOctaves(rcg.mainNoiseRegion, x, y, z, 5, 33, 5, (double)(f / rcg.settings.mainNoiseScaleX), (double)(f1 / rcg.settings.mainNoiseScaleY), (double)(f / rcg.settings.mainNoiseScaleZ));
	rcg.minLimitRegion = rcg.minLimitPerlinNoise.generateNoiseOctaves(rcg.minLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
	rcg.maxLimitRegion = rcg.maxLimitPerlinNoise.generateNoiseOctaves(rcg.maxLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
	int i = 0;
	int j = 0;

	for (int k = 0; k < 5; ++k)
	{
		for (int l = 0; l < 5; ++l)
		{
			float f2 = 0.0F;
			float f3 = 0.0F;
			float f4 = 0.0F;
			int i1 = 2;
			Biome biome = rcg.biomesForGeneration[k + 2 + (l + 2) * 10];

			for (int j1 = -2; j1 <= 2; ++j1)
			{
				for (int k1 = -2; k1 <= 2; ++k1)
				{
					Biome biome1 = rcg.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
					float f5 = rcg.settings.biomeDepthOffSet + biome1.getBaseHeight() * rcg.settings.biomeDepthWeight;
					float f6 = rcg.settings.biomeScaleOffset + biome1.getHeightVariation() * rcg.settings.biomeScaleWeight;

					if (rcg.terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
					{
						f5 = 1.0F + f5 * 2.0F;
						f6 = 1.0F + f6 * 4.0F;
					}

					float f7 = rcg.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

					if (biome1.getBaseHeight() > biome.getBaseHeight())
					{
						f7 /= 2.0F;
					}

					f2 += f6 * f7;
					f3 += f5 * f7;
					f4 += f7;
				}
			}

			f2 = f2 / f4;
			f3 = f3 / f4;
			f2 = f2 * 0.9F + 0.1F;
			f3 = (f3 * 4.0F - 1.0F) / 8.0F;
			double d7 = rcg.depthRegion[j] / 8000.0D;

			if (d7 < 0.0D)
			{
				d7 = -d7 * 0.3D;
			}

			d7 = d7 * 3.0D - 2.0D;

			if (d7 < 0.0D)
			{
				d7 = d7 / 2.0D;

				if (d7 < -1.0D)
				{
					d7 = -1.0D;
				}

				d7 = d7 / 1.4D;
				d7 = d7 / 2.0D;
			}
			else
			{
				if (d7 > 1.0D)
				{
					d7 = 1.0D;
				}

				d7 = d7 / 8.0D;
			}

			++j;
			double d8 = (double)f3;
			double d9 = (double)f2;
			d8 = d8 + d7 * 0.2D;
			d8 = d8 * (double)rcg.settings.baseSize / 8.0D;
			double d0 = (double)rcg.settings.baseSize + d8 * 4.0D;

			for (int l1 = 0; l1 < 33; ++l1)
			{
				double d1 = ((double)l1 - d0) * (double)rcg.settings.stretchY * 128.0D / 256.0D / d9;

				if (d1 < 0.0D)
				{
					d1 *= 4.0D;
				}

				double d2 = rcg.minLimitRegion[i] / (double)rcg.settings.lowerLimitScale;
				double d3 = rcg.maxLimitRegion[i] / (double)rcg.settings.upperLimitScale;
				double d4 = (rcg.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
				double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

				if (l1 > 29)
				{
					double d6 = (double)((float)(l1 - 29) / 3.0F);
					d5 = d5 * (1.0D - d6) + -10.0D * d6;
				}

				rcg.heightMap[i] = d5;
				++i;
			}
		}
	}
}
}
