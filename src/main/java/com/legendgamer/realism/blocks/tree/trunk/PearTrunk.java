package com.legendgamer.realism.blocks.tree.trunk;

import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PearTrunk extends BlockRealTrees {

	public PearTrunk(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	}
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) {
	}
	public int getFrequencyTick() {
		return 40;
	}
	public Block getTB() {
		return BlocksList.REAL_TB_PEAR;
	}
	public Block getLeaves() {
		return BlocksList.REAL_PEAR_LEAVES;
	}
}
