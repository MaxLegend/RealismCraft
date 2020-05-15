package com.legendgamer.realism.world.biome.decorator;

import java.util.Random;

import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.blocks.worldblock.SwampGrass;
import com.legendgamer.realism.config.ConfigManager;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.gen.feature.WorldGenRealismOre;
import com.legendgamer.realism.world.gen.trees.foliate.GenAshTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenBirchTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenOakTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenPearTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenPoplarTree;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class SedimentaryBiomeDecorator extends BiomeDecorator {

	
	public final IBlockState SWAMP_WATER = BlocksList.SWAMP_WATER.getDefaultState();
	
	public WorldGenerator fossils_gen,
	copper_gen,
	tin_gen,
	iron_gen,
	tungsten_gen;

	public WorldGenerator genTreeAsh = new GenAshTree();
	public WorldGenerator genTreePear = new GenPearTree();
	public WorldGenerator genTreePoplar = new GenPoplarTree();
	public WorldGenerator genTreeBirch = new GenBirchTree();
	public WorldGenerator genTreeOak = new GenOakTree();

	int fossilssize = ThreadLocalRandom.current().nextInt(40,80);
	int coppersize = ThreadLocalRandom.current().nextInt(30,45);
	int tinsize = ThreadLocalRandom.current().nextInt(12,26);
	int ironsize = ThreadLocalRandom.current().nextInt(16,28);
	int tungstensize = ThreadLocalRandom.current().nextInt(2,6);

	public SedimentaryBiomeDecorator() {

	}
	@Override
	public void decorate(World world, Random random, Biome biome, BlockPos pos)
	{
		this.chunkPos = pos;

		this.fossils_gen = new WorldGenRealismOre(BlocksList.SEDIMENTARY_COAL.getDefaultState(), fossilssize, BlocksList.SEDIMENTARY_STONE);
		this.copper_gen = new WorldGenRealismOre(BlocksList.SEDIMENTARY_COPPER.getDefaultState(), coppersize,BlocksList.SEDIMENTARY_STONE);
		this.tin_gen = new WorldGenRealismOre(BlocksList.SEDIMENTARY_TIN.getDefaultState(), tinsize,BlocksList.SEDIMENTARY_STONE);
		this.iron_gen = new WorldGenRealismOre(BlocksList.SEDIMENTARY_IRON.getDefaultState(), ironsize,BlocksList.SEDIMENTARY_STONE);
		this.tungsten_gen = new WorldGenRealismOre(BlocksList.SEDIMENTARY_TUNGSTEN.getDefaultState(), tungstensize,BlocksList.SEDIMENTARY_STONE);
		

		this.genDecorations(biome, world, random);
		if(biome == RegBiomes.SEDIMENTARY_FOREST) {
			this.generateTree(world, random, pos);	
		}

	}
	public void decorateSwamp(World w, BlockPos p) {
		ThreadLocalRandom rt = ThreadLocalRandom.current();
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				int l = j * 4 + 4;
				int k = i * 4 + 4;
				BlockPos blockpos = w.getHeight(p.add(k, 0, l));
				if(w.getBlockState(blockpos).getBlock() instanceof SwampGrass && rt.nextInt(5) == 0) {
					w.setBlockState(blockpos, SWAMP_WATER);
				}
			}
		}
	}
	public void generateTree(World world, Random random, BlockPos p) {
		ThreadLocalRandom rt = ThreadLocalRandom.current();
		for (int i = 0; i < 4; ++i)
		{
			for (int j = 0; j < 4; ++j)
			{
				if(rt.nextInt(16) == 2) {
					int k = i * 4 + 2 + rt.nextInt(6,8) + random.nextInt(3);
					int l = j * 4 + 2 + + rt.nextInt(6,8) + random.nextInt(3);
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeAsh.generate(world, random, blockpos);
						}
					}
				}
				if(rt.nextInt(16) == 2) {
					int k = i * 4 + 2 + rt.nextInt(6,8) + random.nextInt(3);
					int l = j * 4 + 2 + + rt.nextInt(6,8) + random.nextInt(3);
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreePear.generate(world, random, blockpos);
						}
					}
				}
				if(rt.nextInt(16) == 2) {
					int k = i * 4 + 2 + rt.nextInt(6,8) + random.nextInt(3);
					int l = j * 4 + 2 + + rt.nextInt(6,8) + random.nextInt(3);
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {

							genTreePoplar.generate(world, random, blockpos);
						}
					}
				}
				if(rt.nextInt(14) == 4) {
					int l3 = j * 4  + random.nextInt(8) ;
					int k3 = i* 4   + random.nextInt(8) ;
					BlockPos blockpos3 = world.getHeight(p.add(k3, 0, l3));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(p.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(p.offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeOak.generate(world, random, blockpos3);

						} 
					}
				}
				if(rt.nextInt(16) == 4) {
					int l = j * 4 + 4 + random.nextInt(8);
					int k = i * 4 + 4  + random.nextInt(8) ;
					BlockPos blockpos = world.getHeight(p.add(k, 0, l));
					for(EnumFacing f : EnumFacing.HORIZONTALS) {
						if(!(world.getBlockState(blockpos.offset(f)) instanceof BlockRealTrees) && !(world.getBlockState(blockpos.offset(f).offset(f)) instanceof BlockRealTrees)&& !(world.getBlockState(blockpos.offset(f).offset(f).offset(f)) instanceof BlockRealTrees)) {
							genTreeBirch.generate(world, random, blockpos);
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
		int rare_coal = ThreadLocalRandom.current().nextInt(ConfigManager.rare_sed_coal);
		int rare_copper = ThreadLocalRandom.current().nextInt(ConfigManager.rare_sed_copper);
		int rare_tin = ThreadLocalRandom.current().nextInt(ConfigManager.rare_sed_tin);
		int rare_iron = ThreadLocalRandom.current().nextInt(ConfigManager.rare_sed_iron);
		int rare_tungsten = ThreadLocalRandom.current().nextInt(ConfigManager.rare_sed_tungsten);


		if(rare_coal == 0) genOre(worldIn, random, fossilssize, fossils_gen, 0, 80);
		if(rare_copper == 0) genOre(worldIn, random, coppersize, copper_gen, 0, 80);
		if(rare_tin == 0) genOre(worldIn, random, tinsize, tin_gen, 0, 30);
		if(rare_iron == 0) genOre(worldIn, random, ironsize,iron_gen, 0, 20);
		if(rare_tungsten == 0) genOre(worldIn, random, tungstensize, tungsten_gen, 0, 4);




	}

}
