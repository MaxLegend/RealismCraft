package com.legendgamer.realism.blocks.tree.thinbranch;

import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.blocks.tree.frame.BlockTreeNewBranch;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LindenTN extends BlockTreeNewBranch {

	public LindenTN(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	}
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) 
	{
		
	}
	public Block getLeaves() {
		return BlocksList.REAL_LINDEN_LEAVES;
	}
}
