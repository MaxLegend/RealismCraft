package com.legendgamer.realism.world.gen.chunk;

import com.legendgamer.realism.Realism;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBlocks;
import com.legendgamer.realism.reg.RegDim;
import com.legendgamer.realism.world.biome.RealismBiomeProvider;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class RealismWorldProvider extends WorldProvider {
	@Override
	public void init() {
		this.hasSkyLight = true;
		this.biomeProvider = new RealismBiomeProvider(this.world.getWorldInfo());
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new RealismChunkGenerator(this.world, this.world.getSeed(), this.world.toString());
	}

	@Override
	public DimensionType getDimensionType() {
		return RegDim.REALISM_DIM;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public String getSaveFolder() {
		return Realism.MODID + "/" + "rDim";
	}
	@Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        BlockPos blockpos = new BlockPos(x, 0, z);

        if (this.world.getBiome(blockpos).ignorePlayerSpawnSuitability())
        {
            return true;
        }
        else
        {
            return this.world.getGroundAboveSeaLevel(blockpos).getBlock() == BlocksList.MAGMATIC_GRASS;
        }
    }

}
