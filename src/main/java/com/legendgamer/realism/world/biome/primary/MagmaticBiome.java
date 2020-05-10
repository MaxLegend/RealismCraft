package com.legendgamer.realism.world.biome.primary;


import java.util.Random;

import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.biome.base.BiomeBase;
import com.legendgamer.realism.world.biome.base.EnumBiomes;
import com.legendgamer.realism.world.biome.decorator.MagmaticBiomeDecorator;
import com.legendgamer.realism.world.gen.feature.SwampBiomeDecorator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class MagmaticBiome extends BiomeBase{


	private final Type type;
	//public static final WorldGenAshTree ASH_GEN_TREE = new WorldGenAshTree(false, BlocksList.MAGMATIC_DIRT, true);

	BiomeDecorator bd = new MagmaticBiomeDecorator();
	public MagmaticBiome(Biome.BiomeProperties properties, EnumBiomes eBiome, Type type) {
		super(properties, eBiome);
		this.type = type;

		this.topBlock = BlocksList.MAGMATIC_GRASS.getDefaultState();
		this.fillerBlock = BlocksList.MAGMATIC_DIRT.getDefaultState();

		this.clayBlock = BlocksList.MAGMATIC_CLAY.getDefaultState();
		this.claystoneBlock = BlocksList.MAGMATIC_CLAYSTONE.getDefaultState(); 
		this.stoneBlock = BlocksList.MAGMATIC_STONE.getDefaultState();
		this.sandBlock = BlocksList.MAGMATIC_SAND.getDefaultState();
		this.gravelBlock = BlocksList.MAGMATIC_GRAVEL.getDefaultState();


	}
	public SwampBiomeDecorator swp = new SwampBiomeDecorator();
	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		if(this.type == Type.SWAMP) {
			swp.generate(world, rand, pos);
		}
		//		this.getRandomTreeFeaturer(rand);
		//	ASH_GEN_TREE.generate(world, rand, pos);
		this.bd.decorate(world, rand, this, pos);
	}
	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		return  new MagmaticBiomeDecorator();
	}

	//	 public RWorldGenAbstractTree getRandomTreeFeaturer(Random rand)
	//	    {
	//		System.out.println("ff");
	//	        if (this.type == Type.FOREST)
	//	        {
	//	   //         return ASH_GEN_TREE;
	//	        }
	//	    //    return ASH_GEN_TREE;
	//	    }

	//0x308f0d
	public static enum Type {
		HILLS(), PLAINS(), LAKE(), SWAMP(), FOREST();


	}
}
