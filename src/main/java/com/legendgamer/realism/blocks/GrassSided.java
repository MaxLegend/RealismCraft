package com.legendgamer.realism.blocks;

import java.util.Random;


import com.legendgamer.realism.API.BasicBlock.BasicBlockFalling;
import com.legendgamer.realism.API.BasicBlock.BasicBlockFallingWithInfo;
import com.legendgamer.realism.API.BasicBlock.BasicBlockWithInfo;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GrassSided extends BasicBlockWithInfo {

	public static final PropertyEnum<EnumState> GRASS_STATE = PropertyEnum.<EnumState>create("grass_state", EnumState.class);


	Block block;
	public GrassSided(Material materialIn, String name, float hardness, float resistanse,
			SoundType soundtype,CreativeTabs tab ,String tooltip, Block setDirt) {
		super(materialIn, name, 0, 0, SoundType.GROUND, tab, tooltip);
		this.setTickRandomly(true);
		block = setDirt;
		Blocks.FIRE.setFireInfo(this, 36, 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(GRASS_STATE, EnumState.DEFAULT));
	}

	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
	{
		return true;
	}
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		if(world.isAirBlock(pos.down())) {
			world.setBlockState(pos, block.getDefaultState());
		}
		if(!world.isAirBlock(pos.up()) && world.isBlockFullCube(pos.up()) ){
			world.setBlockState(pos, block.getDefaultState());
		}
		if(world.getBlockState(fromPos).getBlock() == Blocks.FIRE) {
			world.setBlockState(pos, state.withProperty(GRASS_STATE, EnumState.SCORCH));
		} else
			if(world.getBlockState(pos.up()) == Blocks.SNOW.getDefaultState() || world.getBlockState(pos.up()) == Blocks.SNOW_LAYER.getDefaultState()) {
				world.setBlockState(pos, state.withProperty(GRASS_STATE, EnumState.SNOWY));
			} 
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random)
	{

		//	world.setBlockState(pos, state.withProperty(GRASS_STATE, EnumState.DRY));
		if(world.isAirBlock(pos.up())) {
			if(random.nextInt(650) == 0) {
				world.setBlockState(pos.up(), BlocksList.FERN.getDefaultState());
			}
			else if(random.nextInt(650) == 22) {
				world.setBlockState(pos.up(), BlocksList.SEDGE.getDefaultState());
			}	else if(random.nextInt(650) == 14) {
				world.setBlockState(pos.up(), BlocksList.WIPE.getDefaultState());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	public static enum EnumState implements IStringSerializable {
		DEFAULT("default"),
		SNOWY("snowy"),
		DRY("dry"),
		SCORCH("scorch");

		private final String name;

		private EnumState(String name)
		{
			this.name = name;
		}

		public String toString()
		{
			return this.name;
		}


		public String getName()
		{
			return this.name;
		}

	}
	public IBlockState getStateFromMeta(int meta)
	{
		EnumState grass_state = EnumState.DEFAULT;
		int i = meta & 12;

		if (i == 4)
		{
			grass_state = EnumState.SNOWY;
		}
		else if (i == 8)
		{
			grass_state = EnumState.DRY;
		}
		else if (i == 12)
		{
			grass_state = EnumState.SCORCH;
		}

		return this.getDefaultState().withProperty(GRASS_STATE, grass_state);
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		EnumState grass_state = (EnumState)state.getValue(GRASS_STATE);

		if (grass_state == EnumState.SNOWY)
		{
			i |= 4;
		}
		else if (grass_state == EnumState.DRY)
		{
			i |= 8;
		}
		else if (grass_state == EnumState.SCORCH)
		{
			i |= 12;
		}

		return i;
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {GRASS_STATE});
	}

}
