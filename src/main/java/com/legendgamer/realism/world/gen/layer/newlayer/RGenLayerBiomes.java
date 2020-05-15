package com.legendgamer.realism.world.gen.layer.newlayer;

import com.legendgamer.realism.reg.RegBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class RGenLayerBiomes extends RGenLayer {


	private int metamorphic_plains = Biome.getIdForBiome(RegBiomes.METAMORPHIC_PLAINS);
	private int magmatic_plains = Biome.getIdForBiome(RegBiomes.MAGMATIC_PLAINS);
	private int sedimentary_plains = Biome.getIdForBiome(RegBiomes.SEDIMENTARY_PLAINS);

	private int sedimentary_knoll = Biome.getIdForBiome(RegBiomes.SEDIMENTARY_KNOLL);
	private int sedimentary_forest = Biome.getIdForBiome(RegBiomes.SEDIMENTARY_FOREST);
	private int sedimentary_swamp = Biome.getIdForBiome(RegBiomes.SEDIMENTARY_SWAMP);




	private int metamorphic_hills = Biome.getIdForBiome(RegBiomes.METAMORPHIC_HILLS);
	private int metamorphic_forest = Biome.getIdForBiome(RegBiomes.METAMORPHIC_FOREST);
	private int metamorphic_swamp = Biome.getIdForBiome(RegBiomes.METAMORPHIC_SWAMP);

	private int magmatic_hills = Biome.getIdForBiome(RegBiomes.MAGMATIC_HILLS);
	private int magmatic_forest = Biome.getIdForBiome(RegBiomes.MAGMATIC_FOREST);
	private int magmatic_swamp = Biome.getIdForBiome(RegBiomes.MAGMATIC_SWAMP);

	public final int[] sed_biomes = { sedimentary_knoll, sedimentary_swamp, sedimentary_plains,sedimentary_forest}; 
	public final int[] mtm_biomes = { metamorphic_plains, metamorphic_hills, metamorphic_swamp,metamorphic_forest}; 
	public final int[] mgm_biomes = { magmatic_hills, magmatic_plains, magmatic_swamp,magmatic_forest}; 

	public RGenLayerBiomes(long seed, GenLayer parent) {
		super(seed);
		this.parent = parent;

	}



	@Override
	public int[] getInts(int x, int z, int width, int height) {
		int[] aint = this.parent.getInts(x, z, width, height);
		int[] dest = IntCache.getIntCache(width * height);
		int ndx;
		int ndz;
		for (int dz = 0; dz < height; dz++) {
			for (int dx = 0; dx < width; dx++) {
				initChunkSeed(dx + x, dz + z);
				
				int rand = this.nextInt(2);
				
				int k = aint[(dx + dz * width)];//Получение id биома
				if(k == Biome.getIdForBiome(RegBiomes.SEDIMENTARY_PLAINS)) {
					dest[(dx + dz * width)] = this.sed_biomes[nextInt(this.sed_biomes.length)];

				} else if(k == Biome.getIdForBiome(RegBiomes.METAMORPHIC_PLAINS)) {
					dest[(dx + dz * width)] = this.mtm_biomes[nextInt(this.mtm_biomes.length)];

				}
				else if(k == Biome.getIdForBiome(RegBiomes.MAGMATIC_PLAINS)) {
					dest[(dx + dz * width)] = this.mgm_biomes[nextInt(this.mgm_biomes.length)];

				}



			}
		}

		return dest;
	}
}

