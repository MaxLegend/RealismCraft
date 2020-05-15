package com.legendgamer.realism.world.gen.trees.foliate;

import java.util.Random;

import com.legendgamer.realism.blocks.tree.frame.BlockRealTrees;
import com.legendgamer.realism.blocks.tree.frame.BlockThickBranch;
import com.legendgamer.realism.blocks.worldblock.BlockDirt;
import com.legendgamer.realism.blocks.worldblock.GrassSided;
import com.legendgamer.realism.reg.BlocksList;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenPoplarTree  extends WorldGenerator {

	private final  IBlockState LOG = BlocksList.REAL_POPLAR.getDefaultState().withProperty(BlockRealTrees.STAGE, 6);
	private  IBlockState THICK_BRANCH ;
	
	private final  IBlockState LEAVES = BlocksList.REAL_POPLAR_LEAVES.getDefaultState();

	public boolean generateTree(World w, BlockPos pos) {

		
		ThreadLocalRandom tr = ThreadLocalRandom.current();
		int heightTree = ThreadLocalRandom.current().nextInt(14, 19);
		boolean checkOppGrowth = true;
		THICK_BRANCH = BlocksList.REAL_TB_POPLAR.getDefaultState();
		if (pos.getY() >= 1 && pos.getY() + heightTree + 1 <= 256) {
			for (int i = pos.getY(); i <= pos.getY() + 1 + heightTree; ++i)
			{
				int k = 1;
				BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();
				for (int l = pos.getX() - k; l <= pos.getX() + k; ++l)
				{
					for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k; ++i1)
					{
						if (i >= 0 && i < w.getHeight())
						{
							if (!this.isReplaceable(w, mbp.setPos(l, i, i1)))
							{
								checkOppGrowth = false;
							}
						}
						else
						{
							checkOppGrowth = false;
						}
					}
				}
			}
			if (!checkOppGrowth)
			{
				return false;
			}
			else
			{
				BlockPos down = pos.down();
				IBlockState state = w.getBlockState(down);
				if(state.getBlock() instanceof GrassSided || state.getBlock() instanceof BlockDirt) {

					//сперва генерим ствол, чтобы крона не зан€ла его кусок
					for (int j2 = 0; j2 < heightTree; ++j2)
					{
						BlockPos upN = pos.up(j2);
						IBlockState state2 = w.getBlockState(upN);


						if (state2.getBlock().isAir(state2, w, upN) || state2.getBlock().isLeaves(state2, w, upN))
						{
							this.setBlockAndNotifyAdequately(w, pos.up(j2), LOG);

							if(j2 > 5 && !w.isAirBlock(pos.up(2))) {
								//		if(!w.isAirBlock(pos.up(heightTree))) {
								for(EnumFacing f : EnumFacing.HORIZONTALS) {


									if(f == EnumFacing.EAST && tr.nextInt(2) == 1) {
										int randStage = 0;
										for(int stage = randStage; stage >= 0; stage--) {
										
											this.setBlockAndNotifyAdequately(w, pos.up(j2).offset(f,stage-randStage-1), THICK_BRANCH.withProperty(BlockThickBranch.STAGE, stage).withProperty(BlockThickBranch.AXIS, BlockThickBranch.EnumAxis.X));
										}
									} 

									if(f == EnumFacing.WEST && tr.nextInt(2) == 0) {
										int randStage  = 0;
										for(int stage = randStage; stage >= 0; stage--) {
									
											this.setBlockAndNotifyAdequately(w, pos.up(j2).offset(f,stage-randStage-1), THICK_BRANCH.withProperty(BlockThickBranch.STAGE, stage).withProperty(BlockThickBranch.AXIS, BlockThickBranch.EnumAxis.X));
										}
									} 

									if(f == EnumFacing.SOUTH && tr.nextInt(2) == 1) {
										int randStage  = 0;
										for(int stage = randStage; stage >= 0; stage--) {
							
											this.setBlockAndNotifyAdequately(w, pos.up(j2).offset(f,stage-randStage-1), THICK_BRANCH.withProperty(BlockThickBranch.STAGE, stage).withProperty(BlockThickBranch.AXIS, BlockThickBranch.EnumAxis.Z));
										}
									} 

									if(f == EnumFacing.NORTH && tr.nextInt(2) == 1) {
										int randStage  = 0;
										for(int stage = randStage; stage >= 0; stage--) {
											
											this.setBlockAndNotifyAdequately(w, pos.up(j2).offset(f,stage-randStage-1), THICK_BRANCH.withProperty(BlockThickBranch.STAGE, stage).withProperty(BlockThickBranch.AXIS, BlockThickBranch.EnumAxis.Z));
										}
									} 
								}
							}

						}
					}

					//крона представл€ет собой как бы конус
					for (int heightFoliageGrowth = pos.getY() -11 + heightTree; heightFoliageGrowth <= pos.getY() + heightTree; ++heightFoliageGrowth)
					{
						int k2 = heightFoliageGrowth - (pos.getY() + heightTree - 5);// —тартовый размер каждого сло€ кроны(радиус каждого сло€ конуса)
						int l2 = 1- k2 / 8;// –адиус нижнего сло€ конуса

						for (int sizeLeavesX = pos.getX() - l2; sizeLeavesX <= pos.getX() + l2; ++sizeLeavesX)
						{
							int j1 = sizeLeavesX - pos.getX();

							for (int sizeLeavesZ = pos.getZ() - l2; sizeLeavesZ <= pos.getZ() + l2; ++sizeLeavesZ)
							{
								int l1 = sizeLeavesZ - pos.getZ();

								if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || ThreadLocalRandom.current().nextInt(2) != 0 && k2 != 0)
								{
									BlockPos blockpos = new BlockPos(sizeLeavesX, heightFoliageGrowth, sizeLeavesZ);
									IBlockState state2 = w.getBlockState(blockpos);
									if (state2.getBlock().isAir(state2, w , blockpos) || state2.getBlock().isAir(state2, w, blockpos))
									{
									
										this.setBlockAndNotifyAdequately(w, blockpos, LEAVES);
									}
								}
							}
						}
					}
				

					return true;

				}
				else
				{
					return false;
				}
			}
		} 
		else
		{
			return false;
		}
	}

	protected boolean canGrowInto(Block blockType)
	{
		Material material = blockType.getDefaultState().getMaterial();
		return material == Material.AIR || material == Material.LEAVES || material == Material.WOOD || material == Material.GRASS || material == Material.GROUND;
	}
	public boolean isReplaceable(World world, BlockPos pos)
	{
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getBlock().isWood(world, pos) || canGrowInto(state.getBlock());
	}
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateTree(worldIn, position);
		return true;
	}
}


