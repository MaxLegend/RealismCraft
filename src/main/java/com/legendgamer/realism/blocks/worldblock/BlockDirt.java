package com.legendgamer.realism.blocks.worldblock;

import java.util.Random;

import com.legendgamer.realism.API.BasicBlock.BasicBlockWithInfo;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDirt extends BasicBlockWithInfo {

	public BlockDirt(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab, String tooltip) {
		super(materialIn, name, hardness, resistanse, soundtype, tab, tooltip);

		this.setTickRandomly(true);
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random)
	{
		if (!world.isRemote)
		{
			IBlockState gb_mgm = BlocksList.MAGMATIC_GRASS.getDefaultState();
			IBlockState gb_sed = BlocksList.SEDIMENTARY_GRASS.getDefaultState();
			IBlockState gb_mtm = BlocksList.METAMORPHIC_GRASS.getDefaultState();
			if(world.isAirBlock(pos.up()) && world.getBlockLightOpacity(pos.up()) <= 4 && world.rand.nextInt(14) == 3){

				if(this == BlocksList.MAGMATIC_DIRT) {

					world.setBlockState(pos, gb_mgm);
				}
				if(this == BlocksList.SEDIMENTARY_DIRT) {

					world.setBlockState(pos, gb_sed);
				}
				if(this == BlocksList.METAMORPHIC_DIRT) {

					world.setBlockState(pos, gb_mtm);
				}
			}
		}
	}
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{

		if (!world.isRemote)
		{

			this.checkFallableDown(world, pos);

			if(!isExistCheckSideBlock(world, pos))  {
				this.checkFallableWest(world, pos);
			}
			if(!isExistCheckSideBlock(world, pos))  {
				this.checkFallableEast(world, pos);
			}
			if(!isExistCheckSideBlock(world, pos))  {
				this.checkFallableNorth(world, pos);
			}
			if(!isExistCheckSideBlock(world, pos))  {
				this.checkFallableSouth(world, pos);
			}

		}
	}
	public boolean isExistCheckSideBlock(World w, BlockPos pos) {
		int count = 0;
		for(EnumFacing f : EnumFacing.HORIZONTALS) {
			if(!w.isAirBlock(pos.offset(f))) {
				count++;
			}
		}
		if(count >= 2) {
			return true;
		} else
			return false;

	}
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(entity instanceof EntityFallingBlock) {
			EntityFallingBlock efb = (EntityFallingBlock)entity;
			this.isCollidedEFB(efb, world, pos);		
		}

	}

	public boolean isCollidedEFB(EntityFallingBlock efb, World w, BlockPos pos) {
		if(efb.collided) {
			return false;
		} else return true;

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
					if(isCollidedEFB(efb, world, pos)) {
						this.onStartFalling(efb);
						world.spawnEntity(efb);
					}
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
					if(isCollidedEFB(efb, world, pos)) {
						this.onStartFalling(efb);
						world.spawnEntity(efb);
					}
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
					if(isCollidedEFB(efb, world, pos)) {
						this.onStartFalling(efb);
						world.spawnEntity(efb);
					}
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
					if(isCollidedEFB(efb, world, pos)) {
						this.onStartFalling(efb);
						world.spawnEntity(efb);
					}
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
}
