package com.legendgamer.realism.blocks.tree.frame;

import java.util.Random;

import com.legendgamer.realism.API.BasicBlock.BasicBlockWithCustomModel;
import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFruitLeaves extends BasicBlockWithCustomModel {

	//0 - blossom, 1 - unripe, 2 - ripe
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 2);
	public Item fruit;
	public BlockFruitLeaves(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab)
	{
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.blockState.getBaseState().withProperty(STAGE, 0);
	}
	/**
	 * 0 - Jan,
	 * 1 - Feb,
	 * 2 - Mar,
	 * 3 - Apr,
	 * 4 - May,
	 * 5 - June,
	 * 6 - Jule,
	 * 7 - Aug,
	 * 8 - Sep,
	 * 9 - Oct,
	 * 10 - Nov,
	 * 11 - Dec
	 */
	public void fruitUpdate(World w, BlockPos p, IBlockState s, Random r, IDate date) {}
	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random) 
	{
		IDate date = world.getCapability(DateProvider.DATE, null);
		this.fruitUpdate(world, pos, state, random, date);
	}
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STAGE);
	}
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, meta);
	}
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {STAGE});
	}

}
