package com.legendgamer.realism.world.biome;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.legendgamer.realism.world.biome.base.BiomeBase;
import com.legendgamer.realism.world.gen.layer.GenLayerBiomes;
import com.legendgamer.realism.world.gen.layer.GenLayerRegions;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;

public class RealismBiomeProvider extends BiomeProvider {

	public RealismBiomeProvider(WorldInfo info) {
		super(info);
		// GenLayer[] agenlayer = GenLayerBiomes.initializeAllBiomeGenerators(info.getSeed(), info.getTerrainType());
	}
	
	public static void drawImage(int size, GenLayer biomes, String name) {
        try {
            File outFile = new File(name + ".bmp");
            if (outFile.exists())
                return;
            int[] ints = biomes.getInts(0, 0, size, size);
            BufferedImage outBitmap = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
            graphics.clearRect(0, 0, size, size);
           
            for (int x = 0; x < size; x++) {
                for (int z = 0; z < size; z++) {
                	
                    if (ints[x * size + z] != -1 && Biome.getBiome(ints[x * size + z]) instanceof BiomeBase) {
                 //    graphics.setColor(Color.getColor("", ((BiomeBase)Biome.getBiome(ints[x * size + z]))));

                        graphics.drawRect(x, z, 1, 1);
                    }
                }
            }
   
            ImageIO.write(outBitmap, "BMP", outFile);
        }
        catch (Exception e) {
         
        }
    }
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
	    GenLayer genlayer = new GenLayerIsland(1L);

	    genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
	    genlayer = new GenLayerAddIsland(1L, genlayer);
	    genlayer = new GenLayerZoom(2001L, genlayer);
	    genlayer = new GenLayerAddIsland(2L, genlayer);
	   genlayer = new GenLayerAddIsland(50L, genlayer);
	    genlayer = new GenLayerAddIsland(70L, genlayer);
    genlayer = new GenLayerRemoveTooMuchOcean(2L, genlayer);
	    genlayer = new GenLayerAddIsland(3L, genlayer);
	    genlayer = new GenLayerRegions(seed, genlayer); 
	    genlayer = new GenLayerZoom(2002L, genlayer);
	    genlayer = new GenLayerZoom(2003L, genlayer);
	    genlayer = new GenLayerBiomes(seed, genlayer); 
	    GenLayer pre_river = new GenLayerAddIsland(4L, genlayer);
	    genlayer = GenLayerZoom.magnify(1000L, pre_river, 2);
	    int biomeSize = 4;

	    for (int k = 0; k < biomeSize; k++) {
	        genlayer = new GenLayerZoom((long) (1000 + k), genlayer);

	        if (k == 0) {
	            genlayer = new GenLayerAddIsland(3L, genlayer);
	        }
	    }

	    genlayer = new GenLayerZoom(2022L, genlayer);
	    genlayer = new GenLayerZoom(2023L, genlayer);


	    GenLayer genlayer_world = new GenLayerSmooth(1000L, genlayer);
	    genlayer = new GenLayerVoronoiZoom(10L, genlayer_world);
	  

	    genlayer.initWorldGenSeed(seed);
	    System.out.println("seed " + seed);
	    return new GenLayer[] { genlayer_world, genlayer, genlayer_world };
	}
	
//		GenLayer start = new RealGenLayerIsland(1L);
//		 start = new GenLayerFuzzyZoom(2000L, start);
//		 start = new GenLayerZoom(2000L, start);
//		 GenLayer layer1 = new GenLayerAddIsland(1L, start);
//		 layer1 = new GenLayerAddIsland(50L, layer1);
//		 layer1 = new GenLayerAddIsland(70L, layer1);
//		 GenLayer layerzoom1 = new GenLayerZoom(1001L, layer1);
//		 layerzoom1 = new GenLayerZoom(1001L, layerzoom1);
//		 layerzoom1 = new GenLayerZoom(1002L, layerzoom1);
//		 layerzoom1 = new GenLayerZoom(1003L, layerzoom1);
//		 layerzoom1 = new GenLayerZoom(1004L, layerzoom1);
//		 layerzoom1 = new GenLayerSmooth(1001L, layerzoom1);
//	//	 layerzoom1 = new GenLayerSmooth(1002L, layerzoom1);
//	//	 layerzoom1 = new GenLayerZoom(1005L, layerzoom1);
//		 GenLayer biomes = new GenLayerSubBiomes(1001L);
//		 biomes = new GenLayerZoom(1002L, biomes);
//		 biomes = new GenLayerZoom(1003L, biomes);
//		 biomes = new GenLayerZoom(1004L, biomes);
//		 biomes = new GenLayerZoom(1005L, biomes);
//		 biomes = new GenLayerZoom(1006L, biomes);
//		 biomes = new GenLayerSmooth(1004L, biomes);
//		 biomes = new GenLayerSmooth(1004L, biomes);
//		 biomes = new GenLayerSmooth(1004L, biomes);
//		 GenLayer biomeIndexLayer = new GenLayerVoronoiZoom(10L, biomes);
//		
//
//		 start.initWorldGenSeed(seed);
////	drawImage(1000, biomeIndexLayer, "biomeIndexLayer");
////	drawImage(1000, biomes, "biomes");
//
//	        return new GenLayer[]{ 
//	        		layerzoom1,
//	        		biomes,
//	                biomeIndexLayer
//	        };
  
    
}
