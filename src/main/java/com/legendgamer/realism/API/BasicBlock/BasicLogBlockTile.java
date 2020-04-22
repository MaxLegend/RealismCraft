package com.legendgamer.realism.API.BasicBlock;

import java.util.Random;

import com.legendgamer.realism.API.BasicBlock.BasicLogBlockTile.EnumAxis;
import com.legendgamer.realism.blocks.tree.RealTreeTileEntity;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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

public class BasicLogBlockTile extends BlockTileEntity {

	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	public Block branch;
	public BasicLogBlockTile(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype,CreativeTabs cTab, Block branch) {
		super(name, materialIn, resistanse, resistanse, soundtype, cTab);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setCreativeTab(cTab);
		this.setTickRandomly(true);
		this.branch = branch;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.Y).withProperty(STAGE, 0));

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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote) {
			//			RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
			//			world.scheduleUpdate(pos, this, 20);
			//			//te.saveStage(state.getValue(STAGE));;
			//
			//			te.stage = state.getValue(STAGE);
			//
			//			if(te.stage <= 6) {
			//				te.stage++;
			//				world.setBlockState(pos, state.withProperty(STAGE, te.stage));
			//				System.out.println(" te.stage2 " +  te.stage);
			//				 
			//				te.saveStage(te.stage);
			//			}

			return true;
		}
		return true;
	}

	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{

		world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{

//		RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
//		if(te.stage > 0) {
//			return state.withProperty(STAGE, te.stage);
//		
		return state;
	}


	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
	{

//		RealTreeTileEntity te = (RealTreeTileEntity)this.getTileEntity(world, pos);
//		world.scheduleUpdate(pos, this, 60);
//		if(te.stage > 0) {
//		//	System.out.println("te.stage " + te.stage);
//		//	world.setBlockState(pos, state.withProperty(STAGE, te.stage));
//			if(te.stage < 6) {
//				world.setBlockState(pos, state.withProperty(STAGE, te.stage+1));
//				System.out.println("te.stage2 " + te.stage);
//				te.saveStage(te.stage+1);
//			
//			}
//		}
////		} else {
////			System.out.println("else te.stage " + te.stage);
//			te.stage = state.getValue(STAGE);
//			System.out.println("else te.stagepost " + te.stage);
//		}
		//	stage = te.getStage();
	


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


