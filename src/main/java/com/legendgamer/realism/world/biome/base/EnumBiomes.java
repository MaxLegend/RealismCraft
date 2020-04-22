package com.legendgamer.realism.world.biome.base;

import com.legendgamer.realism.reg.RegBiomes;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public enum EnumBiomes {
	METAMORPHIC(1, RegBiomes.METAMORPHIC_TYPE),
	MAGMATIC(1, RegBiomes.MAGMATIC_TYPE),
	OCEAN(4, RegBiomes.MAGMATIC_TYPE),
	RIVER(4, RegBiomes.MAGMATIC_TYPE),
	SEDIMENTARY( 1, RegBiomes.SEDIMENTARY_TYPE);
	

	private final BiomeManager.BiomeType biomeType;

	private final int weight;

	private final BiomeDictionary.Type[] types;

	EnumBiomes(int weight, BiomeDictionary.Type... types) {
		this(null, weight, types);
	}

	EnumBiomes(BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... types) {
		this.biomeType = biomeType;
		this.weight = weight;
		this.types = types;
	}

	public BiomeManager.BiomeType getBiomeType() {
		return this.biomeType;
	}

	public int getWeight() {
		return this.weight;
	}

	public BiomeDictionary.Type[] getTypes() {
		return this.types;
	}
}
