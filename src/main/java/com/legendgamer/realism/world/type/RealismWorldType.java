package com.legendgamer.realism.world.type;

import com.legendgamer.realism.world.biome.RealismBiomeProvider;
import com.legendgamer.realism.world.gen.chunk.RealismChunkGenerator;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.layer.GenLayer;

public class RealismWorldType extends WorldType {

	public RealismWorldType(String name) {
		super(name);
	
	}
	@Override
    public BiomeProvider getBiomeProvider(World world)
    {
    	return new RealismBiomeProvider(world.getWorldInfo());
    }
	
	@Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
		return new RealismChunkGenerator(world, world.getSeed(), generatorOptions);
		
    }

}
