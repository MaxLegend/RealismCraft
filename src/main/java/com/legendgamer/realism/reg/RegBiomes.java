package com.legendgamer.realism.reg;

import com.legendgamer.realism.world.biome.PondBiome;
import com.legendgamer.realism.world.biome.RiverBiome;
import com.legendgamer.realism.world.biome.ĄdditionalsBiomes;
import com.legendgamer.realism.world.biome.base.EnumBiomes;
import com.legendgamer.realism.world.biome.primary.MagmaticBiome;
import com.legendgamer.realism.world.biome.primary.MetamorphicBiome;
import com.legendgamer.realism.world.biome.primary.SedimentaryBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegBiomes {

	public static final BiomeDictionary.Type MAGMATIC_TYPE = BiomeDictionary.Type.getType("magmatic_type");
	public static final BiomeDictionary.Type SEDIMENTARY_TYPE = BiomeDictionary.Type.getType("sedimentary_type");
	public static final BiomeDictionary.Type METAMORPHIC_TYPE = BiomeDictionary.Type.getType("metamorphic_type");
	


	public static final Biome MAGMATIC_EDGE= new MagmaticBiome(new Biome.BiomeProperties("Magmatic Edge").setBaseHeight(5F).setHeightVariation(0.18F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.MAGMATIC, MagmaticBiome.Type.EDGE);
	public static final Biome MAGMATIC_HILLS = new MagmaticBiome(new Biome.BiomeProperties("Magmatic Hills").setBaseHeight(5F).setHeightVariation(0.18F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.MAGMATIC, MagmaticBiome.Type.HILLS);
	public static final Biome MAGMATIC_PLAINS = new MagmaticBiome(new Biome.BiomeProperties("Magmatic Plains").setBaseHeight(4.7F).setHeightVariation(0.2F).setTemperature(1F).setRainfall(0.6F), EnumBiomes.MAGMATIC, MagmaticBiome.Type.PLAINS);
	public static final Biome MAGMATIC_LAKE = new MagmaticBiome(new Biome.BiomeProperties("Magmatic Lake").setBaseHeight(4.3F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.MAGMATIC, MagmaticBiome.Type.LAKE);
	public static final Biome MAGMATIC_SWAMP = new MagmaticBiome(new Biome.BiomeProperties("Magmatic Swamp").setBaseHeight(4F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.MAGMATIC, MagmaticBiome.Type.SWAMP);
	public static final Biome MAGMATIC_FOREST = new MagmaticBiome(new Biome.BiomeProperties("Magmatic Forest").setBaseHeight(4.75F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.MAGMATIC, MagmaticBiome.Type.FOREST);
	
	public static final Biome SEDIMENTARY_EDGE = new SedimentaryBiome(new Biome.BiomeProperties("Sedimentary Edge").setBaseHeight(4.24F).setHeightVariation(0.12F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.SEDIMENTARY, SedimentaryBiome.Type.EDGE);
	public static final Biome SEDIMENTARY_KNOLL = new SedimentaryBiome(new Biome.BiomeProperties("Sedimentary Knoll").setBaseHeight(4.24F).setHeightVariation(0.2F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.SEDIMENTARY, SedimentaryBiome.Type.KNOLL);
	public static final Biome SEDIMENTARY_PLAINS = new SedimentaryBiome(new Biome.BiomeProperties("Sedimentary Plains").setBaseHeight(4.2F).setHeightVariation(0.1F).setTemperature(1.6F).setRainfall(0.6F), EnumBiomes.SEDIMENTARY, SedimentaryBiome.Type.PLAINS);
	public static final Biome SEDIMENTARY_LAKE = new SedimentaryBiome(new Biome.BiomeProperties("Sedimentary Lake").setBaseHeight(4F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.SEDIMENTARY, SedimentaryBiome.Type.LAKE);
	public static final Biome SEDIMENTARY_SWAMP = new SedimentaryBiome(new Biome.BiomeProperties("Sedimentary Swamp").setBaseHeight(3.9F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.SEDIMENTARY, SedimentaryBiome.Type.SWAMP);
	public static final Biome SEDIMENTARY_FOREST = new SedimentaryBiome(new Biome.BiomeProperties("Sedimentary Forest").setBaseHeight(4.25F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.SEDIMENTARY, SedimentaryBiome.Type.FOREST);
	
	public static final Biome METAMORPHIC_EDGE = new MetamorphicBiome(new Biome.BiomeProperties("Metamorphic Edge").setBaseHeight(4.8F).setHeightVariation(0.12F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.METAMORPHIC, MetamorphicBiome.Type.EDGE);
	public static final Biome METAMORPHIC_HILLS = new MetamorphicBiome(new Biome.BiomeProperties("Metamorphic Hills").setBaseHeight(4.8F).setHeightVariation(0.12F).setTemperature(0.2F).setRainfall(0.7F), EnumBiomes.METAMORPHIC, MetamorphicBiome.Type.HILLS);
	public static final Biome METAMORPHIC_PLAINS = new MetamorphicBiome(new Biome.BiomeProperties("Metamorphic Plains").setBaseHeight(4.5F).setHeightVariation(0.1F).setTemperature(1.2F).setRainfall(0.6F), EnumBiomes.METAMORPHIC, MetamorphicBiome.Type.PLAINS);
	public static final Biome METAMORPHIC_LAKE = new MetamorphicBiome(new Biome.BiomeProperties("Metamorphic Lake").setBaseHeight(4.2F).setHeightVariation(0.1F).setTemperature(1.2F).setRainfall(0.6F), EnumBiomes.METAMORPHIC, MetamorphicBiome.Type.LAKE);
	public static final Biome METAMORPHIC_SWAMP = new MetamorphicBiome(new Biome.BiomeProperties("Metamorphic Swamp").setBaseHeight(4F).setHeightVariation(0.1F).setTemperature(1.2F).setRainfall(0.6F), EnumBiomes.METAMORPHIC, MetamorphicBiome.Type.SWAMP);
	public static final Biome METAMORPHIC_FOREST = new MetamorphicBiome(new Biome.BiomeProperties("Metamorphic Forest").setBaseHeight(4.55F).setHeightVariation(0.1F).setTemperature(1.2F).setRainfall(0.6F), EnumBiomes.METAMORPHIC, MetamorphicBiome.Type.FOREST);

	
	public static final Biome OCEAN = new PondBiome(new Biome.BiomeProperties("RC Ocean").setBaseHeight(-2.6F).setHeightVariation(0.2F).setTemperature(0.2F).setRainfall(0.6F), EnumBiomes.OCEAN, PondBiome.Type.OCEAN);
	public static final Biome DEEP_OCEAN = new PondBiome(new Biome.BiomeProperties("RC Deep Ocean").setBaseHeight(-2.9F).setHeightVariation(-0.23F).setTemperature(0.2F).setRainfall(0.6F), EnumBiomes.OCEAN, PondBiome.Type.DEEP_OCEAN);
	public static final Biome SMALL_OCEAN = new PondBiome(new Biome.BiomeProperties("RC Small Ocean").setBaseHeight(-2.3F).setHeightVariation(-0.3F).setTemperature(0.2F).setRainfall(0.6F), EnumBiomes.OCEAN, PondBiome.Type.SMALL_OCEAN);
	public static final Biome SEA = new PondBiome(new Biome.BiomeProperties("RC Sea").setBaseHeight(-2F).setHeightVariation(-0.22F).setTemperature(0.2F).setRainfall(0.6F), EnumBiomes.OCEAN, PondBiome.Type.SEA);
	public static final Biome LAKE = new PondBiome(new Biome.BiomeProperties("RC Lake").setBaseHeight(-2.4f).setHeightVariation(-0.3F).setTemperature(0.2F).setRainfall(0.6F), EnumBiomes.OCEAN, PondBiome.Type.LAKE);

	public static final Biome BEACH = new ĄdditionalsBiomes(new Biome.BiomeProperties("RC Beach").setBaseHeight(0.2F).setHeightVariation(0.1F).setTemperature(1.2F).setRainfall(0.6F), EnumBiomes.OCEAN, ĄdditionalsBiomes.Type.BEACH);
	public static final Biome GRAVEL_BEACH = new ĄdditionalsBiomes(new Biome.BiomeProperties("RC Gravel Beach").setBaseHeight(0.2F).setHeightVariation(0.1F).setTemperature(1.2F).setRainfall(0.6F), EnumBiomes.OCEAN, ĄdditionalsBiomes.Type.GRAVEL_BEACH);

	
	public static final Biome RIVER = new RiverBiome(new Biome.BiomeProperties("RC River").setBaseHeight(-0.8F).setHeightVariation(0.01F).setTemperature(0.1F).setRainfall(0.6F), EnumBiomes.RIVER, RiverBiome.Type.RIVER);

	
	public static void registerBiomes() {
		register(SEA, "rc_sea", BiomeType.WARM, Type.OCEAN);
		register(DEEP_OCEAN, "rc_deep_ocean", BiomeType.WARM, Type.OCEAN);
		register(SMALL_OCEAN, "rc_small_ocean", BiomeType.WARM, Type.OCEAN);
		register(OCEAN, "rc_ocean", BiomeType.WARM, Type.OCEAN);
		register(LAKE, "rc_lake", BiomeType.WARM, Type.OCEAN);
		register(RIVER, "r_river", BiomeType.WARM, Type.RIVER);
		register(BEACH, "rc_beach", BiomeType.WARM, Type.BEACH);
		register(GRAVEL_BEACH, "rc__gravel_beach", BiomeType.WARM, Type.BEACH);
		
	
		register(METAMORPHIC_LAKE, "metamorphic_lake", BiomeType.COOL, METAMORPHIC_TYPE);
		register(METAMORPHIC_SWAMP, "metamorphic_swamp", BiomeType.COOL, METAMORPHIC_TYPE);
		register(METAMORPHIC_HILLS, "metamorphic_hills", BiomeType.COOL, METAMORPHIC_TYPE);
		register(METAMORPHIC_PLAINS, "metamorphic_plains", BiomeType.COOL, METAMORPHIC_TYPE);
		register(METAMORPHIC_FOREST, "metamorphic_forest", BiomeType.COOL, METAMORPHIC_TYPE);
		register(METAMORPHIC_EDGE, "metamorphic_edge", BiomeType.COOL, METAMORPHIC_TYPE);
		
		register(MAGMATIC_LAKE, "magmatic_lake", BiomeType.ICY, MAGMATIC_TYPE);
		register(MAGMATIC_SWAMP, "magmatic_swamp", BiomeType.ICY, MAGMATIC_TYPE);
		register(MAGMATIC_FOREST, "magmatic_forest", BiomeType.ICY, MAGMATIC_TYPE);
		register(MAGMATIC_HILLS, "magmatic_hills", BiomeType.ICY, MAGMATIC_TYPE);
		register(MAGMATIC_PLAINS, "magmatic_plains", BiomeType.ICY, MAGMATIC_TYPE);
		register(MAGMATIC_EDGE, "magmatic_edge", BiomeType.ICY, MAGMATIC_TYPE);
		
		register(SEDIMENTARY_LAKE, "sedimentary_lake", BiomeType.WARM, SEDIMENTARY_TYPE);
		register(SEDIMENTARY_SWAMP, "sedimentary_swamp", BiomeType.WARM, SEDIMENTARY_TYPE);
		register(SEDIMENTARY_KNOLL, "sedimentary_knoll", BiomeType.WARM, SEDIMENTARY_TYPE);
		register(SEDIMENTARY_PLAINS, "sedimentary_plains", BiomeType.WARM, SEDIMENTARY_TYPE);
		register(SEDIMENTARY_FOREST, "sedimentary_forest", BiomeType.WARM, SEDIMENTARY_TYPE);
		register(SEDIMENTARY_EDGE, "sedimentary_edge", BiomeType.WARM, SEDIMENTARY_TYPE);
		
		
	
	}
	
	public static Biome register(Biome b, String name, BiomeType bt, Type... types) {
		b.setRegistryName(name);
		ForgeRegistries.BIOMES.register(b);
		BiomeDictionary.addTypes(b, types);
		BiomeManager.addSpawnBiome(b);

		return b;
	}
    public static boolean isOceanicBiome(Biome b)
    {
        return OCEAN == b || DEEP_OCEAN == b;
    }
    public static boolean isOceanicBiome(int id)
    {
        return Biome.getIdForBiome(OCEAN) == id || Biome.getIdForBiome(DEEP_OCEAN) == id;
    }
    public static boolean isMountainBiome(Biome b)
    {
        return METAMORPHIC_HILLS == b || METAMORPHIC_HILLS == b;
    }
    public static boolean isMountainBiome(int id)
    {
        return Biome.getIdForBiome(METAMORPHIC_HILLS) == id || Biome.getIdForBiome(METAMORPHIC_HILLS) == id;
    }
    public static boolean isBeachBiome(int id)
    {
        return Biome.getIdForBiome(BEACH) == id || Biome.getIdForBiome(GRAVEL_BEACH) == id;
    }
    public static boolean isRiverBiome(Biome b)
    {
        return RIVER == b;
    }

    public static boolean isLakeBiome(Biome b)
    {
        return LAKE == b;
    }
    public static boolean isBeachBiome(Biome b)
    {
        return BEACH == b || GRAVEL_BEACH == b;
    }
}
