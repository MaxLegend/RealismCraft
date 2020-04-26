package com.legendgamer.realism.world.biome.base;

import java.util.Random;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeBase extends Biome  {

	
	
	protected IBlockState claystoneBlock;
	protected IBlockState clayBlock;
	protected IBlockState stoneBlock;
	protected IBlockState gravelBlock;
	protected IBlockState sandBlock;

	private final BiomeManager.BiomeType biomeType;

	private final int weight;

	public int colorGrass;
	public int colorFoliage;
	public int colorSky;
	public int colorWater;
	

	
	private final BiomeDictionary.Type[] types;

	public BiomeBase(Biome.BiomeProperties properties, EnumBiomes eBiome) {
		super(properties);

		this.biomeType = eBiome.getBiomeType();
		this.weight = eBiome.getWeight();
		this.types = eBiome.getTypes();
		   this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntitySquid.class, 10, 4, 4));
        this.spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityBat.class, 10, 8, 8));
	}
	public BiomeBase getBiomeBase() {
		return this;
	
	}

	public int getColorBySeasonFoliage(BlockPos pos) {
		World world = Minecraft.getMinecraft().world;
		IDate date = world.getCapability(DateProvider.DATE, null);
		//winter
		if(date.getMonth() == 11|| date.getMonth() == 0||date.getMonth() == 1 ) {
			return 0xbfb32e;
		}
		//spring
	if(date.getMonth() == 2|| date.getMonth() == 3||date.getMonth() == 4 ) {
		return 0x00FF7F;
		}
	//summer
	if(date.getMonth() == 5|| date.getMonth() == 6||date.getMonth() == 7 ) {
		return 0x228B22;
	}
	//autumn
	if(date.getMonth() == 8|| date.getMonth() == 9||date.getMonth() == 10 ) {
		return 0x6B8E23;
	} else {
		return 0x8c2323;
	}
	}
	
	public int getColorBySeasonGrass(BlockPos pos) {
		World world = Minecraft.getMinecraft().world;
		IDate date = world.getCapability(DateProvider.DATE, null);
		//winter
		if(date.getMonth() == 11|| date.getMonth() == 0||date.getMonth() == 1 ) {
			return 0xbfb32e;
		}
		//spring
	if(date.getMonth() == 2|| date.getMonth() == 3||date.getMonth() == 4 ) {
		return 0x00FF7F;
		}
	//summer
	if(date.getMonth() == 5|| date.getMonth() == 6||date.getMonth() == 7 ) {
		return 0x228B22;
	}
	//autumn
	if(date.getMonth() == 8|| date.getMonth() == 9||date.getMonth() == 10 ) {
		return 0x6B8E23;
	} else {
		return 0x8c2323;
	}
		
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		
		return 0x6B8E23;
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		
		return 0x6B8E23;
	}

	@Override
	public void genTerrainBlocks(World w, Random rand, ChunkPrimer setBIC, int x, int z, double noiseVal)
	{
		int i = w.getSeaLevel();
		int actHeight = w.getHeight(x, z);
		IBlockState topBlock = this.topBlock;
		IBlockState fillerBlock = this.fillerBlock;
		IBlockState clayBlock = this.clayBlock;
		IBlockState claystoneBlock = this.claystoneBlock;
		IBlockState stoneBlock = this.stoneBlock;
		IBlockState gravelBlock = this.gravelBlock;
		IBlockState sandBlock = this.sandBlock;
		int j = -1;
		
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int chunkX = x & 15;
		int chunkZ = z & 15;


		for (int j1 = 0; j1 <= 255; ++j1)
		{
			//Ќа высоте от 0 до 5 замен€етс€ на блоки бедрока
			if (j1 <= rand.nextInt(5))
			{
				setBIC.setBlockState(chunkX, j1, chunkZ, BEDROCK);
			} 
			else {
				//ѕолучаем блок чанка на координатах
				IBlockState chunkBlock = setBIC.getBlockState(chunkX, j1, chunkZ);
				IBlockState chunkBlockDown = setBIC.getBlockState(chunkX, j1-1, chunkZ);
				IBlockState chunkBlockUp = setBIC.getBlockState(chunkX, j1+1, chunkZ);
				if (chunkBlock.getMaterial() == Material.AIR)
				{
					j = -1;
				} 
				
	
				if (chunkBlock.getBlock() == Blocks.STONE && chunkBlock.getBlock() != Blocks.AIR)
				{
					if(chunkBlock != Blocks.AIR.getDefaultState()  ) {
						setBIC.setBlockState(chunkX, j1, chunkZ, stoneBlock);
					}
				}
			
				if (chunkBlockDown == stoneBlock && chunkBlockUp == Blocks.AIR.getDefaultState()  )
				{
					setBIC.setBlockState(chunkX, j1, chunkZ, topBlock);
					
					if ( chunkBlockDown == stoneBlock && chunkBlockUp == Blocks.AIR.getDefaultState()  )
					{
						setBIC.setBlockState(chunkX, j1-1, chunkZ, fillerBlock);
						setBIC.setBlockState(chunkX, j1-2, chunkZ, fillerBlock);
						setBIC.setBlockState(chunkX, j1-3, chunkZ, fillerBlock);
						if (chunkBlockDown == stoneBlock && chunkBlockUp == Blocks.AIR.getDefaultState()  )
						{
							setBIC.setBlockState(chunkX, j1-4, chunkZ, clayBlock);
							setBIC.setBlockState(chunkX, j1-5, chunkZ, clayBlock);
							setBIC.setBlockState(chunkX, j1-6, chunkZ, clayBlock);
							if (chunkBlockDown == stoneBlock && chunkBlockUp == Blocks.AIR.getDefaultState()  )
							{
								setBIC.setBlockState(chunkX, j1-7, chunkZ, claystoneBlock);
								setBIC.setBlockState(chunkX, j1-8, chunkZ, claystoneBlock);
								setBIC.setBlockState(chunkX, j1-9, chunkZ, claystoneBlock);
								setBIC.setBlockState(chunkX, j1-10, chunkZ, claystoneBlock);
							}
						}
					}
				}
			}
		}}
	





}
