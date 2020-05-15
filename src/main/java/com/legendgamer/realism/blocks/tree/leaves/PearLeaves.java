package com.legendgamer.realism.blocks.tree.leaves;

import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.blocks.tree.frame.BlockRealLeaves;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PearLeaves extends BlockRealLeaves {

	public PearLeaves(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		
	}
	/**
	 * Жизнедеятельность листвы - время когда замена на цветущую, когда рядом спавнить веточку, когда листву(для разрастания)
	 * Life activity of foliage - a time when replacing with a flowering one, when a branch is spawned nearby, when a foliage (for growth)
	 */
	@Override
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) {
		
	}

	@Override
	public int getFrequencyTick() {
		return 40;
	}
	@Override
	public Block getFruitage() {
		return BlocksList.PEAR_FRUIT;
	}
}
