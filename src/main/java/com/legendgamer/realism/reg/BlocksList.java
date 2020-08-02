package com.legendgamer.realism.reg;

import com.legendgamer.realism.API.BasicBlock.BasicBlockLogModel;
import com.legendgamer.realism.API.BasicBlock.BasicBlockWithDoubleInfo;
import com.legendgamer.realism.API.BasicBlock.BasicBlockWithInfo;
import com.legendgamer.realism.Realism;
import com.legendgamer.realism.blocks.grass.TallGrassAgeble;
import com.legendgamer.realism.blocks.liquid.CustomWater;
import com.legendgamer.realism.blocks.tree.BlockThinLog;
import com.legendgamer.realism.blocks.tree.XBeam;
import com.legendgamer.realism.blocks.tree.YBeam;
import com.legendgamer.realism.blocks.tree.ZBeam;
import com.legendgamer.realism.blocks.tree.fruitleaves.*;
import com.legendgamer.realism.blocks.tree.leaves.*;
import com.legendgamer.realism.blocks.tree.sapling.*;
import com.legendgamer.realism.blocks.tree.thickbranch.*;
import com.legendgamer.realism.blocks.tree.thinbranch.*;
import com.legendgamer.realism.blocks.tree.trunk.*;
import com.legendgamer.realism.blocks.worldblock.BlockDirt;
import com.legendgamer.realism.blocks.worldblock.GrassSided;
import com.legendgamer.realism.blocks.worldblock.SwampGrass;
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

    public static final Block BASE_GRANITE = new BasicBlockWithInfo(Material.ROCK, "base_granite", 0, 0, SoundType.STONE, Realism.tabRocks, "group.granite");
    //TODO creativeTabs == null
    //thin log double
