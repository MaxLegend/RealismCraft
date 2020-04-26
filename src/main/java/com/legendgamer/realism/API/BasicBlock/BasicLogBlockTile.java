package com.legendgamer.realism.API.BasicBlock;

import java.util.Random;

import com.legendgamer.realism.blocks.tree.RealTreeTileEntity;
import com.legendgamer.realism.reg.BlocksList;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.Block;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicLogBlockTile extends BlockTileEntity {
	protected static final AxisAlignedBB[] AABB_STAGER = new AxisAlignedBB[] {
			new AxisAlignedBB(0.377D, 0.0D, 0.377D, 0.623D, 1D, 0.623D),
			new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 1D, 0.685D),
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1D, 0.75D),
			new AxisAlignedBB(0.19D, 0.0D, 0.19D, 0.81D, 1D, 0.81D),
			new AxisAlignedBB(0.127D, 0.0D, 0.127D, 0.873D, 1D, 0.873D),
			new AxisAlignedBB(0.065D, 0.0D, 0.065D, 0.935D, 1D, 0.935D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	protected static final AxisAlignedBB[] AABB_STAGER_X = new AxisAlignedBB[] {
			new AxisAlignedBB(0D, 0.377D, 0.377D, 1D, 0.623D, 0.623D),
			new AxisAlignedBB(0D, 0.315D, 0.315D, 1D, 0.685D, 0.685D),
			new AxisAlignedBB(0D, 0.25D, 0.25D, 1D, 0.75D, 0.75D),
			new AxisAlignedBB(0D, 0.19D, 0.19D, 1D, 0.81D, 0.81D),
			new AxisAlignedBB(0D, 0.127D, 0.127D, 1D, 0.873D, 0.873D),
			new AxisAlignedBB(0D, 0.065D, 0.065D, 1D, 0.935D, 0.935D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	protected static final AxisAlignedBB[] AABB_STAGER_Z = new AxisAlignedBB[] {
			new AxisAlignedBB(0.377D, 0.377D, 0D, 0.623D,  0.623D, 1D),
			new AxisAlignedBB(0.315D, 0.315D, 0D, 0.685D, 0.685D, 1D),
			new AxisAlignedBB(0.25D, 0.25D, 0D, 0.75D, 0.75D, 1D),
			new AxisAlignedBB(0.19D, 0.19D, 0D, 0.81D, 0.81D, 1D),
			new AxisAlignedBB(0.127D, 0.127D, 0D, 0.873D, 0.873D, 1D),
			new AxisAlignedBB(0.065D, 0.065D, 0D, 0.935D, 0.935D, 1D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	public Block branch;
	//	public Block leaves;
	public BasicLogBlockTile(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype,CreativeTabs cTab, Block branch) {
		super(name, materialIn, resistanse, resistanse, soundtype, cTab);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setCreativeTab(cTab);
		this.setTickRandomly(true);
		this.branch = branch;
		//	this.leaves = leaves;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.Y).withProperty(STAGE, 0));

	}
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if(state.getValue(AXIS) == EnumAxis.Y) {
			switch(state.getValue(STAGE)) {
			case 0:	return AABB_STAGER[0];
			case 1:	return AABB_STAGER[1];
			case 2:	return AABB_STAGER[2];
			case 3:	return AABB_STAGER[3];
			case 4:	return AABB_STAGER[4];
			case 5:	return AABB_STAGER[5];
			case 6:	return AABB_STAGER[6];
			}
		}
		if(state.getValue(AXIS) == EnumAxis.X) {
			switch(state.getValue(STAGE)) {
			case 0:	return AABB_STAGER_X[0];
			case 1:	return AABB_STAGER_X[1];
			case 2:	return AABB_STAGER_X[2];
			case 3:	return AABB_STAGER_X[3];
			case 4:	return AABB_STAGER_X[4];
			case 5:	return AABB_STAGER_X[5];
			case 6:	return AABB_STAGER_X[6];
			}
		}
		if(state.getValue(AXIS) == EnumAxis.Z) {
			switch(state.getValue(STAGE)) {
			case 0:	return AABB_STAGER_Z[0];
			case 1:	return AABB_STAGER_Z[1];
			case 2:	return AABB_STAGER_Z[2];
			case 3:	return AABB_STAGER_Z[3];
			case 4:	return AABB_STAGER_Z[4];
			case 5:	return AABB_STAGER_Z[5];
			case 6:	return AABB_STAGER_Z[6];
			}
		}
		return new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D);
	}
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
		int stage = state.getValue(STAGE);
		if(stage < 6) world.setBlockState(pos, state.withProperty(STAGE, stage+1));
		return true;
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

		public static BasicLogBlockTile.EnumAxis fromFacingAxis(Axis axis)
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


	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{


		RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
		if(te.stage > 0) {
			return state.withProperty(STAGE, te.stage);
		}
		return state;
	}

	public void growBlock(World world, BlockPos pos, IBlockState state) {

		RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
		if(te.stage >= 6) {

			int stage = te.stage;
			world.removeTileEntity(pos);
			world.setBlockState(pos, state.withProperty(STAGE, 6));
			RealTreeTileEntity tile = (RealTreeTileEntity)world.getTileEntity(pos);
			tile.stage = 6;
		} else 
			if(te.stage < 6) {
				int stage = te.stage;
				world.removeTileEntity(pos);
				world.setBlockState(pos, state.withProperty(STAGE, stage+1));
				RealTreeTileEntity tile = (RealTreeTileEntity)world.getTileEntity(pos);
				tile.stage = stage + 1;
			}

	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
	{
		if(!world.isRemote) {
			RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
			world.scheduleUpdate(pos, this, 60);
			//				int maxHeightTree = 0;
			//				if(world.getBlockState(pos.down()) != this && state.getValue(STAGE) == 0) {
			//					maxHeightTree = ThreadLocalRandom.current().nextInt(5,8);
			//				//	System.out.println("maxHeightTree "+ te.stage );
			//				}
			//		
			//				if(world.isAirBlock(pos.up()) && state.getValue(AXIS) == EnumAxis.Y ) {
			//					if(world.getBlockState(pos.down(maxHeightTree)).getBlock() != this) {
			//						world.setBlockState(pos.up(), state);
			//					}
			//					for(EnumFacing f : EnumFacing.HORIZONTALS) {
			//						if(world.isAirBlock(pos.offset(f))) {
			//							world.setBlockState(pos.offset(f), BlocksList.REAL_BIRCH_LEAVES.getDefaultState());
			//						}
			//					}
		}

		this.growBlock(world, pos, state);
		//	}
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

		int stage = state.getValue(STAGE);

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
		return new BlockStateContainer(this, new IProperty[] {AXIS, STAGE});
	}

	protected ItemStack getSilkTouchDrop(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this));
	}
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getStateFromMeta(meta).withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}

	@Override
	public Class getTileEntityClass() {

		return RealTreeTileEntity.class;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState blockState) {

		return new RealTreeTileEntity();
	}

}


