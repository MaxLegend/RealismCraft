package com.legendgamer.realism.blocks.tree;

import java.util.Random;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRealTrees extends BasicLogBlock {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	
	Block branch;
	public BlockRealTrees(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab, Block branch) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.branch = branch;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumAxis.Y).withProperty(STAGE, 0));
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
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
//		int chance = world.rand.nextInt(12);
//		switch (chance) {
//		case 0: if(world.isAirBlock(pos.north())) world.setBlockState(pos.north(), branch.getDefaultState()); break;
//		case 1: if(world.isAirBlock(pos.south())) world.setBlockState(pos.south(), branch.getDefaultState()); break; 
//		case 2: if(world.isAirBlock(pos.east())) world.setBlockState(pos.east(), branch.getDefaultState()); break; 
//		case 3: if(world.isAirBlock(pos.west())) world.setBlockState(pos.west(), branch.getDefaultState()); break; 
//		case 4: if(world.isAirBlock(pos.west()) && world.isAirBlock(pos.east())) world.setBlockState(pos.west(), branch.getDefaultState()); world.setBlockState(pos.east(), branch.getDefaultState()); break; 
//		case 5: if(world.isAirBlock(pos.north()) && world.isAirBlock(pos.south())) world.setBlockState(pos.south(), branch.getDefaultState()); world.setBlockState(pos.south(), branch.getDefaultState()); break; 
//		case 6: if(world.isAirBlock(pos.east()) && world.isAirBlock(pos.south())) world.setBlockState(pos.east(), branch.getDefaultState()); world.setBlockState(pos.south(), branch.getDefaultState()); break; 
//		case 7: if(world.isAirBlock(pos.west()) && world.isAirBlock(pos.south())) world.setBlockState(pos.west(), branch.getDefaultState()); world.setBlockState(pos.south(), branch.getDefaultState()); break; 
//		case 8: if(world.isAirBlock(pos.west()) && world.isAirBlock(pos.north())) world.setBlockState(pos.west(), branch.getDefaultState()); world.setBlockState(pos.north(), branch.getDefaultState()); break; 
//		case 9: if(world.isAirBlock(pos.east()) && world.isAirBlock(pos.north())) world.setBlockState(pos.east(), branch.getDefaultState()); world.setBlockState(pos.north(), branch.getDefaultState()); break; 
//		case 10: if(world.isAirBlock(pos.east()) && world.isAirBlock(pos.north()) && world.isAirBlock(pos.west()) ) world.setBlockState(pos.east(), branch.getDefaultState()); world.setBlockState(pos.north(), branch.getDefaultState()); world.setBlockState(pos.west(), branch.getDefaultState()); break; 
//		case 11: if(world.isAirBlock(pos.east()) && world.isAirBlock(pos.north()) && world.isAirBlock(pos.west()) && world.isAirBlock(pos.south()) ) world.setBlockState(pos.east(), branch.getDefaultState()); world.setBlockState(pos.north(), branch.getDefaultState()); world.setBlockState(pos.west(), branch.getDefaultState()); world.setBlockState(pos.south(), branch.getDefaultState()); break; 

	
				
	}
}
