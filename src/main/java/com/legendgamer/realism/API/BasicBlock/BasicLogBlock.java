package com.legendgamer.realism.API.BasicBlock;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
/**
 * Basics API class by Log( Rotate X:Y:Z ) Block
 * @author LegendGamer
 */
public class BasicLogBlock extends BlockRotatedPillar {
	
	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);

	
	public BasicLogBlock(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype,CreativeTabs cTab) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setCreativeTab(cTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.Y));
	
	}
	
	public static enum EnumAxis implements IStringSerializable {
		X("x"),
		Y("y"),
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

		public static BasicLogBlock.EnumAxis fromFacingAxis(Axis axis)
		{
			switch (axis)
			{
			case X:
				return X;
			case Y:
				return Y;
			case Z:
				return Z;
			default:
				return Y;
			}
		}

		public String getName()
		{
			return this.name;
		}
		
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

	public IBlockState getStateFromMeta(int meta)
	{
		EnumAxis enumfacing$axis = EnumAxis.Y;
		int i = meta & 12;

		if (i == 4)
		{
			enumfacing$axis = EnumAxis.X;
		}
		else if (i == 8)
		{
			enumfacing$axis = EnumAxis.Z;
		}

		return this.getDefaultState().withProperty(AXIS, enumfacing$axis);
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		EnumAxis enumfacing$axis = (EnumAxis)state.getValue(AXIS);

		if (enumfacing$axis == EnumAxis.X)
		{
			i |= 4;
		}
		else if (enumfacing$axis == EnumAxis.Z)
		{
			i |= 8;
		}

		return i;
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {AXIS});
	}

	protected ItemStack getSilkTouchDrop(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this));
	}
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
    }

}

