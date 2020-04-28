package com.legendgamer.realism.blocks.tree.frame;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableSet;
import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.API.BasicBlock.BasicLogBlockTile;
import com.legendgamer.realism.API.BasicBlock.BasicLogBlockTile.EnumAxis;
import com.legendgamer.realism.blocks.tree.RealTreeTileEntity;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockThickBranch extends BasicBlock {

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);

	public BlockThickBranch(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0).withProperty(AXIS, EnumAxis.X));
	}

	//��� ��� ��� ����������� ������. ���� ��������� 2 �������� �� X � Z, � ����� ����� �������� �����.
	//��� �������� 1 ��� (0 - x, 1 - z), ����� ����� - 3 ����. �� ����.. ����, �� � �� ����, ������ ��� �� ��������!!!1!��������
	public IBlockState getStateFromMeta(int meta)
	{
		if(meta < 0) {
			System.out.println("kto-to lox");
			ArrayList<Pair<Integer,EnumAxis>> metaToState = new ArrayList();
			for(EnumAxis s : ImmutableSet.of(EnumAxis.X, EnumAxis.Z)) {
				for(int j = 0; j <= 5; j++) {
					metaToState.add(Pair.of(j, s));
				}
			}
			Pair nowPair = metaToState.get(meta);
			return this.getDefaultState().withProperty(STAGE, (int)nowPair.getLeft()).withProperty(AXIS, (EnumAxis)nowPair.getRight());
		} else {return this.getDefaultState();
		}

	}
	public int getMetaFromState(IBlockState state)
	{
		
		ArrayList<Pair<Integer,EnumAxis>> metaToState = new ArrayList();
		
		for(EnumAxis s : ImmutableSet.of(EnumAxis.X, EnumAxis.Z)) {
			for(int j = 0; j <= 5; j++) {
				metaToState.add(Pair.of(j, s));
			}
		}
		System.out.println("metaToState " + metaToState);
		Pair<Integer,EnumAxis> pair = Pair.of(state.getValue(STAGE), state.getValue(AXIS));
		return metaToState.indexOf(pair);

	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		int i = 0;
		//i = state.getValue(STAGE) << 2;
		//System.out.println(Integer.toBinaryString(i));

		if(state.getValue(STAGE) < 6) world.setBlockState(pos, state.withProperty(STAGE, state.getValue(STAGE)+1));

		return true;
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
	{
		IBlockState state = world.getBlockState(pos);
		for (net.minecraft.block.properties.IProperty<?> prop : state.getProperties().keySet())
		{
			if (prop.getName().equals("axis"))
			{
				world.setBlockState(pos, state.cycleProperty(prop));
				return true;
			}
		}
		return false;
	}
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{

		return this.getStateFromMeta(meta).withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}


	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		switch (rot)
		{
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:

			switch ((EnumAxis)state.getValue(AXIS))
			{
			case X:
				return state.withProperty(AXIS, EnumAxis.Z);
			case Z:
				return state.withProperty(AXIS, EnumAxis.X);
			default:
				return state;
			}

		default:
			return state;
		}
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		if(state.getValue(STAGE) == 6) {
			return true;
		}
		return false;
	} 	


	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {STAGE, AXIS});
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

		public static EnumAxis fromFacingAxis(Axis axis)
		{
			switch (axis)
			{
			case X:
				return X;
			case Z:
				return Z;
			default:
				return X;
			}
		}

		public String getName()
		{
			return this.name;
		}

	}
}
