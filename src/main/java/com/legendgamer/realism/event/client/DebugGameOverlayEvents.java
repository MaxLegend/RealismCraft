package com.legendgamer.realism.event.client;

import org.lwjgl.opengl.GL11;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DebugGameOverlayEvents {
	static Minecraft mc = Minecraft.getMinecraft();
	

//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
	public void thermoRender(RenderGameOverlayEvent.Text event) {
//		World world = mc.world;
//	
//
//
//				GL11.glPushMatrix();
//				GL11.glEnable(GL11.GL_BLEND);
//
//				
//				World world2 = mc.world;
//				IDate date = world.getCapability(DateProvider.DATE, null);
//
//				FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
//
//				
//					fr.drawString("Day: "  + date.getDay(), 20, 20, 0x000000);
//					fr.drawString("Month: "  + this.getMonthName(), 20, 30, 0x000000);
//					fr.drawString("Year: "  + (date.getYear()) ,20, 40, 0x000000);
//					fr.drawString("Hour: " + world.getWorldTime()/1000, 20, 50, 0x000000);
//					fr.drawString("Tick: " + world.getWorldTime(), 20, 60, 0x000000);
//				GL11.glDisable(GL11.GL_BLEND);
//				GL11.glPopMatrix();
			
	}
	
	public String getMonthName() {
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.world;
		IDate date = world.getCapability(DateProvider.DATE, null);
		String[] monthName = new String[] {"January","February","March","April","May","June","Jule","August","September","October","November","December"};
		
		return monthName[date.getMonth()];
	}

}
