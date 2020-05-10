package com.legendgamer.realism.blocks.tree.frame;

import java.util.Random;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.blocks.GrassSided;
import com.legendgamer.realism.blocks.tree.util.EnumTreeType;
import com.legendgamer.realism.blocks.tree.util.ITreeType;
import com.legendgamer.realism.reg.BlocksList;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRealTrees extends BasicBlock implements ITreeType{
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	public static final PropertyBool IS_BRANCH_X = PropertyBool.create("is_branch_x");
	public static final PropertyBool IS_BRANCH_Z = PropertyBool.create("is_branch_z");
	EnumTreeType type;
	protected static final AxisAlignedBB[] AABB_STAGER = new AxisAlignedBB[] {
			new AxisAlignedBB(0.377D, 0.0D, 0.377D, 0.623D, 1D, 0.623D),
			new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 1D, 0.685D),
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1D, 0.75D),
			new AxisAlignedBB(0.19D, 0.0D, 0.19D, 0.81D, 1D, 0.81D),
			new AxisAlignedBB(0.127D, 0.0D, 0.127D, 0.873D, 1D, 0.873D),
			new AxisAlignedBB(0.065D, 0.0D, 0.065D, 0.935D, 1D, 0.935D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	IBlockState branch;
	public BlockRealTrees(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0).withProperty(IS_BRANCH_X, false).withProperty(IS_BRANCH_Z, false));
	}
    public void onBlockDestroyedByPlayer(World w, BlockPos pos, IBlockState state)
    {
//	state.getBlock().breakBlock(w,  pos,  state);
    }
	public void neighborChanged(IBlockState state, World w, BlockPos pos, Block blockIn, BlockPos fromPos)
    {

   
    }
	@Override
	public EnumTreeType getType() {
		return type;
	}
	@Override
	public Block setType(EnumTreeType type) {
		this.type = type;
		return this;
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
         		
         		if(w.getBlockState(pos.offset(f)).getBlock() instanceof BlockRealLeaves) {
         			isLeaves = false;
         			 w.destroyBlock(pos.offset(f), isLeaves);
         		} 
         		 w.destroyBlock(pos.offset(f), isLeaves);
         	}
         }
    }

//	@Override
//	public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
//	{
//		world.scheduleUpdate(pos, this, 60);
//
//		int height = getHeightTrunk(5,10);
//		chanceBranch = ThreadLocalRandom.current().nextInt(0,5);
//		int stage = state.getValue(STAGE);
//		if(world.getBlockState(pos.down(height)).getBlock() != this) {
//			if(stage < 6) { 
//
//				world.setBlockState(pos, state.withProperty(STAGE, stage+1));
//
//				if(world.getBlockState(pos.down()).getBlock() instanceof BlockRealTrees) {
//
//					if(world.getBlockState(pos.down()).getValue(STAGE) < 2)  {
//						world.setBlockState(pos.up(), state.withProperty(STAGE, stage));
//					}
//				} else if(world.getBlockState(pos.down()).getBlock() instanceof GrassSided) {
//					world.setBlockState(pos.up(), state.withProperty(STAGE, stage));
//				}
//
//				int heightspawn = 4;
//				for(EnumFacing f : EnumFacing.HORIZONTALS) {
//					if(f.getAxis() == EnumFacing.Axis.X) {
//						//	System.out.println("pos " + world.isAirBlock(pos.down(heightspawn  + 1)));
//						if(world.getBlockState(pos.down(heightspawn)).getBlock() == this && world.isAirBlock(pos.offset(f)) && random.nextInt(2) == 1) {
//							world.setBlockState(pos.offset(f), BlocksList.REAL_TB_ASH.getDefaultState().withProperty(BlockThickBranch.AXIS, BlockThickBranch.EnumAxis.X));
//
//						}
//					} 
//					if(f.getAxis() == EnumFacing.Axis.Z) {
//						if(world.getBlockState(pos.down(heightspawn)).getBlock() == this&& world.isAirBlock(pos.offset(f))&& random.nextInt(2) == 1) {
//							world.setBlockState(pos.offset(f), BlocksList.REAL_TB_ASH.getDefaultState().withProperty(BlockThickBranch.AXIS, BlockThickBranch.EnumAxis.Z));
//						}
//					}
//				}
//				for(EnumFacing f : EnumFacing.VALUES) {
//					if(world.getBlockState(pos.offset(f)) == Blocks.AIR.getDefaultState()) {
//						world.setBlockState(pos.offset(f), BlocksList.REAL_ASH_LEAVES.getDefaultState());
//					}
//				}
//			}
//		}
//	}
//	int maxHeightTree;
//	int chanceBranch;
//	public int getHeightTrunk(int min, int max) {
//		maxHeightTree = ThreadLocalRandom.current().nextInt(min,max);
//		return maxHeightTree;
//	}
//	public IBlockState getThickBranch() {
//		return branch;	
//	}
//	public int getHeightSpawnBranch(int min, int max) {
//		chanceBranch = ThreadLocalRandom.current().nextInt(min,max);
//		int heightspawn = getHeightTrunk(min, max) - chanceBranch;
//		return heightspawn;
//	}
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	private boolean canConnectToBranch(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block connector_branch = world.getBlockState(pos.offset(facing)).getBlock();
		if( connector_branch instanceof BlockTreeNewBranch || connector_branch instanceof BlockThickBranch) {
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
		if(stage < 6) world.setBlockState(pos, state.withProperty(STAGE, stage+1));
		return true;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		if(state.getValue(STAGE) == 6) {
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
