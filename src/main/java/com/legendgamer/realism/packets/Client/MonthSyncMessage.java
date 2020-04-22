package com.legendgamer.realism.packets.Client;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
public class MonthSyncMessage implements IMessage {  
	public byte day;  
	public MonthSyncMessage(){}

	public MonthSyncMessage(byte value){  
		this.day = value;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		day = buf.readByte();   
	}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeByte(day); 
	}   

	public static class Handler implements IMessageHandler<MonthSyncMessage, IMessage> {

		@Override
		public IMessage onMessage(MonthSyncMessage message, MessageContext ctx) { 

			EntityPlayerSP player = Minecraft.getMinecraft().player;   
			if(player != null) {
				IDate cap = player.getEntityWorld().getCapability(DateProvider.DATE, null);   
				if(cap != null)    {
					cap.setMonth(message.day);  
				}
			}
				return null;    
			} 
		}
	}