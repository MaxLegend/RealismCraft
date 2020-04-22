package com.legendgamer.realism.API.BasicBlock;

import java.util.List;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
/**
 * Basics API class by Block With drops items
 * @author LegendGamer
 */
public class BasicBlockWithDropItem extends BasicBlock {
	public Item drop;
	public  int valueDrops;
	public int meta;

	public BasicBlockWithDropItem(final Material materialIn, final String name,float hardness, float resistanse,SoundType soundtype, Item drop, int valueDrops, int meta,CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse,soundtype,tab);
		setItemDrop();
		this.setHarvestLevel("pickaxe", 4);
		this.drop = drop;
		this.meta = meta;
		this.valueDrops = valueDrops;
	}
	
	public void setItemDrop() {
		if(drop == null) {
			this.drop = drop.getItemFromBlock(Blocks.AIR);
		}
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos,IBlockState state, int fortune) {
		Random rand = new Random();
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state,fortune);
		ret.add(new ItemStack(drop, valueDrops + rand.nextInt(4), meta));
		return ret;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.AIR);

	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
}
