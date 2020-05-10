package com.legendgamer.realism.world.biome.primary;

import java.util.Random;

import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.biome.base.BiomeBase;
import com.legendgamer.realism.world.biome.base.EnumBiomes;
import com.legendgamer.realism.world.biome.decorator.MetamorphicBiomeDecorator;
import com.legendgamer.realism.world.biome.decorator.SedimentaryBiomeDecorator;
import com.legendgamer.realism.world.biome.primary.MetamorphicBiome.Type;
import com.legendgamer.realism.world.gen.feature.SwampBiomeDecorator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class SedimentaryBiome extends BiomeBase {

			
			
	private final Type type;
	BiomeDecorator bd = new SedimentaryBiomeDecorator();
	public SedimentaryBiome(Biome.BiomeProperties properties, EnumBiomes eBiome, Type type) {
		super(properties, eBiome);
		this.type = type;
		
		this.topBlock = BlocksList.SEDIMENTARY_GRASS.getDefaultState();
		this.fillerBlock = BlocksList.SEDIMENTARY_DIRT.getDefaultState();

        this.clayBlock = BlocksList.SEDIMENTARY_CLAY.getDefaultState();
		this.claystoneBlock = BlocksList.SEDIMENTARY_CLAYSTONE.getDefaultState();
        this.stoneBlock = BlocksList.SEDIMENTARY_STONE.getDefaultState();
		this.sandBlock = BlocksList.SEDIMENTARY_SAND.getDefaultState();
		this.gravelBlock = BlocksList.SEDIMENTARY_GRAVEL.getDefaultState();
	}
	public SwampBiomeDecorator swp = new SwampBiomeDecorator();
	@Override
	public void decorate(World world, Random rand, BlockPos pos)
	{
		if(this.type == Type.SWAMP) {
		
			swp.generate(world, rand, pos);
		}
		 this.bd.decorate(world, rand, this, pos);
	}
	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		return new SedimentaryBiomeDecorator(); 
	}
	public static enum Type {
		//0x38ad0e
		KNOLL(), PLAINS(), LAKE(), SWAMP(), FOREST();


	}
}

