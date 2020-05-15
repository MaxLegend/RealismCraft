package com.legendgamer.realism.world.biome;

import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.biome.base.BiomeBase;
import com.legendgamer.realism.world.biome.base.EnumBiomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class RiverBiome extends BiomeBase {

	private final Type type;

	public RiverBiome(Biome.BiomeProperties properties, EnumBiomes eBiome, Type type) {
		super(properties, eBiome);
		this.type = type;
		   this.spawnableCreatureList.clear();
			this.topBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
			this.fillerBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
	        this.stoneBlock = BlocksList.SEDIMENTARY_STONE.getDefaultState();
			this.sandBlock = BlocksList.SEDIMENTARY_SAND.getDefaultState();
			this.gravelBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return this.type.color;
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return this.type.color;
	}


	public static enum Type {
		RIVER(0x228B22);

		final int color;

		Type(int color) {
			this.color = color;
		}

	}
	
}