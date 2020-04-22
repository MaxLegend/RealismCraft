package com.legendgamer.realism.blocks.tree;

import java.util.Random;

import javax.annotation.Nullable;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.API.BasicBlock.BasicBlockWithCustomModel;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTreeBranch extends BasicBlock{


	public static final PropertyBool DEFAULT = PropertyBool.create("default");

	public static final PropertyBool UP_LV = PropertyBool.create("up_lv");
	public static final PropertyBool DOWN_LV = PropertyBool.create("down_lv");
	public static final PropertyBool NORTH_LV = PropertyBool.create("north_lv");
	public static final PropertyBool EAST_LV = PropertyBool.create("east_lv");
	public static final PropertyBool SOUTH_LV = PropertyBool.create("south_lv");
	public static final PropertyBool WEST_LV = PropertyBool.create("west_lv");

	public static final PropertyBool UP_BR = PropertyBool.create("up_br");
	public static final PropertyBool DOWN_BR = PropertyBool.create("down_br");
	public static final PropertyBool NORTH_BR = PropertyBool.create("north_br");
	public static final PropertyBool EAST_BR = PropertyBool.create("east_br");
	public static final PropertyBool SOUTH_BR = PropertyBool.create("south_br");
	public static final PropertyBool WEST_BR = PropertyBool.create("west_br");
	
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
//		int chance = world.rand.nextInt(20);
//		if(!world.isRemote) {
//			if(chance == 12 || chance == 8) {
//				if(world.isAirBlock(pos.north())) world.setBlockState(pos.north(), this.getDefaultState());
//			} 
//			if(chance == 2 || chance == 5) {
//				if(world.isAirBlock(pos.south())) world.setBlockState(pos.south(), this.getDefaultState());
//			}
//			if(chance == 4 || chance == 6) {
//				if(world.isAirBlock(pos.west())) world.setBlockState(pos.west(), this.getDefaultState());
//			} 
//			if(chance == 13 || chance == 15) {
//				if(world.isAirBlock(pos.east())) world.setBlockState(pos.east(),this.getDefaultState());
//			}
//	
//				for(EnumFacing f : EnumFacing.VALUES) {
//					if(world.isAirBlock(pos.offset(f))) {
//						world.setBlockState(pos.offset(f), leaves.getDefaultState());
//					}
//				}
//			
//		
//		}
	}

	public BlockTreeBranch(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DEFAULT, true));
		this.setTickRandomly(true);

	
	}
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{


		boolean downBR =  canConnectToBranch(worldIn, pos, EnumFacing.DOWN);
		boolean upBR =  canConnectToBranch(worldIn, pos, EnumFacing.UP);
		boolean northBR =  canConnectToBranch(worldIn, pos, EnumFacing.NORTH);
		boolean eastBR = canConnectToBranch(worldIn, pos, EnumFacing.EAST);
		boolean southBR = canConnectToBranch(worldIn, pos, EnumFacing.SOUTH);
		boolean westBR = canConnectToBranch(worldIn, pos, EnumFacing.WEST);

		boolean downLV =  canConnectToLeaves(worldIn, pos, EnumFacing.DOWN);
		boolean upLV =  canConnectToLeaves(worldIn, pos, EnumFacing.UP);
		boolean northLV =  canConnectToLeaves(worldIn, pos, EnumFacing.NORTH);
		boolean eastLV = canConnectToLeaves(worldIn, pos, EnumFacing.EAST);
		boolean southLV = canConnectToLeaves(worldIn, pos, EnumFacing.SOUTH);
		boolean westLV = canConnectToLeaves(worldIn, pos, EnumFacing.WEST);

		return state

				.withProperty(UP_BR, Boolean.valueOf(upBR))
				.withProperty(NORTH_BR, Boolean.valueOf(northBR))
				.withProperty(EAST_BR, Boolean.valueOf(eastBR))
				.withProperty(SOUTH_BR, Boolean.valueOf(southBR))
				.withProperty(WEST_BR, Boolean.valueOf(westBR))
				.withProperty(DOWN_BR, Boolean.valueOf(downBR))

				.withProperty(UP_LV, Boolean.valueOf(upLV))
				.withProperty(NORTH_LV, Boolean.valueOf(northLV))
				.withProperty(EAST_LV, Boolean.valueOf(eastLV))
				.withProperty(SOUTH_LV, Boolean.valueOf(southLV))
				.withProperty(WEST_LV, Boolean.valueOf(westLV))
				.withProperty(DOWN_LV, Boolean.valueOf(downLV));

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
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	private boolean canConnectToBranch(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block connector_branch = world.getBlockState(pos.offset(facing)).getBlock();
		if(connector_branch instanceof BlockRealTrees || connector_branch instanceof BlockTreeBranch) {
			return true;
		} else return false;
	}
	private boolean canConnectToLeaves(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block connector_leaves = world.getBlockState(pos.offset(facing)).getBlock();
		if(connector_leaves instanceof BlockRealLeaves) {
			return true;
		} else return false;
	}


	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(DEFAULT, true);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {DEFAULT, DOWN_BR, UP_BR, NORTH_BR, SOUTH_BR, WEST_BR, EAST_BR, DOWN_LV, UP_LV, NORTH_LV, SOUTH_LV, WEST_LV, EAST_LV});
	}
}
