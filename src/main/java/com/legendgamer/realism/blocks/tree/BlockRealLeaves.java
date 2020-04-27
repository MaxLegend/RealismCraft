package com.legendgamer.realism.blocks.tree;

import java.util.Random;

import javax.annotation.Nullable;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRealLeaves extends BasicBlock {


		public static final PropertyBool DEFAULT = PropertyBool.create("default");
		public static final PropertyBool UP = PropertyBool.create("up");
		public static final PropertyBool DOWN = PropertyBool.create("down");
		public static final PropertyBool NORTH = PropertyBool.create("north");
		public static final PropertyBool EAST = PropertyBool.create("east");
		public static final PropertyBool SOUTH = PropertyBool.create("south");
		public static final PropertyBool WEST = PropertyBool.create("west");

		public BlockRealLeaves(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
				CreativeTabs tab) {
			super(materialIn, name, hardness, resistanse, soundtype, tab);
			setTickRandomly(true);
			this.setDefaultState(this.blockState.getBaseState().withProperty(DEFAULT, true));

		}
		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
		{
			boolean flag0 =  canLogConnectTo(worldIn, pos, EnumFacing.DOWN);
			boolean flag4 =  canLogConnectTo(worldIn, pos, EnumFacing.UP);
			boolean flag =  canLogConnectTo(worldIn, pos, EnumFacing.NORTH);
			boolean flag1 = canLogConnectTo(worldIn, pos, EnumFacing.EAST);
			boolean flag2 = canLogConnectTo(worldIn, pos, EnumFacing.SOUTH);
			boolean flag3 = canLogConnectTo(worldIn, pos, EnumFacing.WEST);



			return state
					.withProperty(DEFAULT, Boolean.valueOf(true))//!flag4 && !flag0 && !flag && !flag1 && !flag2 && !flag3))
					.withProperty(UP, Boolean.valueOf(flag4))
					.withProperty(NORTH, Boolean.valueOf(flag))
					.withProperty(EAST, Boolean.valueOf(flag1))
					.withProperty(SOUTH, Boolean.valueOf(flag2))
					.withProperty(WEST, Boolean.valueOf(flag3))
					.withProperty(DOWN, Boolean.valueOf(flag0));



		}
		@Override
		public void randomTick(World world, BlockPos pos, IBlockState state, Random random)
		{
			//листва пропадает зимой
			IDate date = world.getCapability(DateProvider.DATE, null);
			if(date.getMonth() == 11|| date.getMonth() == 0||date.getMonth() == 1 ) {
				world.setBlockToAir(pos);
			}
		}
		private boolean canLogConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
		{
			Block connector = world.getBlockState(pos.offset(facing)).getBlock();
			IBlockState state_connector = world.getBlockState(pos.offset(facing));
			if(connector instanceof BlockLeaves || connector instanceof BlockRealLeaves|| connector instanceof BlockTreeNewBranch || connector instanceof BlockLog || connector instanceof BlockRealTrees) {
				return true;
			} else return false;
		}



		@Override
		@SideOnly(Side.CLIENT)
		public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
			return true;
		}
		@Override
		@SideOnly(Side.CLIENT)
		public boolean isFullCube(IBlockState state) {
			return false;
		} 	
		public int getMetaFromState(IBlockState state)
		{
			return 0;
		}

		public Block setNewState() {
			return this;
		}
		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.TRANSLUCENT;
		}

	    @Nullable
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return NULL_AABB;
	    }
		@Override
		protected BlockStateContainer createBlockState()
		{
			return new BlockStateContainer(this, new IProperty[] {DEFAULT, DOWN, UP, NORTH, EAST, WEST, SOUTH});
		}

	}
