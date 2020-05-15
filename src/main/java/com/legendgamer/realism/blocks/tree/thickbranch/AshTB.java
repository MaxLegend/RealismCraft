package com.legendgamer.realism.blocks.tree.thickbranch;

import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.blocks.tree.frame.BlockThickBranch;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AshTB extends BlockThickBranch {

	public AshTB(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		// TODO Auto-generated constructor stub
	}
	public Block getLeaves() {
		return BlocksList.REAL_ASH_LEAVES;
	}
//	public Block getTN() {
//		return tn;
//	}
	public Block getTB() {
		return this;
	}
	public int getFrequencyTick() {
		return 40;
	}
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) {
		
	}
	
}