//    public static final Block ASH_LOG_TD = new BlockDoubleThinLog(Material.GROUND, "tree/log_t/ash_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block BIRCH_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/birch_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block OAK_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/oak_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block LINDEN_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/linden_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block PEAR_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/pear_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block POPLAR_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/poplar_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block PINE_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/pine_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block SPRUCE_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/spruce_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);
//    public static final Block LARCH_LOG_TD = new BasicBlockLogModel(Material.GROUND, "tree/log_t/larch_log_td", 0, 0, SoundType.GROUND, Realism.tabTrees);

    public static final Block ASH_LOG_TX = new XBeam(0, Material.WOOD, "tree/log_t/ash_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block ASH_LOG_TZ = new ZBeam(0, Material.WOOD, "tree/log_t/ash_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block ASH_LOG_TY = new YBeam(0, Material.WOOD, "tree/log_t/ash_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block BIRCH_LOG_TX = new XBeam(1, Material.WOOD, "tree/log_t/birch_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block BIRCH_LOG_TZ = new ZBeam(1, Material.WOOD, "tree/log_t/birch_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block BIRCH_LOG_TY = new YBeam(1, Material.WOOD, "tree/log_t/birch_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block OAK_LOG_TX = new XBeam(2, Material.WOOD, "tree/log_t/oak_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block OAK_LOG_TZ = new ZBeam(2, Material.WOOD, "tree/log_t/oak_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block OAK_LOG_TY = new YBeam(2, Material.WOOD, "tree/log_t/oak_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block LINDEN_LOG_TX = new XBeam(3, Material.WOOD, "tree/log_t/linden_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block LINDEN_LOG_TZ = new ZBeam(3, Material.WOOD, "tree/log_t/linden_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block LINDEN_LOG_TY = new YBeam(3, Material.WOOD, "tree/log_t/linden_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block PEAR_LOG_TX = new XBeam(4, Material.WOOD, "tree/log_t/pear_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block PEAR_LOG_TZ = new ZBeam(4, Material.WOOD, "tree/log_t/pear_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block PEAR_LOG_TY = new YBeam(4, Material.WOOD, "tree/log_t/pear_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block POPLAR_LOG_TX = new XBeam(5, Material.WOOD, "tree/log_t/poplar_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block POPLAR_LOG_TZ = new ZBeam(5, Material.WOOD, "tree/log_t/poplar_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block POPLAR_LOG_TY = new YBeam(5, Material.WOOD, "tree/log_t/poplar_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block PINE_LOG_TX = new XBeam(6, Material.WOOD, "tree/log_t/pine_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block PINE_LOG_TZ = new ZBeam(6, Material.WOOD, "tree/log_t/pine_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block PINE_LOG_TY = new YBeam(6, Material.WOOD, "tree/log_t/pine_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block SPRUCE_LOG_TX = new XBeam(7, Material.WOOD, "tree/log_t/spruce_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block SPRUCE_LOG_TZ = new ZBeam(7, Material.WOOD, "tree/log_t/spruce_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block SPRUCE_LOG_TY = new YBeam(7, Material.WOOD, "tree/log_t/spruce_log_ty", 0, 0, SoundType.WOOD, null);

    public static final Block LARCH_LOG_TX = new XBeam(8, Material.WOOD, "tree/log_t/larch_log_tx", 0, 0, SoundType.WOOD, null);
    public static final Block LARCH_LOG_TZ = new ZBeam(8, Material.WOOD, "tree/log_t/larch_log_tz", 0, 0, SoundType.WOOD, null);
    public static final Block LARCH_LOG_TY = new YBeam(8, Material.WOOD, "tree/log_t/larch_log_ty", 0, 0, SoundType.WOOD, null);

    //И для ствола, и для толстых бревен - дроп в зависимости от стадии роста: 5-6 - падает бревно обычное, 0-1 палка, 2-3-4 тонкое бревно(в четверть толстого)
    //thin log(dropped from thick branch)
    public static final Block ASH_LOG_T = new BlockThinLog(Material.GROUND, "tree/log_t/ash_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block BIRCH_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/birch_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block OAK_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/oak_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block SPRUCE_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/spruce_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block PEAR_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/pear_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block PINE_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/pine_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block POPLAR_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/poplar_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block LARCH_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/larch_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block LINDEN_LOG_T = new BasicBlockLogModel(Material.GROUND, "tree/log_t/linden_log_t", 0, 0, SoundType.GROUND, Realism.tabTrees);
    //log(dropped with trunk)(
    public static final Block ASH_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/ash_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block BIRCH_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/birch_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block OAK_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/oak_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block SPRUCE_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/spruce_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block PEAR_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/pear_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block PINE_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/pine_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block POPLAR_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/poplar_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block LARCH_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/larch_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block LINDEN_LOG = new BasicBlockLogModel(Material.GROUND, "tree/log/linden_log", 0, 0, SoundType.GROUND, Realism.tabTrees);
    //stripped log
    public static final Block ASH_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/ash_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block BIRCH_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/birch_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block OAK_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/oak_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block SPRUCE_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/spruce_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block PEAR_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/pear_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block PINE_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/pine_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block POPLAR_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/poplar_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block LARCH_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/larch_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);
    public static final Block LINDEN_LOG_STR = new BasicBlockLogModel(Material.GROUND, "tree/log/linden_log_str", 0, 0, SoundType.GROUND, Realism.tabTrees);

    public static final Block

            MAGMATIC_DIRT = new BlockDirt(Material.GROUND, "magmatic/magmatic_dirt", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
            MAGMATIC_GRASS = new GrassSided(Material.GRASS, "magmatic/magmatic_grass", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic", MAGMATIC_DIRT),
            MAGMATIC_SWAMP_DIRT = new BlockDirt(Material.GROUND, "magmatic/magmatic_dirt_sw", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
            MAGMATIC_SWAMP_GRASS = new SwampGrass(Material.ROCK, "magmatic/magmatic_grass_sw", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic", MAGMATIC_SWAMP_DIRT),

    MAGMATIC_STONE = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_stone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.magmatic"),
            MAGMATIC_GRAVEL = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_gravel", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
            MAGMATIC_SAND = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_sand", 0, 0, SoundType.SAND, Realism.tabRocks, "group.magmatic"),

    MAGMATIC_CLAY = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_clay", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.magmatic"),
            MAGMATIC_CLAYSTONE = new BasicBlockWithInfo(Material.ROCK, "magmatic/magmatic_claystone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.magmatic");


    public static final Block

            METAMORPHIC_DIRT = new BlockDirt(Material.GROUND, "metamorphic/metamorphic_dirt", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"),
            METAMORPHIC_GRASS = new GrassSided(Material.GRASS, "metamorphic/metamorphic_grass", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic", METAMORPHIC_DIRT),
            METAMORPHIC_SWAMP_DIRT = new BlockDirt(Material.GROUND, "metamorphic/metamorphic_dirt_sw", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"),
            METAMORPHIC_SWAMP_GRASS = new SwampGrass(Material.GRASS, "metamorphic/metamorphic_grass_sw", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic", METAMORPHIC_SWAMP_DIRT),

    METAMORPHIC_STONE = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_stone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.metamoprhic"),
            METAMORPHIC_GRAVEL = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_gravel", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"),
            METAMORPHIC_SAND = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_sand", 0, 0, SoundType.SAND, Realism.tabRocks, "group.metamoprhic"),
            METAMORPHIC_CLAY = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_clay", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.metamoprhic"),
            METAMORPHIC_CLAYSTONE = new BasicBlockWithInfo(Material.ROCK, "metamorphic/metamorphic_claystone", 0, 0, SoundType.STONE, Realism.tabRocks, "group.metamoprhic");

    public static final Block

            SEDIMENTARY_DIRT = new BlockDirt(Material.GROUND, "sedimentary/sedimentary_dirt", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary"),
            SEDIMENTARY_GRASS = new GrassSided(Material.GRASS, "sedimentary/sedimentary_grass", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary", SEDIMENTARY_DIRT),
            SEDIMENTARY_SWAMP_DIRT = new BlockDirt(Material.GROUND, "sedimentary/sedimentary_dirt_sw", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary"),
            SEDIMENTARY_SWAMP_GRASS = new SwampGrass(Material.GRASS, "sedimentary/sedimentary_grass_sw", 0, 0, SoundType.GROUND, Realism.tabRocks, "group.sedimentary", SEDIMENTARY_SWAMP_DIRT),


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


    //Тонкие ветки исключены из обращения до необходимости.
    public static final Block REAL_ASH_BRANCH = new AshTN(Material.WOOD, "tree/real_ash_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_BIRCH_BRANCH = new BirchTN(Material.WOOD, "tree/real_birch_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_LARCH_BRANCH = new LarchTN(Material.WOOD, "tree/real_larch_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block REAL_PEAR_BRANCH = new PearTN(Material.WOOD, "tree/real_pear_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_POPLAR_BRANCH = new PoplarTN(Material.WOOD, "tree/real_poplar_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_PINE_BRANCH = new PineTN(Material.WOOD, "tree/real_pine_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block REAL_OAK_BRANCH = new OakTN(Material.WOOD, "tree/real_oak_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_SPRUCE_BRANCH = new SpruceTN(Material.WOOD, "tree/real_spruce_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_LINDEN_BRANCH = new LindenTN(Material.WOOD, "tree/real_linden_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);

    //Заготовки - блоки-переменные для плодов.
    /**
     * Каждое дерево имеет: блок ствола, блок толстой ветки, блок тонкой ветки(возможно, в будущем), блок листвы, блок плода, блок сгнившего ствола(только 6 стадии, поваленные деревья), блок саженца(с итемом)
     * Соответсвенно каждый тип дерева имеет свое бревно(отдельный блок, дропающийся со ствола) свое тонкое бревно(отдельный блок, дропается с толстой ветки), которые можно объединить друг с другом в связку с
     * помощью высушенной лозы
     *  свою доску, доски(как блок), верстак, дверь, лестницу, ступеньки, забор, калитку, баррикаду, сундук + всякие предметы делающиеся из дерева, мысли о которых
     * придут позднее.
     */
    public static final Block PINE_FRUIT = new PineFL(Material.PLANTS, "fruit_lv/pine_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_PINE_LEAVES = new PineLeaves(Material.LEAVES, "tree/real_pine_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_PINE = new PineTB(Material.WOOD, "tree/pine_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_PINE = new PineTrunk(Material.WOOD, "tree/real_pine", 0, 0, SoundType.WOOD, Realism.tabTrees );


    public static final Block SPRUCE_FRUIT = new SpruceFL(Material.PLANTS, "fruit_lv/spruce_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_SPRUCE_LEAVES = new SpruceLeaves(Material.LEAVES, "tree/real_spruce_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_SPRUCE = new SpruceTB(Material.WOOD, "tree/spruce_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_SPRUCE = new SpruceTrunk(Material.WOOD, "tree/real_spruce", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block LARCH_FRUIT = new LarchFL(Material.PLANTS, "fruit_lv/larch_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_LARCH_LEAVES = new LarchLeaves(Material.LEAVES, "tree/real_larch_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_LARCH = new LarchTB(Material.WOOD, "tree/larch_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_LARCH = new LarchTrunk(Material.WOOD, "tree/real_larch", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block PEAR_FRUIT = new PearFL(Material.PLANTS, "fruit_lv/pear_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_PEAR_LEAVES = new PearLeaves(Material.LEAVES, "tree/real_pear_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_PEAR = new PearTB(Material.WOOD, "tree/pear_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees );
    public static final Block REAL_PEAR = new PearTrunk(Material.WOOD, "tree/real_pear", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block POPLAR_FRUIT = new PoplarFL(Material.PLANTS, "fruit_lv/poplar_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_POPLAR_LEAVES = new PoplarLeaves(Material.LEAVES, "tree/real_poplar_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_POPLAR = new PoplarTB(Material.WOOD, "tree/poplar_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_POPLAR = new PoplarTrunk(Material.WOOD, "tree/real_poplar", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block ASH_FRUIT = new AshFL(Material.PLANTS, "fruit_lv/ash_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_ASH_LEAVES = new AshLeaves(Material.LEAVES, "tree/real_ash_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_ASH = new AshTB(Material.WOOD, "tree/ash_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_ASH = new AshTrunk(Material.WOOD, "tree/real_ash", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block OAK_FRUIT = new OakFL(Material.PLANTS, "fruit_lv/oak_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_OAK_LEAVES = new OakLeaves(Material.LEAVES, "tree/real_oak_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_OAK = new OakTB(Material.WOOD, "tree/oak_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_OAK = new OakTrunk(Material.WOOD, "tree/real_oak", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block BIRCH_FRUIT = new BirchFL(Material.PLANTS, "fruit_lv/birch_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_BIRCH_LEAVES = new BirchLeaves(Material.LEAVES, "tree/real_birch_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_BIRCH = new BirchTB(Material.WOOD, "tree/birch_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_BIRCH = new BirchTrunk(Material.WOOD, "tree/real_birch", 0, 0, SoundType.WOOD, Realism.tabTrees);

    public static final Block LINDEN_FRUIT = new LindenFL(Material.PLANTS, "fruit_lv/linden_frt_lv", 0, 0, SoundType.PLANT, Realism.tabTrees);
    public static final Block REAL_LINDEN_LEAVES = new LindenLeaves(Material.LEAVES, "tree/real_linden_lv", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_TB_LINDEN = new LindenTB(Material.WOOD, "tree/linden_thick_branch", 0, 0, SoundType.WOOD, Realism.tabTrees);
    public static final Block REAL_LINDEN = new LindenTrunk(Material.WOOD, "tree/real_linden", 0, 0, SoundType.WOOD, Realism.tabTrees);


    public static final Block REAL_ASH_SAPLING = new AshSP(Material.WOOD, "tree/real_ash_sapling", Realism.tabTrees);
    public static final Block REAL_OAK_SAPLING = new OakSP(Material.WOOD, "tree/real_oak_sapling", Realism.tabTrees);
    public static final Block REAL_PEAR_SAPLING = new PearSP(Material.WOOD, "tree/real_pear_sapling", Realism.tabTrees);

    public static final Block REAL_PINE_SAPLING = new PineSP(Material.WOOD, "tree/real_pine_sapling", Realism.tabTrees);
    public static final Block REAL_POPLAR_SAPLING = new PoplarSP(Material.WOOD, "tree/real_poplar_sapling", Realism.tabTrees);
    public static final Block REAL_LINDEN_SAPLING = new LindenSP(Material.WOOD, "tree/real_linden_sapling", Realism.tabTrees);

    public static final Block REAL_BIRCH_SAPLING = new BirchSP(Material.WOOD, "tree/real_birch_sapling", Realism.tabTrees);
    public static final Block REAL_LARCH_SAPLING = new LarchSP(Material.WOOD, "tree/real_larch_sapling", Realism.tabTrees);
    public static final Block REAL_SPRUCE_SAPLING = new SpruceSP(Material.WOOD, "tree/real_spruce_sapling", Realism.tabTrees);





}
