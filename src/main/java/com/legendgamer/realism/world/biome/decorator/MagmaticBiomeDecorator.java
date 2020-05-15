package com.legendgamer.realism.world.biome.decorator;

import java.util.Random;

import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.config.ConfigManager;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.gen.feature.WorldGenRealismOre;
import com.legendgamer.realism.world.gen.trees.coniferous.GenLarchTree;
import com.legendgamer.realism.world.gen.trees.coniferous.GenPineTree;
import com.legendgamer.realism.world.gen.trees.coniferous.GenSpruceTree;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MagmaticBiomeDecorator extends BiomeDecorator {
	public WorldGenerator anthracite_gen;
	public WorldGenerator copper_gen;
	public WorldGenerator tin_gen;
	public WorldGenerator iron_gen;
	public WorldGenerator titan_gen;
	public WorldGenerator nickel_gen;
	public WorldGenerator chrome_gen;
	public WorldGenerator vanadium_gen;

	
	
	int coalsize = ThreadLocalRandom.current().nextInt(40,80);
	int coppersize = ThreadLocalRandom.current().nextInt(30,45);
	int tinsize = ThreadLocalRandom.current().nextInt(12,26);
	int ironsize = ThreadLocalRandom.current().nextInt(16,28);
	int titansize = ThreadLocalRandom.current().nextInt(7,13);
	int nickelsize = ThreadLocalRandom.current().nextInt(5,10);
	int chromesize = ThreadLocalRandom.current().nextInt(2,8);
	int vanadiumsize = ThreadLocalRandom.current().nextInt(4,10);
	public MagmaticBiomeDecorator() {

	}
	@Override
	public void decorate(World world, Random random, Biome biome, BlockPos pos)
	{
		this.anthracite_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_COAL.getDefaultState(), coalsize, BlocksList.MAGMATIC_STONE);
		this.copper_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_COPPER.getDefaultState(), coppersize, BlocksList.MAGMATIC_STONE);
		this.tin_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_TIN.getDefaultState(), tinsize, BlocksList.MAGMATIC_STONE);
		this.iron_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_IRON.getDefaultState(), ironsize, BlocksList.MAGMATIC_STONE);
		this.titan_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_TITAN.getDefaultState(), titansize, BlocksList.MAGMATIC_STONE);
		this.nickel_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_NICKEL.getDefaultState(), nickelsize, BlocksList.MAGMATIC_STONE);
		this.chrome_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_CHROME.getDefaultState(), chromesize, BlocksList.MAGMATIC_STONE);
		this.vanadium_gen = new WorldGenRealismOre(BlocksList.MAGMATIC_VANADIUM.getDefaultState(), vanadiumsize, BlocksList.MAGMATIC_STONE);
		if(biome == RegBiomes.MAGMATIC_FOREST) {
			this.generateTree(world, random, pos);	
		}
	}
	
	public WorldGenerator genTreePine = new GenPineTree();
	public WorldGenerator genTreeSpruce = new GenSpruceTree();
	public WorldGenerator genTreeLarch = new GenLarchTree();

	@Override
	protected void genDecorations(Biome biome, World world, Random random)
	{
		this.generateOres(world, random);
		
		
		if(biome == RegBiomes.MAGMATIC_SWAMP) {
			
		}
		
	}
	
	public void generateTree(World world, Random r, BlockPos p) {
		ThreadLocalRandom rt = ThreadLocalRandom.current();
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				if(rt.nextInt(9) == 4) {
				//	int k = i * 4 + 2 + rt.nextInt(6,8) + random.nextInt(3);
				//	int l = j * 4 + 2 + rt.nextInt(6,8) + random.nextInt(3);
					int l = j * 4 + 4 + r.nextInt(8);
					int k = i * 4 + 4  + r.nextInt(8) ;
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreePine.generate(world, r, blockpos);
						}
					}
				}
				if(rt.nextInt(9) == 4) {
					int l2 = j * 4  + r.nextInt(8) ;
					int k2 = i* 4   + r.nextInt(8) ;
					BlockPos blockpos2 = world.getHeight(p.add(k2, 0, l2));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(p.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(p.offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeSpruce.generate(world, r, blockpos2);
						} 
					}
				}
				if(rt.nextInt(9) == 4) {
					int l3 = j * 4  + r.nextInt(8) ;
					int k3 = i* 4   + r.nextInt(8) ;
					BlockPos blockpos3 = world.getHeight(p.add(k3, 0, l3));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(p.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(p.offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeLarch.generate(world, r, blockpos3);

						} 
					}
				}
			}
		}

	
	}
	@Override
	protected void generateOres(World worldIn, Random random)
	{
		int rare_anthracite = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_coal);
		int rare_copper = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_copper);
		int rare_tin = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_tin);
		int rare_iron = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_iron);
		int rare_titan = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_titan);
		int rare_nickel = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_nickel);
		int rare_chrome = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_chrome);
		int rare_vanadium = ThreadLocalRandom.current().nextInt(ConfigManager.rare_mgm_vanadium);

		if(rare_anthracite == 0)this.genOre(worldIn, random, coalsize, this.anthracite_gen, 0, 80);
		if(rare_copper == 0)this.genOre(worldIn, random, coppersize, this.copper_gen, 0, 70);
		if(rare_tin == 0)this.genOre(worldIn, random, tinsize, this.tin_gen, 0, 50);
		if(rare_iron == 0)this.genOre(worldIn, random, ironsize, this.iron_gen, 0, 25);
		if(rare_titan == 0)this.genOre(worldIn, random, titansize, this.titan_gen, 0, 20);
		if(rare_nickel == 0)this.genOre(worldIn, random, nickelsize, this.nickel_gen, 0, 10);
		if(rare_chrome == 0)this.genOre(worldIn, random, chromesize, this.chrome_gen, 0, 6);
		if(rare_vanadium == 0)this.genOre(worldIn, random, vanadiumsize, this.vanadium_gen, 0, 8);

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
			if(blockpos != null && generator != null)   generator.generate(worldIn, random, blockpos);
		}
	}
}
