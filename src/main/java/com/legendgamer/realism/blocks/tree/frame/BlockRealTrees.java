package com.legendgamer.realism.blocks.tree.frame;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRealTrees extends BasicBlock {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	public static final PropertyBool IS_BRANCH_X = PropertyBool.create("is_branch_x");
	public static final PropertyBool IS_BRANCH_Z = PropertyBool.create("is_branch_z");

	protected static final AxisAlignedBB[] AABB_STAGER = new AxisAlignedBB[] {
			new AxisAlignedBB(0.377D, 0.0D, 0.377D, 0.623D, 1D, 0.623D),
			new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 1D, 0.685D),
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1D, 0.75D),
			new AxisAlignedBB(0.19D, 0.0D, 0.19D, 0.81D, 1D, 0.81D),
			new AxisAlignedBB(0.127D, 0.0D, 0.127D, 0.873D, 1D, 0.873D),
			new AxisAlignedBB(0.065D, 0.0D, 0.065D, 0.935D, 1D, 0.935D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	protected Block tb;
	protected int tickUpdate;
	protected Block leaves;
	public BlockRealTrees(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0).withProperty(IS_BRANCH_X, false).withProperty(IS_BRANCH_Z, false));
	}

	public Block getTB() {
		return tb;
	}
	public Block getLeaves() {
		return leaves;
	}
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}
	public void breakBlock(World w, BlockPos pos, IBlockState state)
	{
		boolean isLeaves = true;
		for(EnumFacing f : EnumFacing.VALUES) {
			if(f == EnumFacing.DOWN) {

			} else
				if(w.getBlockState(pos.offset(f)).getBlock() instanceof BlockThickBranch || w.getBlockState(pos.offset(f)).getBlock() instanceof BlockRealTrees || w.getBlockState(pos.offset(f)).getBlock() instanceof BlockRealLeaves ) {

					//если это листва, то дроп не падает \ if it is foliage, then the drop does not fall
					if(w.getBlockState(pos.offset(f)).getBlock() instanceof BlockRealLeaves) {
						isLeaves = false;
						w.destroyBlock(pos.offset(f), isLeaves);
					} 
					w.destroyBlock(pos.offset(f), isLeaves);
				}
		}
	}
	@Override
	public void randomTick(World w, BlockPos pos, IBlockState state, Random r)
	{
		IDate date = w.getCapability(DateProvider.DATE, null);
		if(w.getWorldTime() % getFrequencyTick() == 0) {
			this.lifeActivity(w, pos, state, date, ThreadLocalRandom.current());
		}
	}
	public int getFrequencyTick() {
		return tickUpdate;
	}
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) {
	}
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		//debug tool
	// 	world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	private boolean canConnectToBranch(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block connector_branch = world.getBlockState(pos.offset(facing)).getBlock();
		if( connector_branch == getTB()) {
			return true;
		} else return false;
	}
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{	
		boolean isEast = canConnectToBranch(worldIn, pos, EnumFacing.EAST);
		boolean isWest =  canConnectToBranch(worldIn, pos, EnumFacing.WEST);
		boolean isXConnect = isEast || isWest;
		boolean isSouth = canConnectToBranch(worldIn, pos, EnumFacing.SOUTH);
		boolean isNorth = canConnectToBranch(worldIn, pos, EnumFacing.NORTH);
		boolean isZConnect = isNorth || isSouth;
		return state.withProperty(IS_BRANCH_X, isXConnect).withProperty(IS_BRANCH_Z, isZConnect);
	}
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		switch(state.getValue(STAGE)) {
		case 0:	return AABB_STAGER[0];
		case 1:	return AABB_STAGER[1];
		case 2:	return AABB_STAGER[2];
		case 3:	return AABB_STAGER[3];
		case 4:	return AABB_STAGER[4];
		case 5:	return AABB_STAGER[5];
		case 6:	return AABB_STAGER[6];
		}
		return AABB_STAGER[6];
	}
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(STAGE, meta);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		int stage = state.getValue(STAGE);
		//debug tool - get fast check stage
		//	if(stage < 6) world.setBlockState(pos, state.withProperty(STAGE, stage+1));
		return true;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state)
	{
		if(state.getValue(STAGE) == 6)
		{
			return true;
		}
		return false;
	} 	
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(STAGE);
	}
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { STAGE,IS_BRANCH_X,IS_BRANCH_Z});
	}


}
