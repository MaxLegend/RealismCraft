package com.legendgamer.realism.world.biome.primary;


import java.util.Random;

import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.biome.base.BiomeBase;
import com.legendgamer.realism.world.biome.base.EnumBiomes;
import com.legendgamer.realism.world.biome.decorator.MetamorphicBiomeDecorator;
import com.legendgamer.realism.world.biome.decorator.SwampBiomeDecorator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class MetamorphicBiome extends BiomeBase {

	public final Type type;

	
	 
	BiomeDecorator bd = new MetamorphicBiomeDecorator();
	public MetamorphicBiome(Biome.BiomeProperties properties, EnumBiomes eBiome, Type type) {
		super(properties, eBiome);
		this.type = type;
		if(type == Type.SWAMP) {
			this.topBlock = BlocksList.METAMORPHIC_SWAMP_GRASS.getDefaultState();
			this.fillerBlock = BlocksList.METAMORPHIC_SWAMP_DIRT.getDefaultState();
			this.color = 0x445429;
		}else {
			this.topBlock = BlocksList.METAMORPHIC_GRASS.getDefaultState();
			this.fillerBlock = BlocksList.METAMORPHIC_DIRT.getDefaultState();
			this.color = 0x6B8E23;
		}
		
	

        this.clayBlock = BlocksList.METAMORPHIC_CLAY.getDefaultState();
		this.claystoneBlock = BlocksList.METAMORPHIC_CLAYSTONE.getDefaultState();
        this.stoneBlock = BlocksList.METAMORPHIC_STONE.getDefaultState();
		this.sandBlock = BlocksList.METAMORPHIC_SAND.getDefaultState();
		this.gravelBlock = BlocksList.METAMORPHIC_GRAVEL.getDefaultState();
	}
	public SwampBiomeDecorator swp = new SwampBiomeDecorator();
	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		if(this.type == Type.SWAMP) {
			
			swp.generate(world, rand, pos);
		}
		 this.bd.decorate(world, rand, this, pos);
	}
	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		return new MetamorphicBiomeDecorator(); 
	}
	

	//0x3aa612
	public static enum Type {
		
		HILLS(), PLAINS() , LAKE(), SWAMP(), FOREST(), EDGE();


	}
}
