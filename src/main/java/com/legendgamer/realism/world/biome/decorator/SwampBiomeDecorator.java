package com.legendgamer.realism.world.biome.decorator;

import java.util.Random;

import com.legendgamer.realism.blocks.worldblock.GrassSided;
import com.legendgamer.realism.reg.BlocksList;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class SwampBiomeDecorator extends WorldGenerator {

	public final IBlockState WATER = BlocksList.SWAMP_WATER.getDefaultState();
	
	@Override
	public boolean generate(World w, Random rand, BlockPos p) {
		ThreadLocalRandom r = ThreadLocalRandom.current();
		int i;

	
	//		System.out.println("ffff" + p);
			if(w.getBlockState(p).getBlock() instanceof GrassSided && r.nextInt(1) == 0) {
		//		System.out.println("ffff22");
				w.setBlockState(p, WATER);
			}
		


		return false;
	}

}
