package com.legendgamer.realism.world.biome.decorator;

import java.util.Random;

import com.legendgamer.realism.config.ConfigManager;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.gen.feature.WorldGenRealismOre;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MetamorphicBiomeDecorator extends BiomeDecorator {

	public WorldGenerator stonecoal_gen,
	copper_gen,
	tin_gen,
	titan_gen,
	nickel_gen,
	vanadium_gen;

	int coalsize = ThreadLocalRandom.current().nextInt(40,80);
	int coppersize = ThreadLocalRandom.current().nextInt(30,45);
	int tinsize = ThreadLocalRandom.current().nextInt(12,26);
	int titansize = ThreadLocalRandom.current().nextInt(7,13);
	int nickelsize = ThreadLocalRandom.current().nextInt(5,10);
	int vanadiumsize = ThreadLocalRandom.current().nextInt(2,8);
	public MetamorphicBiomeDecorator() {

	}
	@Override
	public void decorate(World world, Random random, Biome biome, BlockPos pos)
	{
		this.chunkPos = pos;

		this.stonecoal_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_COAL.getDefaultState(), coalsize, BlocksList.METAMORPHIC_STONE);
		this.copper_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_COPPER.getDefaultState(), coppersize,BlocksList.METAMORPHIC_STONE);
		this.tin_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_TIN.getDefaultState(), tinsize,BlocksList.METAMORPHIC_STONE);
		this.titan_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_TITAN.getDefaultState(), titansize,BlocksList.METAMORPHIC_STONE);
		this.nickel_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_NICKEL.getDefaultState(), nickelsize,BlocksList.METAMORPHIC_STONE);
		this.vanadium_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_VANADIUM.getDefaultState(), vanadiumsize,BlocksList.METAMORPHIC_STONE);

		this.genDecorations(biome, world, random);

	}
	@Override
	protected void genDecorations(Biome biome, World world, Random random)
	{
		this.generateOres(world, random);

		
	}
	protected void genOre(World worldIn, Random random, int blockCount, WorldGenerator generator, int minHeight, int maxHeight)
	{
		if (maxHeight < minHeight)
		{
			int i = minHeight;
			minHeight = maxHeight;
			maxHeight = i;
		}
		else if (maxHeight == minHeight)
		{
			if (minHeight < 255)
			{
				++maxHeight;
			}
			else
			{
				--minHeight;
			}
		}
		for (int j = 0; j < blockCount; ++j)
		{
			BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
			if(blockpos != null && generator != null) generator.generate(worldIn, random, blockpos);

		}
	}
	@Override
	protected void generateOres(World worldIn, Random random)
	{
		int rare_coal = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mtm_coal);
		int rare_copper = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mtm_copper);
		int rare_tin = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mtm_tin);
		int rare_titan = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mtm_titan);
		int rare_nickel = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mtm_nickel);
		int rare_vanadium = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mtm_vanadium);

		if(rare_coal == 0) genOre(worldIn, random, coalsize, stonecoal_gen, 0, 80);
		if(rare_copper == 0) genOre(worldIn, random, coppersize, copper_gen, 0, 80);
		if(rare_tin == 0) genOre(worldIn, random, tinsize, tin_gen, 0, 30);
		if(rare_titan == 0) genOre(worldIn, random, titansize,titan_gen, 0, 20);
		if(rare_nickel == 0) genOre(worldIn, random, nickelsize, nickel_gen, 0, 12);
		if(rare_vanadium == 0) genOre(worldIn, random, vanadiumsize, vanadium_gen, 0, 8);



	}

}
