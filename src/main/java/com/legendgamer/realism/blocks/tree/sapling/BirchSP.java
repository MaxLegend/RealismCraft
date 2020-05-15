package com.legendgamer.realism.blocks.tree.sapling;

import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.blocks.tree.frame.BlockDefSapling;
import com.legendgamer.realism.capability.world_cap.IDate;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BirchSP extends BlockDefSapling {

	public BirchSP(Material materialIn, String name, CreativeTabs tab) {
		super(materialIn, name, tab);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ћетод, определ€ющий жизнеде€тельность саженца.
	 * A method that determines the life activity of a sapling.
	 */
	@Override
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr)
	{
		
	}
	

	@Override
	public int getFrequencyTick() {
		return 40;
	}

}
