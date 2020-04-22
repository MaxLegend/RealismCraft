package com.legendgamer.realism.API.BasicBlock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
/**
 * Basics API class by Block With drops blocks
 * @author LegendGamer
 */
public class BasicBlockWithDropsBlock extends BasicBlock {
	
	public  Block drop;
	public int valueDrops;

	public BasicBlockWithDropsBlock(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype,Block drop,int valueDrops,CreativeTabs tab) {
		super(materialIn,name,hardness, resistanse, soundtype,tab);
		this.drop = drop;
		this.valueDrops = valueDrops;
	}
	
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		ret.add(new ItemStack(drop, valueDrops, 0));			 	    
		return ret;
	}
}
