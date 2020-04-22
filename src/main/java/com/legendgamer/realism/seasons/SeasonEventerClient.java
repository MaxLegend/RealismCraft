package com.legendgamer.realism.seasons;

import java.lang.reflect.Field;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class SeasonEventerClient {


	ChunkRenderDispatcher cr = new ChunkRenderDispatcher();
	Long2ObjectMap<Chunk> map;
	Field chunkMapping;

	@SubscribeEvent
	public void onWorldTick(RenderWorldLastEvent e) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		
//		Minecraft mc = Minecraft.getMinecraft();
//		  chunkMapping = ChunkProviderClient.class.getDeclaredField("chunkMapping");
//		  chunkMapping.setAccessible(true);
//		  map = (Long2ObjectMap<Chunk>) chunkMapping.get(Minecraft.getMinecraft().world.getChunkProvider());
//		  ObjectIterator iterator = map.values().iterator();
//		  while (iterator.hasNext()) {
//		    Chunk chunk = (Chunk)iterator.next();
//		    int x = chunk.x*16; 
//		    int z = chunk.z*16; 
//		    BlockPos pos = new BlockPos(x,0,z);
//		IDate date = mc.world.getCapability(DateProvider.DATE, null);
//		if(date.getMonth() == 11 && date.getDay() == 1) {
//	
//			Minecraft.getMinecraft().renderGlobal.markBlockRangeForRenderUpdate(x, 0, z, x + 15, 255, z + 15);
			  }

		
		
		
//
//		}
//		if(date.getMonth() == 2) {
//
//		}
//		if(date.getMonth() == 5) {
//
//		}
//		if(date.getMonth() == 8) {
//
//		}

//	  
//	 //   Minecraft.getMinecraft().renderGlobal.markBlockRangeForRenderUpdate(x, 0, z, x, 0, z);
//	    mc.renderGlobal.notifyBlockUpdate(mc.world, pos, mc.world.getBlockState(pos), mc.world.getBlockState(pos), 2);
	}

