package com.legendgamer.realism.blocks.tree;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.API.BasicBlock.BasicLogBlock;
import com.legendgamer.realism.blocks.tree.BlockThinLog.EnumCorner;
import com.legendgamer.realism.blocks.tree.frame.BlockThickBranch.EnumAxis;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoubleThinLog extends BasicBlock {

	private final Map<Integer, Pair<EnumHalf, EnumAxis>> metaToState = new HashMap<>();
	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);
	public static final PropertyEnum<EnumHalf> HALF = PropertyEnum.<EnumHalf>create("half", EnumHalf.class);
	public BlockDoubleThinLog(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		
		for (int i = 0; i < EnumAxis.values().length; i++) {
			//“екущее значение поворота
			EnumAxis axis = EnumAxis.values()[i];
			// j - значение стадии роста
			for (int j = 0; j < EnumHalf.values().length; j++) {
				EnumHalf half = EnumHalf.values()[i];
				metaToState.put((i * EnumHalf.values().length) + j, Pair.of(half, axis));
				
			}
		}
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.X).withProperty(HALF, EnumHalf.BOTTOM));
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		if(hitY <= 0.5F) {
			return this.getDefaultState().withProperty(HALF, EnumHalf.BOTTOM);
		} else 
			if(hitY >= 0.5F) {
				return this.getDefaultState().withProperty(HALF, EnumHalf.TOP);
			} 
		return this.getDefaultState();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	public IBlockState getStateFromMeta(int meta)
	{
		Pair<EnumHalf, EnumAxis> nowPair = metaToState.get(meta);
		if (nowPair != null) {
			return getDefaultState().withProperty(HALF, nowPair.getLeft()).withProperty(AXIS, nowPair.getRight());
		} else return getDefaultState();
	}
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(AXIS).ordinal()*EnumHalf.values().length) + (state.getValue(HALF).ordinal());
	}
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {HALF, AXIS});
	}
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public static enum EnumAxis implements IStringSerializable {
		X("x"),
		Z("z");

		private final String name;

		private EnumAxis(String name)
		{
			this.name = name;
		}

		public String toString()
		{
			return this.name;
		}

		public String getName()
		{
			return this.name;
		}

	}

	public static enum EnumHalf implements IStringSerializable
	{
		TOP("top"),
		BOTTOM("bottom");

		private final String name;

		private EnumHalf(String name)
		{
			this.name = name;
		}

		public String toString()
		{
			return this.name;
		}

		public String getName()
		{
			return this.name;
		}
	}
}
