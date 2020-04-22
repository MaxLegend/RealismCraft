package com.legendgamer.realism.API.BasicBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/**
 * Basics API class by Block Falling
 * Improved physics - blocks fall not only down, but also diagonally.
 * @author LegendGamer
 */
public class BasicBlockFalling extends BasicBlock
{
	public BasicBlockFalling(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,CreativeTabs tab)
	{
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{

		if (!world.isRemote)
		{
			this.checkFallableDown(world, pos);
			this.checkFallableWest(world, pos);
			this.checkFallableEast(world, pos);
			this.checkFallableNorth(world, pos);
			this.checkFallableSouth(world, pos);
		}
	}

	private void checkFallableDown(World world, BlockPos pos)
	{	
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())) {
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos));
					this.onStartFalling(efb);
					world.spawnEntity(efb);
				}
			}
		}
	}
	private void checkFallableSouth(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.south())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.south()));
					this.onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ() + 1))) {

					world.setBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ() + 1), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	private void checkFallableNorth(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.north())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.north()));
					this.onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ() - 1))) {

					world.setBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ() - 1), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	private void checkFallableWest(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.west())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.west()));
					this.onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX() + 1,pos.getY() - 1,pos.getZ()))) {

					world.setBlockState(new BlockPos(pos.getX() + 1,pos.getY(),pos.getZ()), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	private void checkFallableEast(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.east())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.east()));
					this.onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX() - 1,pos.getY() - 1,pos.getZ()))) {

					world.setBlockState(new BlockPos(pos.getX() - 1,pos.getY(),pos.getZ()), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}

	protected void onStartFalling(EntityFallingBlock fallingEntity)
	{
	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World worldIn)
	{
		return 2;
	}

	public static boolean canFallThrough(IBlockState state)
	{
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
	}

	public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_)
	{
	}

	public void onBroken(World worldIn, BlockPos pos)
	{
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (rand.nextInt(16) == 0)
		{
			BlockPos blockpos = pos.down();

			if (canFallThrough(worldIn.getBlockState(blockpos)))
			{
				double d0 = (double)((float)pos.getX() + rand.nextFloat());
				double d1 = (double)pos.getY() - 0.05D;
				double d2 = (double)((float)pos.getZ() + rand.nextFloat());
				worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(stateIn));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getDustColor(IBlockState state)
	{
		return -16777216;
	}
}