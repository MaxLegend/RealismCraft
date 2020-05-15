package com.legendgamer.realism.blocks.tree.sapling;

import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.API.BasicBlock.BasicBlockBush;
import com.legendgamer.realism.blocks.tree.frame.BlockDefSapling;
import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.gen.trees.coniferous.GenPineTree;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PineSP extends BlockDefSapling {

	public PineSP(Material materialIn, String name, CreativeTabs tab) {
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

