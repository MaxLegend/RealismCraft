package com.legendgamer.realism.blocks.tree.frame;

import java.util.Random;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.API.BasicBlock.BasicLogBlock;
import com.legendgamer.realism.API.BasicBlock.BasicLogBlock.EnumAxis;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRealTrees extends BasicBlock {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	protected static final AxisAlignedBB[] AABB_STAGER = new AxisAlignedBB[] {
			new AxisAlignedBB(0.377D, 0.0D, 0.377D, 0.623D, 1D, 0.623D),
			new AxisAlignedBB(0.315D, 0.0D, 0.315D, 0.685D, 1D, 0.685D),
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1D, 0.75D),
			new AxisAlignedBB(0.19D, 0.0D, 0.19D, 0.81D, 1D, 0.81D),
			new AxisAlignedBB(0.127D, 0.0D, 0.127D, 0.873D, 1D, 0.873D),
			new AxisAlignedBB(0.065D, 0.0D, 0.065D, 0.935D, 1D, 0.935D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	Block branch;
	public BlockRealTrees(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab, Block branch) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.branch = branch;
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
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
		return new BlockStateContainer(this, new IProperty[] { STAGE});
	}
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
				
	}
}
