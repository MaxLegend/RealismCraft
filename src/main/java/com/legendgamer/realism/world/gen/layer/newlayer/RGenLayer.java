package com.legendgamer.realism.world.gen.layer.newlayer;

import com.legendgamer.realism.world.gen.layer.newlayer.river.RGenLayerRiver;
import com.legendgamer.realism.world.gen.layer.newlayer.river.RGenLayerRiverInit;
import com.legendgamer.realism.world.gen.layer.newlayer.river.RGenLayerRiverMix;

import net.minecraft.world.gen.layer.GenLayer;

public abstract class RGenLayer extends GenLayer {

	public RGenLayer(long p_i2125_1_) {
		super(p_i2125_1_);

	}

	public static RGenLayer[] initialize2(long seed)
	{
		
		 RGenLayer continent = genContinent(0);
	     continent = new RGenLayerDeepOcean(4L, continent);
	     byte var4 = 4;
	     RGenLayer continentCopy2 = RGenLayerZoom.magnify(1000L, continent, 0);
	     RGenLayer regions = new RGenLayerRegions(200L, continentCopy2);
	     
	     regions = new RGenLayerZoom(2002L, regions);
	     regions = new RGenLayerZoom(2003L, regions);
	     regions = new RGenLayerBiomes(seed, regions); 
	     RGenLayerLakes lakes = new RGenLayerLakes(200L, regions);
	     continentCopy2 = RGenLayerZoom.magnify(1000L, lakes, 2);
	     RGenLayer createEdge = new RGenLayerBiomeEdge(1000L, continentCopy2);
	     for (int var7 = 0; var7 < var4; ++var7)
	        {
	    	 createEdge = new RGenLayerZoom(1000 + var7, createEdge);

	            if (var7 == 0)
	            	createEdge = new RGenLayerAddIsland(3L, createEdge);
	            if (var7 == 1)
	            {
	            	createEdge = new RGenLayerShore(1000L, createEdge);

	            }
	        }
	   //Create Rivers
	        RGenLayer riverCont = RGenLayerZoom.magnify(1000L, continent, 4);
	        riverCont = new RGenLayerRiverInit(100L, riverCont);
	        riverCont = new RGenLayerZoom(1000L, riverCont);
	        riverCont = RGenLayerZoom.magnify(1000L, riverCont, 6);
	        riverCont = new RGenLayerRiver(1L, riverCont);
	        riverCont = new RGenLayerSmooth(1000L, riverCont);
	        RGenLayerSmooth smoothContinent = new RGenLayerSmooth(1000L, createEdge);
	        RGenLayerRiverMix riverMix = new RGenLayerRiverMix(1000L, smoothContinent, riverCont);
	        RGenLayer finalCont = RGenLayerZoom.magnify(1000L, riverMix, 2);
	    //    finalCont = new RGenLayerZoom(1000L, finalCont);
	        finalCont = new RGenLayerSmooth(1001L, finalCont);
	        riverMix.initWorldGenSeed(seed);
	        finalCont.initWorldGenSeed(seed);
	        return new RGenLayer[] {riverMix, finalCont};

	}

	public static RGenLayer genContinent(long seed)
	{
		RGenLayer continentStart = new RGenLayerIsland(1L + seed);
		RGenLayerFuzzyZoom continentFuzzyZoom = new RGenLayerFuzzyZoom(2000L, continentStart);
		RGenLayer var10 = new RGenLayerAddIsland(1L, continentFuzzyZoom);
		RGenLayer var11 = new RGenLayerZoom(2001L, var10);
		var10 = new RGenLayerAddIsland(2L, var11);
		var11 = new RGenLayerZoom(2002L, var10);
		var10 = new RGenLayerAddIsland(3L, var11);
		var11 = new RGenLayerZoom(2003L, var10);
		RGenLayer continent = new RGenLayerAddIsland(4L, var11);
		return continent;
	}
}
