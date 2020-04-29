package com.legendgamer.realism.event.client;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderExBranch {
	

	@SubscribeEvent
	public void draw(RenderWorldLastEvent e) {
		Minecraft mc  = Minecraft.getMinecraft();
		World world = Minecraft.getMinecraft().world;
		Block block;
		BlockPos pos = this.getCoordBlockInChunk(world);
		GlStateManager.enableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		GlStateManager.doPolygonOffset(-3.0F, -3.0F);
		GlStateManager.enablePolygonOffset();
		GlStateManager.alphaFunc(516, 0.3F);
		GlStateManager.enableAlpha();
		GlStateManager.pushMatrix();
	//	System.out.println("KFFF");
		Tessellator tessellatorIn = Tessellator.getInstance();
		BufferBuilder bufferBuilderIn = tessellatorIn.getBuffer();
		
		BlockPos pos2 = mc.player.getPosition();
		int worldX = pos2.getX();
		int worldY = pos2.getY();
		int worldZ = pos2.getZ();
		
		BlockPos pos3 = new BlockPos(pos.getX()-worldX,pos.getY()-worldY, pos.getZ()-worldZ);
		
	
		IBlockState iblockstate = world.getBlockState(pos2.down());
		 EnumBlockRenderType enumblockrendertype = iblockstate.getRenderType();
		BlockRendererDispatcher br = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
	
		
		System.out.println("KFFF" + br.renderBlock(iblockstate, pos2, world, bufferBuilderIn) + pos2); 
		br.renderBlock(iblockstate, pos2, world, bufferBuilderIn);
		//	br.getBlockModelRenderer().renderModel(world, br.getModelForState(iblockstate), iblockstate, pos, bufferBuilderIn, true);
			br.renderBlockBrightness(iblockstate, 1F);
		
		GlStateManager.disableAlpha();
		GlStateManager.doPolygonOffset(0.0F, 0.0F);
		GlStateManager.disablePolygonOffset();
		GlStateManager.enableAlpha();
		GlStateManager.depthMask(true);
		GlStateManager.popMatrix();
	}
	
	public BlockPos getCoordBlockInChunk(World world) {
		BlockPos defpos = new BlockPos(0, 0, 0);
		ChunkProviderClient cp = (ChunkProviderClient) world.getChunkProvider();
		List<Chunk> chunks = Lists.newArrayList(cp.chunkMapping.values());
		for(Chunk chunk : chunks) {
			int x = chunk.x * 16;
			int z = chunk.z * 16;
			BlockPos pos = new BlockPos(x, 0, z);
			return pos;
		} 

		return defpos;
	

	}
	public static class WSDUtil {
		@SuppressWarnings("all")
		public static <T extends WorldSavedData> T get(World world, String name, Class<T> clazzSavedData) {
			T instance = (T) world.getMapStorage().getOrLoadData(clazzSavedData, name);

			if (instance == null) {
				try {
					instance = clazzSavedData.getConstructor(String.class).newInstance(name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				instance.markDirty();
				world.getMapStorage().setData(name, instance);
			}
			return instance;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void drawBlockTexture(Entity entityIn, float partialTicks, int x,int y,int z, World world, String texture) {
		double d3 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks;
		double d4 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks;
		double d5 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks;
		Tessellator tessellatorIn = Tessellator.getInstance();
		BufferBuilder bufferBuilderIn = tessellatorIn.getBuffer();
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		bufferBuilderIn.begin(7, DefaultVertexFormats.BLOCK);
		bufferBuilderIn.setTranslation(-d3, -d4, -d5);
		bufferBuilderIn.noColor();
		IBlockState iblockstate = world.getBlockState(new BlockPos(x,y,z));
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		blockrendererdispatcher.renderBlock(iblockstate, new BlockPos(x,y,z), world, bufferBuilderIn);
		tessellatorIn.draw();
		bufferBuilderIn.setTranslation(0.0D, 0.0D, 0.0D);

	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent 
	public void spriteRegisterEventPre(TextureStitchEvent.Pre event) {  
		ResourceLocation glass = new ResourceLocation("realism:textatlas/mold");  
		event.getMap().registerSprite(glass); 
	}
}
