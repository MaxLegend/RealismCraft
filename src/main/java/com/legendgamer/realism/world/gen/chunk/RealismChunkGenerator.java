package com.legendgamer.realism.world.gen.chunk;


import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.world.gen.feature.RealMapGenCaves;
import com.legendgamer.realism.world.gen.layer.GenLayerBiomes;
import com.legendgamer.realism.world.gen.trees.layergen.FoliateTreeGenerator;
import com.legendgamer.realism.world.gen.trees.layergen.PineTreeGenerator;
import com.legendgamer.realism.world.gen.trees.layergen.SpruceTreeGenerator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenStronghold;

public class RealismChunkGenerator extends ChunkGeneratorOverworld implements IChunkGenerator {
	public IBlockState oceanBlock = BlocksList.SALTY_WATER.getDefaultState();
	public MapGenBase caveGenerator = new RealMapGenCaves();

	public TerrainGenerator terrainGen = new TerrainGenerator();
	
	public RealismChunkGenerator(World worldIn, long seed, String generatorOptions) {
		super(worldIn, seed, false, generatorOptions);
		{
			
			caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);

			strongholdGenerator = (MapGenStronghold)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(strongholdGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD);

		}
	} 


	@Override
	public void populate(int x, int z)
	{

		int i = x * 16 ;
		int j = z * 16;

        BlockPos pos = new BlockPos(i, 1, j);

		
		Biome biome = this.world.getBiome(pos.add(16, 0, 16));
		this.rand.setSeed(this.world.getSeed());
	     biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));

	}
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}
	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
	{
		return false;
	}
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
	{

	}
	@Override
	public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
	{
		
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i)
		{
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l)
			{
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2)
				{
					double d0 = 0.125D;
					double d1 = this.heightMap[i1 + i2];
					double d2 = this.heightMap[j1 + i2];
					double d3 = this.heightMap[k1 + i2];
					double d4 = this.heightMap[l1 + i2];
					double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2)
						{
							double d14 = 0.25D;
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2)
							{
								if ((lvt_45_1_ += d16) > 0.0D)
								{
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
								}
								else if (i2 * 8 + j2 < 63)
								{
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}




	@Override
	public Chunk generateChunk(int x, int z)
	{
		this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);

		if (this.settings.useCaves)
		{
			this.caveGenerator.generate(this.world, x, z, chunkprimer);
		}


		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i)
		{
			abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}
	@Override
	public void generateHeightmap(int x, int y, int z)
	{
		terrainGen.generateHeightmap(this, x, y, z);
	}

}