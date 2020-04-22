package com.legendgamer.realism.capevent;

import com.legendgamer.realism.Realism;
import com.legendgamer.realism.capability.world_cap.DateProvider;

import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldCAPEventHandler {
	int count;   
	byte day;
	byte month;
	int year;
	@SubscribeEvent
	public void onWorldDate(AttachCapabilitiesEvent<World> e)
	{
		if (e.getObject() != null)
			e.addCapability(Realism.DATE, new DateProvider()); 
	}
}