package com.legendgamer.realism.blocks;

import java.util.Random;

import com.legendgamer.realism.API.BasicBlock.BasicBlockBush;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TallGrassAgeble extends BasicBlockBush {

	public Block block;
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);
	protected static final AxisAlignedBB[] BUSH_AABB_STAGER = new AxisAlignedBB[] {
			new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.2D, 0.7D),
			new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.3D, 0.7D),
			new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.5D, 0.7D),
			new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.8D, 0.7D)
	};

	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(AGE, meta);

	}
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(AGE);

	}
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
    	return true;
    }
	  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
		   super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
	        this.checkAndDropBlock(worldIn, pos, state);
		  
	    }
	public TallGrassAgeble(Material materialIn, String name, SoundType st, CreativeTabs tab, Block block) {
		super(materialIn, name, 0, 0, st, tab);
		this.setTickRandomly(true);
		this.block = block;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
	}


	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		switch(state.getValue(AGE)) {
		case 0:	return BUSH_AABB_STAGER[0];
		case 1:	return BUSH_AABB_STAGER[1];
		case 2:	return BUSH_AABB_STAGER[2];
		case 3:	return BUSH_AABB_STAGER[3];
		}
		return BUSH_AABB_STAGER[0];
	}
	protected PropertyInteger getAgeProperty()
	{
		return AGE;
	}

	public int getMaxAge()
	{
		return 3;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{

	super.updateTick(worldIn, pos, state, rand);

		if(state.getValue(AGE) == 0 && worldIn.getBlockLightOpacity(pos.up()) <= 4 && rand.nextInt(25) == 4) {
			worldIn.setBlockState(pos, state.withProperty(AGE, 1));
		} else
			if(state.getValue(AGE) == 1 && worldIn.getBlockLightOpacity(pos.up()) <= 4 && rand.nextInt(22) == 5) {
				worldIn.setBlockState(pos, state.withProperty(AGE, 2));

			} else if(state.getValue(AGE) == 2 && worldIn.getBlockLightOpacity(pos.up()) <= 4 && rand.nextInt(21) == 4) {
				worldIn.setBlockState(pos, state.withProperty(AGE, 3));

			}else if(state.getValue(AGE) == 3) {


			}


	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos.down()).isTopSolid(); 
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {AGE});
	}

}
