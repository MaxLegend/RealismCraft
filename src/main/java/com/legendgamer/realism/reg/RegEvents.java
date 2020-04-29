package com.legendgamer.realism.reg;

import com.legendgamer.realism.capevent.WorldCAPEventHandler;
import com.legendgamer.realism.event.client.DebugGameOverlayEvents;
import com.legendgamer.realism.event.client.RenderExBranch;
import com.legendgamer.realism.seasons.GradientTicker;
import com.legendgamer.realism.seasons.SeasonEventerClient;
import com.legendgamer.realism.seasons.SeasonEventerServer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class RegEvents {
	public static void Client() {
		register(new GradientTicker());
		register(new DebugGameOverlayEvents());
		register(new SeasonEventerClient());
	//	register(new RenderExBranch()); //needed fixes
	}
	public static void Server() {
		register(new SeasonEventerServer());
		register(new WorldCAPEventHandler());
	}

	public static void register(Object event) {
		MinecraftForge.EVENT_BUS.register(event);
	}
	public static void registerFML(Object event) {
		FMLCommonHandler.instance().bus().register(event);
	}



}
