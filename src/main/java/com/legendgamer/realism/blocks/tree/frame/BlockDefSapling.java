package com.legendgamer.realism.blocks.tree.frame;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.legendgamer.realism.API.BasicBlock.BasicBlockBush;
import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.gen.trees.foliate.GenAshTree;
import com.legendgamer.realism.world.gen.trees.foliate.GenPoplarTree;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDefSapling extends BasicBlockBush {
	
	protected int tickUpdate;
	
	public BlockDefSapling(Material materialIn, String name, CreativeTabs tab) {
		
		super(materialIn, name, 0, 0, SoundType.PLANT, tab);
	}
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}
	
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) {
	}
	public int getFrequencyTick() {
		return tickUpdate;
	}
	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random) 
	{
		IDate date = world.getCapability(DateProvider.DATE, null);
		if(world.getWorldTime() % getFrequencyTick() == 0) {
			this.lifeActivity(world, pos, state, date, ThreadLocalRandom.current());
		}
	}
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		//debug tool - test worldgen tree
//		if(!world.isRemote) {
//			new GenAshTree().generate(world, world.rand, pos);
//			world.setBlockState(pos, BlocksList.REAL_ASH.getDefaultState().withProperty(BlockRealTrees.STAGE, 6));
//			//	new FoliateTreeGenerator(logBlock, branchBlock, leavesBlock).generate(world, world.rand, pos);
//			return true;
//		}
		return true;
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

}
