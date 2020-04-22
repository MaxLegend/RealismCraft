package com.legendgamer.realism.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RealismTreeGenerator implements IWorldGenerator  {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

		switch (world.provider.getDimension()){
		case 14:
			generateRealism(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			break;
		}
	}
	public void generateRealism(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){		
	
	//	generateTree(world, random, chunkX, chunkZ);

	}
//	private void generateTree(World world, Random random, int chunkX, int chunkZ){
//		ThreadLocalRandom trrand =	ThreadLocalRandom.current();
//
//		int defX = (chunkX << 4) + 8;
//		int defZ = (chunkZ << 4) + 8;
//
//
//		int x = (chunkX << 4) + 8;
//		int z = (chunkZ << 4) + 8;
//		int y = world.getActualHeight();
//		 WorldGenerator worldgenerator = new WorldGenOak(false,10,BlocksList.BIG_OAK_LEAVES_BLOCK.getDefaultState(), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), false);
//	}


}
