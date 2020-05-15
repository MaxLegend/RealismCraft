package com.legendgamer.realism.world.biome;

import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.biome.base.BiomeBase;
import com.legendgamer.realism.world.biome.base.EnumBiomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class PondBiome extends BiomeBase {



	private final Type type;

	public PondBiome(Biome.BiomeProperties properties, EnumBiomes eBiome, Type type) {
		super(properties, eBiome);
		this.type = type;
		   this.spawnableCreatureList.clear();
			this.topBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
			this.fillerBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
			this.clayBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
			this.claystoneBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
	        this.stoneBlock = BlocksList.SEDIMENTARY_STONE.getDefaultState();
			this.sandBlock = BlocksList.SEDIMENTARY_SAND.getDefaultState();
			this.gravelBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
	}



	public static enum Type {
		OCEAN(), DEEP_OCEAN(), SMALL_OCEAN(), SEA(), LAKE();


	}
	
}