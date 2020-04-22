package com.legendgamer.realism.seasons;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.packets.NetworkHandler;
import com.legendgamer.realism.packets.Client.DaySyncMessage;
import com.legendgamer.realism.packets.Client.MonthSyncMessage;
import com.legendgamer.realism.packets.Client.YearSyncMessage;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SeasonEventerServer {
	int count;   
	byte day;
	byte month;
	int year;

	@SubscribeEvent
	public void syncDate(EntityJoinWorldEvent e) {
		IDate date = e.getWorld().getCapability(DateProvider.DATE, null);
		NetworkHandler.network.sendToAll(new DaySyncMessage(date.getDay()));
		NetworkHandler.network.sendToAll(new MonthSyncMessage(date.getMonth()));
		NetworkHandler.network.sendToAll(new YearSyncMessage(date.getYear()));
		
	}
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent e)
	{

		if (e.phase != e.phase.END) { return; }	  
		if(!e.world.isRemote) {
			if(e.world.provider.getDimension() == 0) {
				IDate date = e.world.getCapability(DateProvider.DATE, null);
				if(e.world.getWorldTime() > 24000) {
					e.world.setWorldTime(0);
				}
				if(e.world.getWorldTime() % 23999 == 0) 
				{
					
					day = (byte)(date.getDay() + 1);
					date.setDay(day);
					e.world.setWorldTime(0);
					
					NetworkHandler.network.sendToAll(new DaySyncMessage(date.getDay()));
				}
				if(date.getDay() >= 30) {
					month = (byte)(date.getMonth() + 1);
					date.setMonth(month);
					date.setDay((byte)1);
					NetworkHandler.network.sendToAll(new DaySyncMessage(date.getDay()));
					NetworkHandler.network.sendToAll(new MonthSyncMessage(date.getMonth()));
				}
				if(date.getMonth() > 11) {
					year = (date.getYear() + 1);
					date.setYear(year);
					date.setDay((byte)1);
					date.setMonth((byte)0);
					
					NetworkHandler.network.sendToAll(new DaySyncMessage(date.getDay()));
					NetworkHandler.network.sendToAll(new MonthSyncMessage(date.getMonth()));
					NetworkHandler.network.sendToAll(new YearSyncMessage(date.getYear()));
				}
			}
		}
		
	

	}

}
