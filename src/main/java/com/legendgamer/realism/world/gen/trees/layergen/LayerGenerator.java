package com.legendgamer.realism.world.gen.trees.layergen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class LayerGenerator extends WorldGenerator {
	
	@Override
    public boolean generate(World world, Random random, BlockPos pos) {
		
        byte[][][] matrix = get3LayerMatrix();
        for (int py = 0; py < matrix.length; ++py) {
            for (int px = 0; px < matrix[py].length; ++px) {
                for (int pz = 0; pz < matrix[py][px].length; ++pz) {
                    int px2 = px, py2 = py, pz2 = pz;
                    if (needCentering()) {
                        px2 -= matrix[py].length / 2;
                        pz2 -= matrix[py][px].length / 2;
                    }
                    generateBlock(world, random, px2 + pos.getX(), py2+ pos.getY(), pz2+ pos.getZ(), matrix[py][px][pz]);
                }
            }
        }
		return true;

    }


    protected abstract void generateBlock(World world, Random random, int x, int y, int z, int matrixValue);

    protected abstract byte[][][] get3LayerMatrix();

    protected boolean needCentering()
    {
        return true;
    }
}
