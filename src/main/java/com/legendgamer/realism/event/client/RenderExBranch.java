package com.legendgamer.realism.event.client;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderExBranch {
	
	World world = Minecraft.getMinecraft().world;
	Block block;
	BlockPos pos;
	@SubscribeEvent
	public void draw(RenderBlockOverlayEvent e) {
		GlStateManager.enableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		GlStateManager.doPolygonOffset(-3.0F, -3.0F);
		GlStateManager.enablePolygonOffset();
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableAlpha();
		GlStateManager.pushMatrix();
		
		Tessellator tessellatorIn = Tessellator.getInstance();
		BufferBuilder bufferBuilderIn = tessellatorIn.getBuffer();
		IBlockState iblockstate = world.getBlockState(e.getBlockPos());
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		blockrendererdispatcher.renderBlock(iblockstate, e.getBlockPos().up(), world, bufferBuilderIn);
	
		GlStateManager.disableAlpha();
		GlStateManager.doPolygonOffset(0.0F, 0.0F);
		GlStateManager.disablePolygonOffset();
		GlStateManager.enableAlpha();
		GlStateManager.depthMask(true);
		GlStateManager.popMatrix();
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
