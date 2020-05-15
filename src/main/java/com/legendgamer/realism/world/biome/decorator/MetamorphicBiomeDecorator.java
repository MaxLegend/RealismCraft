package com.legendgamer.realism.world.biome.decorator;

import java.util.Random;

import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.blocks.worldblock.GrassSided;
import com.legendgamer.realism.blocks.worldblock.SwampGrass;
import com.legendgamer.realism.config.ConfigManager;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.gen.feature.WorldGenRealismOre;
import com.legendgamer.realism.world.gen.trees.foliate.GenBirchTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenLindenTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenOakTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenPearTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenPoplarTree;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MetamorphicBiomeDecorator extends BiomeDecorator {


	public final IBlockState SWAMP_WATER = BlocksList.SWAMP_WATER.getDefaultState();

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
	public void decorate(World w, Random random, Biome biome, BlockPos p)
	{
		this.chunkPos = p;

		this.stonecoal_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_COAL.getDefaultState(), coalsize, BlocksList.METAMORPHIC_STONE);
		this.copper_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_COPPER.getDefaultState(), coppersize,BlocksList.METAMORPHIC_STONE);
		this.tin_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_TIN.getDefaultState(), tinsize,BlocksList.METAMORPHIC_STONE);
		this.titan_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_TITAN.getDefaultState(), titansize,BlocksList.METAMORPHIC_STONE);
		this.nickel_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_NICKEL.getDefaultState(), nickelsize,BlocksList.METAMORPHIC_STONE);
		this.vanadium_gen = new WorldGenRealismOre(BlocksList.METAMORPHIC_VANADIUM.getDefaultState(), vanadiumsize,BlocksList.METAMORPHIC_STONE);
		if(biome == RegBiomes.METAMORPHIC_FOREST) {
			generateTree(w, random, p);		
		}
		if(biome == RegBiomes.METAMORPHIC_SWAMP) {
			decorateSwamp(w,p);
		}

		this.genDecorations(biome, w, random);

	} 
	public WorldGenerator genTreeBirch = new GenBirchTree();
	public WorldGenerator genTreeOak = new GenOakTree();
	public WorldGenerator genTreeLinden = new GenLindenTree();

	public WorldGenerator genTreePoplar = new GenPoplarTree();
	public WorldGenerator genTreePear = new GenPearTree();

	public void decoratePlains(World w, BlockPos p) {
		ThreadLocalRandom rt = ThreadLocalRandom.current();
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				int l = j * 4 + 4;
				int k = i * 4 + 4;
				BlockPos pos = w.getHeight(p.add(k, 0, l));
				Block var = rt.nextBoolean() ? BlocksList.FERN : BlocksList.SEDGE;
				if(w.getBlockState(pos).getBlock() instanceof GrassSided) {
					w.setBlockState(pos.up(), var.getDefaultState());
				}
			}
		}
	}
	public void decorateSwamp(World w, BlockPos p) {
		ThreadLocalRandom rt = ThreadLocalRandom.current();
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				int l = j * 4 + 4 + rt.nextInt(8);
				int k = i * 4 + 4 + rt.nextInt(8);
				BlockPos blockpos = w.getHeight(p.add(k, 0, l));
				if(w.getBlockState(blockpos).getBlock() == BlocksList.SALTY_WATER) {
					w.setBlockState(blockpos, SWAMP_WATER);
				}
				if(w.getBlockState(blockpos).getBlock() instanceof SwampGrass && rt.nextInt(19) == 3) {
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(w.getBlockState(blockpos.offset(f)) != Blocks.AIR) {
							w.setBlockState(blockpos, SWAMP_WATER);
						}
					}
				

				}
			}
		}
	}
	public void generateTree(World world, Random r, BlockPos p) {
		ThreadLocalRandom rt = ThreadLocalRandom.current();
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				if(rt.nextInt(16) == 4) {
					int l = j * 4 + 4 + r.nextInt(8);
					int k = i * 4 + 4  + r.nextInt(8) ;
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeBirch.generate(world, r, blockpos);
						}
					}
				}
				if(rt.nextInt(14) == 4) {
					int l2 = j * 4  + r.nextInt(8) ;
					int k2 = i* 4   + r.nextInt(8) ;
					BlockPos blockpos2 = world.getHeight(p.add(k2, 0, l2));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(p.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(p.offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeLinden.generate(world, r, blockpos2);
						} 
					}
				}
				if(rt.nextInt(14) == 4) {
					int l3 = j * 4  + r.nextInt(8) ;
					int k3 = i* 4   + r.nextInt(8) ;
					BlockPos blockpos3 = world.getHeight(p.add(k3, 0, l3));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(p.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(p.offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeOak.generate(world, r, blockpos3);

						} 
					}
				}
				if(rt.nextInt(16) == 4) {
					int l = j * 4 + 4 + r.nextInt(8);
					int k = i * 4 + 4  + r.nextInt(8) ;
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreePoplar.generate(world, r, blockpos);
						}
					}
				}
				if(rt.nextInt(16) == 2) {
					int k = i * 4 + 2 + rt.nextInt(6,8) + r.nextInt(3);
					int l = j * 4 + 2 + + rt.nextInt(6,8) + r.nextInt(3);
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreePear.generate(world, r, blockpos);
						}
					}
				}
			}
		}

		//	new SpruceTreeGenerator(BlocksList.REAL_SPRUCE, BlocksList.REAL_SPRUCE_BRANCH, BlocksList.REAL_SPRUCE_LEAVES).generate(world, random, this.chunkPos);
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
