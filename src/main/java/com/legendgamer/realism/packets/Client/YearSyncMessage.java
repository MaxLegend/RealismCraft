package com.legendgamer.realism.packets.Client;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class YearSyncMessage implements IMessage {  
	public int year;  
	public YearSyncMessage(){}

	public YearSyncMessage(int s){  
		this.year = s;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		year = ByteBufUtils.readVarInt(buf, 5);   
	}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		ByteBufUtils.writeVarInt(buf, year, 5); 
	}   

	public static class Handler implements IMessageHandler<YearSyncMessage, IMessage> {

		@Override
		public IMessage onMessage(YearSyncMessage message, MessageContext ctx) { 

			EntityPlayerSP player = Minecraft.getMinecraft().player;   
			if(player != null) {
				IDate cap = player.getEntityWorld().getCapability(DateProvider.DATE, null);   
				if(cap != null)    {
					cap.setYear(message.year);  
				}
			}
				return null;    
			} 
		}
	}