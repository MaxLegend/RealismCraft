package com.legendgamer.realism.world.gen.feature;

import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.MapGenCaves;

public class RealMapGenCaves extends MapGenCaves {
	
	@Override
	 protected boolean canReplaceBlock(IBlockState state, IBlockState p_175793_2_)
	    {
        if (state.getBlock() == BlocksList.MAGMATIC_CLAY)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.MAGMATIC_DIRT)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.MAGMATIC_CLAYSTONE)
        {
            return true;
        }
        
        if (state.getBlock() == BlocksList.METAMORPHIC_CLAY)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.METAMORPHIC_DIRT)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.METAMORPHIC_CLAYSTONE)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.SEDIMENTARY_CLAY)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.SEDIMENTARY_DIRT)
        {
            return true;
        }
        if (state.getBlock() == BlocksList.SEDIMENTARY_CLAYSTONE)
        {
            return true;
        }
	        if (state.getBlock() == BlocksList.MAGMATIC_STONE)
	        {
	            return true;
	        }
	        else if (state.getBlock() == BlocksList.METAMORPHIC_STONE)
	        {
	            return true;
	        }
	        else if (state.getBlock() == BlocksList.SEDIMENTARY_STONE)
	        {
	            return true;
	        }
	        else if (state.getBlock() == Blocks.HARDENED_CLAY)
	        {
	            return true;
	        }
	        else if (state.getBlock() == Blocks.STAINED_HARDENED_CLAY)
	        {
	            return true;
	        }
	        else if (state.getBlock() == Blocks.SANDSTONE)
	        {
	            return true;
	        }
	        else if (state.getBlock() == Blocks.RED_SANDSTONE)
	        {
	            return true;
	        }
	        else if (state.getBlock() == Blocks.MYCELIUM)
	        {
	            return true;
	        }
	        else if (state.getBlock() == Blocks.SNOW_LAYER)
	        {
	            return true;
	        }
	        else
	        {
	            return (state.getBlock() == BlocksList.MAGMATIC_SAND || state.getBlock() == BlocksList.MAGMATIC_GRAVEL) && p_175793_2_.getMaterial() != Material.WATER;
	        }
	    }
}
