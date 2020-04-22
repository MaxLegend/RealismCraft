package com.legendgamer.realism.world.gen.trees.layergen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PineTreeGenerator extends LayerGenerator
{
	Block log, branch, leaf;
	public PineTreeGenerator(Block log, Block branch, Block leaf) {
		this.leaf = leaf;
		this.branch = branch;
		this.log = log;
	}
    static final byte[][][] MATRIX = new byte[][][] {
        {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        },{
            {0, 0, 0},
            {0, 2, 0},
            {0, 0, 0}
        },{
            {0, 0, 0},
            {0, 2, 0},
            {0, 0, 0}
        },{
            {0, 0, 0},
            {0, 2, 0},
            {0, 0, 0}
        },{
            {0, 0, 0},
            {0, 2, 0},
            {0, 0, 0}
        },{
            {0, 3, 0},
            {3, 2, 3},
            {0, 3, 0}
        },{
            {0, 0, 4, 0, 0},
            {0, 4, 3, 4, 0},
            {4, 3, 2, 3, 4},
            {0, 4, 3, 4, 0},
            {0, 0, 4, 0, 0}
        },{
        	{0, 0, 4, 0, 0},
            {0, 4, 3, 4, 0},
            {4, 4, 3, 4, 4},
            {3, 3, 2, 3, 3},
            {4, 4, 3, 4, 4},
            {0, 4, 3, 4, 0},
            {0, 0, 4, 0, 0}
        },{
            {0, 4, 4, 4, 0},
            {4, 4, 3, 4, 4},
            {4, 3, 2, 3, 4},
            {4, 4, 3, 4, 4},
            {0, 4, 4, 4, 0}
        },{
            {0, 0, 4, 0, 0},
            {0, 4, 3, 4, 0},
            {4, 3, 2, 3, 4},
            {0, 4, 3, 4, 0},
            {0, 0, 4, 0, 0}
        },{
            {0, 0, 4, 0, 0},
            {0, 4, 4, 4, 0},
            {4, 4, 2, 4, 4},
            {0, 4, 4, 4, 0},
            {0, 4, 4, 0, 0}
        },{
            {4, 4, 4},
            {4, 3, 4},
            {4, 4, 4}
        },{
            {4, 4, 4},
            {4, 3, 4},
            {4, 4, 4}
        },{
            {0, 0, 0},
            {0, 4, 0},
            {0, 0, 0}
            
        }
    }; 

    @Override
    protected byte[][][] get3LayerMatrix()
    {
        return MATRIX;
    }

    @Override
    protected void generateBlock(World world, Random random, int x, int y, int z, int matrixValue)
    {
    	BlockPos pos = new BlockPos(x,y,z);
        switch(matrixValue) {
        case 1:
            world.setBlockState(pos, log.getDefaultState());
            break;
        case 2:
        	 if(world.isAirBlock(pos)) world.setBlockState(pos, log.getDefaultState());
            break;
        case 3:
        	 if(world.isAirBlock(pos)) world.setBlockState(pos, branch.getDefaultState());
        case 4:
       	 if(world.isAirBlock(pos)) world.setBlockState(pos, leaf.getDefaultState());
           
            break;
        }
        
    }
}