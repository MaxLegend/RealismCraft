package com.legendgamer.realism.seasons;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.packets.NetworkHandler;
import com.legendgamer.realism.packets.Server.MonthSyncMessageServer;
import com.legendgamer.realism.reg.RegBiomes;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GradientTicker {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void grdticl(BiomeEvent.GetGrassColor e) {

		World world = Minecraft.getMinecraft().world;
		IDate date = world.getCapability(DateProvider.DATE, null);
		EntityPlayer player = (EntityPlayer)Minecraft.getMinecraft().player;
	//	System.out.println("month " + date.getMonth());
		if(date.getMonth() == 0 && e.getBiome() == RegBiomes.METAMORPHIC_HILLS) {

		//	System.out.println("month " + date.getMonth());

			int color = e.getNewColor();
			int R = (color >> 16) & 0xff;         
			int G = (color >> 8) & 0xff;       
			int B = (color) & 0xff;
	//		System.out.println("pirot" + world.getWorldTime());
			int color2 = 0xC2D6C3;
			int R2 = (color2 >> 16) & 0xff;         
			int G2 = (color2 >> 8) & 0xff;       
			int B2 = (color2) & 0xff;
			int step = 5;

			for(int i = 0; i < 10; i++) {
				R = R + (R2-R)*step/i;
				G = G + (G2-G)*step/i;
				B = B + (B2-B)*step/i;
				int resultColor = ((R << 16) & 0xff) + ((G << 8) & 0xff) + ((B) & 0xff);
				e.setNewColor(resultColor);
			}
			NetworkHandler.network.sendToServer(new MonthSyncMessageServer(date.getMonth()));
		}
		
	}
}
