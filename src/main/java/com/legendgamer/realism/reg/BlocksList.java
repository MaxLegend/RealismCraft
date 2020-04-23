package com.legendgamer.realism.reg;

import com.legendgamer.realism.Realism;
import com.legendgamer.realism.API.BasicBlock.BasicBlockWithDoubleInfo;
import com.legendgamer.realism.API.BasicBlock.BasicBlockWithInfo;
import com.legendgamer.realism.API.BasicBlock.BasicLogBlockTile;
import com.legendgamer.realism.blocks.BlockDirt;
import com.legendgamer.realism.blocks.GrassSided;
import com.legendgamer.realism.blocks.TallGrassAgeble;
import com.legendgamer.realism.blocks.liquid.CustomWater;
import com.legendgamer.realism.blocks.tree.BlockRealLeaves;
import com.legendgamer.realism.blocks.tree.BlockRealTrees;
import com.legendgamer.realism.blocks.tree.BlockTreeNewBranch;
import com.legendgamer.realism.blocks.tree.sapling.BlockFoliateSapling;
import com.legendgamer.realism.blocks.tree.sapling.BlockPineSapling;
import com.legendgamer.realism.blocks.tree.sapling.BlockSpruceSapling;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlocksList {


	//	public static final Block OAK_TREE_BLOCK = new GrowTree(Material.GRASS, "oak_tree_block", 0, 0, SoundType.WOOD, Realism.tabTrees);
	//public static final Block OAK_BRANCH_BLOCK = new GrowBranch(Material.GRASS, "oak_branch_block", 0, 0, SoundType.WOOD, Realism.tabTrees);
	//	public static final Block SMALL_OAK_LEAVES_BLOCK = new SmallLeaves(Material.LEAVES, "small_oak_leaves_block", 0, 0, SoundType.PLANT, Realism.tabTrees);
	//public static final Block BIG_OAK_LEAVES_BLOCK = new BigLeaves(Material.LEAVES, "big_oak_leaves_block", 0, 0, SoundType.PLANT, Realism.tabTrees);

	//public static final Block TALL_GRASS = new CustomWater(RegFluids.FRESH_WATER, "fresh_water");
	
	//tall grass
		//metamorphic
	public static final Block SEDGE = new TallGrassAgeble(Material.PLANTS, "plants/sedge", SoundType.PLANT, Realism.tabTrees, BlocksList.SEDGE);
	public static final Block WIPE = new TallGrassAgeble(Material.PLANTS, "plants/wipe", SoundType.PLANT, Realism.tabTrees, BlocksList.WIPE);
	public static final Block FERN = new TallGrassAgeble(Material.PLANTS, "plants/fern", SoundType.PLANT, Realism.tabTrees, BlocksList.FERN);
	
	//basic water
	public static final Block SALTY_WATER = new CustomWater(RegFluids.SALTY_WATER, "water/salty_water");
	public static final Block SWAMP_WATER = new CustomWater(RegFluids.SWAMP_WATER, "water/swamp_water");
	public static final Block FRESH_WATER = new CustomWater(RegFluids.FRESH_WATER, "water/fresh_water");

	public static final Block
	
	MAGMATIC_DIRT = new BlockDirt(Material.GROUND, "magmatic/magmatic_dirt", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
	MAGMATIC_GRASS = new GrassSided(Material.GRASS, "magmatic/magmatic_grass", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic", MAGMATIC_DIRT),
	
	MAGMATIC_STONE = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_stone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.magmatic"),
	MAGMATIC_GRAVEL = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_gravel", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
	MAGMATIC_SAND = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_sand", 0, 0, SoundType.SAND, Realism.tabRocks, "group.magmatic"),
	
	MAGMATIC_CLAY = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_clay", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
	MAGMATIC_CLAYSTONE = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_claystone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.magmatic");
	

	public static final Block 

	METAMORPHIC_DIRT = new BlockDirt(Material.GROUND, "metamorphic/metamorphic_dirt", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"), 

	METAMORPHIC_GRASS = new GrassSided(Material.GRASS, "metamorphic/metamorphic_grass", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic", METAMORPHIC_DIRT),
	
	METAMORPHIC_STONE = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_stone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.metamoprhic"),
	METAMORPHIC_GRAVEL = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_gravel", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"),
	METAMORPHIC_SAND = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_sand", 0, 0, SoundType.SAND, Realism.tabRocks, "group.metamoprhic"),
	METAMORPHIC_CLAY = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_clay", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"),
	METAMORPHIC_CLAYSTONE = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_claystone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.metamoprhic");

	public static final Block

	SEDIMENTARY_DIRT = new BlockDirt(Material.GROUND, "sedimentary/sedimentary_dirt", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary"),
	SEDIMENTARY_GRASS = new GrassSided(Material.GRASS, "sedimentary/sedimentary_grass", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary", SEDIMENTARY_DIRT),

	SEDIMENTARY_STONE = new BasicBlockWithInfo(Material.ROCK, "sedimentary/sedimentary_stone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.sedimentary"),
	SEDIMENTARY_GRAVEL = new BasicBlockWithInfo(Material.GROUND, "sedimentary/sedimentary_gravel", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary"),
	SEDIMENTARY_SAND = new BasicBlockWithInfo(Material.GROUND, "sedimentary/sedimentary_sand", 0, 0, SoundType.SAND, Realism.tabRocks, "group.sedimentary"),
	SEDIMENTARY_CLAY  = new BasicBlockWithInfo(Material.GROUND, "sedimentary/sedimentary_clay", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary"),
	SEDIMENTARY_CLAYSTONE = new BasicBlockWithInfo(Material.GROUND, "sedimentary/sedimentary_claystone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.sedimentary");

	//copper
	public static final Block
	MAGMATIC_COPPER = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_copper", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.copper"),
	SEDIMENTARY_COPPER = new BasicBlockWithDoubleInfo(Material.ROCK, "sedimentary/sed_copper", 0, 0, SoundType.STONE, Realism.tabOres, "group.sedimentary", "group.copper"),
	METAMORPHIC_COPPER = new BasicBlockWithDoubleInfo(Material.ROCK, "metamorphic/met_copper", 0, 0, SoundType.STONE, Realism.tabOres, "group.metamoprhic", "group.copper");

	//tin
	public static final Block
	MAGMATIC_TIN = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_tin", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.tin"),
	SEDIMENTARY_TIN = new BasicBlockWithDoubleInfo(Material.ROCK, "sedimentary/sed_tin", 0, 0, SoundType.STONE, Realism.tabOres, "group.sedimentary", "group.tin"),
	METAMORPHIC_TIN = new BasicBlockWithDoubleInfo(Material.ROCK, "metamorphic/met_tin", 0, 0, SoundType.STONE, Realism.tabOres, "group.metamoprhic", "group.tin");

	//coal
	public static final Block
	SEDIMENTARY_COAL = new BasicBlockWithInfo(Material.ROCK, "sedimentary/sed_coal", 0, 0, SoundType.STONE, Realism.tabOres, "group.sedimentary"),
	MAGMATIC_COAL = new BasicBlockWithInfo(Material.ROCK, "magmatic/magm_coal", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic"),
	METAMORPHIC_COAL = new BasicBlockWithInfo(Material.ROCK, "metamorphic/met_coal", 0, 0, SoundType.STONE, Realism.tabOres, "group.metamoprhic");

	//nickel
	public static final Block 
	MAGMATIC_NICKEL = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_nickel", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.nickel"),
	METAMORPHIC_NICKEL = new BasicBlockWithDoubleInfo(Material.ROCK, "metamorphic/met_nickel", 0, 0, SoundType.STONE, Realism.tabOres, "group.metamoprhic", "group.nickel");

	//iron
	public static final Block 
	MAGMATIC_IRON = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_iron", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.iron"),
	SEDIMENTARY_IRON = new BasicBlockWithDoubleInfo(Material.ROCK, "sedimentary/sed_iron", 0, 0, SoundType.STONE, Realism.tabOres, "group.sedimentary", "group.iron");

	//chrome
	public static final Block 
	MAGMATIC_CHROME = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_chrome", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.chrome");

	//vanadium
	public static final Block 
	MAGMATIC_VANADIUM = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_vanadium", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.vanadium"),
	METAMORPHIC_VANADIUM = new BasicBlockWithDoubleInfo(Material.ROCK, "metamorphic/met_vanadium", 0, 0, SoundType.STONE, Realism.tabOres, "group.metamoprhic", "group.vanadium");

	//tungsten
	public static final Block 
	SEDIMENTARY_TUNGSTEN = new BasicBlockWithDoubleInfo(Material.ROCK, "sedimentary/sed_tungsten", 0, 0, SoundType.STONE, Realism.tabOres, "group.sedimentary", "group.tungsten");

	//titan
	public static final Block 
	MAGMATIC_TITAN = new BasicBlockWithDoubleInfo(Material.ROCK, "magmatic/magm_titan", 0, 0, SoundType.STONE, Realism.tabOres, "group.magmatic", "group.titan"),
	METAMORPHIC_TITAN = new BasicBlockWithDoubleInfo(Material.ROCK, "metamorphic/met_titan", 0, 0, SoundType.STONE, Realism.tabOres, "group.metamoprhic", "group.titan");

	//Magmatic Trees
	public static final Block REAL_PINE_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_pine_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_SPRUCE_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_spruce_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_LARCH_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_larch_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	//Sedimentary Trees
	public static final Block REAL_PEAR_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_pear_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_POPLAR_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_poplar_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_ASH_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_ash_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	//Metamorphic Trees
	public static final Block REAL_OAK_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_oak_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_BIRCH_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_birch_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_LINDEN_LEAVES = new BlockRealLeaves(Material.WOOD, "tree/real_linden_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);

	public static final Block REAL_ASH_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_ash_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_BIRCH_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_birch_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_LARCH_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_larch_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);

	public static final Block REAL_PEAR_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_pear_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_POPLAR_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_poplar_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_PINE_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_pine_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);

	public static final Block REAL_OAK_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_oak_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_SPRUCE_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_spruce_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	public static final Block REAL_LINDEN_BRANCH = new BlockTreeNewBranch(Material.WOOD, "tree/real_linden_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
	//Magmatic Trees
	public static final Block REAL_PINE = new BasicLogBlockTile(Material.WOOD, "tree/real_pine", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_PINE_BRANCH);
	public static final Block REAL_SPRUCE = new BasicLogBlockTile(Material.WOOD, "tree/real_spruce", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_SPRUCE_BRANCH);
	public static final Block REAL_LARCH = new BasicLogBlockTile(Material.WOOD, "tree/real_larch", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_LARCH_BRANCH);
	//Sedimentary Trees
	public static final Block REAL_PEAR = new BasicLogBlockTile(Material.WOOD, "tree/real_pear", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_PEAR_BRANCH);
	public static final Block REAL_POPLAR = new BasicLogBlockTile(Material.WOOD, "tree/real_poplar", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_POPLAR_BRANCH);
	public static final Block REAL_ASH = new BasicLogBlockTile(Material.WOOD, "tree/real_ash", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_ASH_BRANCH);
	//Metamorphic Trees
	public static final Block REAL_OAK = new BasicLogBlockTile(Material.WOOD, "tree/real_oak", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_OAK_BRANCH);
	public static final Block REAL_BIRCH = new BasicLogBlockTile(Material.WOOD, "tree/real_birch", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_BIRCH_BRANCH);
	public static final Block REAL_LINDEN = new BasicLogBlockTile(Material.WOOD, "tree/real_linden", 0, 0, SoundType.WOOD, Realism.tabTrees, REAL_LINDEN_BRANCH);

	public static final Block REAL_ASH_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_ash_sapling", Realism.tabTrees, REAL_ASH, REAL_ASH_BRANCH, REAL_ASH_LEAVES);
	public static final Block REAL_OAK_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_oak_sapling", Realism.tabTrees, REAL_OAK, REAL_OAK_BRANCH, REAL_OAK_LEAVES);
	public static final Block REAL_PEAR_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_pear_sapling", Realism.tabTrees, REAL_PEAR, REAL_PEAR_BRANCH, REAL_PEAR_LEAVES);

	public static final Block REAL_PINE_SAPLING = new BlockPineSapling(Material.WOOD, "tree/real_pine_sapling", Realism.tabTrees, REAL_PINE, REAL_PINE_BRANCH,  REAL_PINE_LEAVES);
	public static final Block REAL_POPLAR_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_poplar_sapling", Realism.tabTrees, REAL_POPLAR, REAL_POPLAR_BRANCH, REAL_POPLAR_LEAVES);
	public static final Block REAL_LINDEN_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_linden_sapling", Realism.tabTrees, REAL_LINDEN, REAL_LINDEN_BRANCH, REAL_LINDEN_LEAVES);

	public static final Block REAL_BIRCH_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_birch_sapling", Realism.tabTrees, REAL_BIRCH, REAL_BIRCH_BRANCH, REAL_BIRCH_LEAVES);
	public static final Block REAL_LARCH_SAPLING = new BlockFoliateSapling(Material.WOOD, "tree/real_larch_sapling", Realism.tabTrees, REAL_LARCH, REAL_LARCH_BRANCH, REAL_LARCH_LEAVES);
	public static final Block REAL_SPRUCE_SAPLING = new BlockSpruceSapling(Material.WOOD, "tree/real_spruce_sapling", Realism.tabTrees, REAL_SPRUCE, REAL_SPRUCE_BRANCH,  REAL_SPRUCE_LEAVES);





}
