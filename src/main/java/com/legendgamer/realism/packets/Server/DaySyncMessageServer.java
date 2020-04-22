package com.legendgamer.realism.packets.Server;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DaySyncMessageServer implements IMessage {  
	public byte value;  
	public DaySyncMessageServer(){}
	
	public DaySyncMessageServer(byte value){  
		this.value = value;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		value = buf.readByte();   
		}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeByte(value); 
	}   

	public static class Handler implements IMessageHandler<DaySyncMessageServer, IMessage> {

		@Override
		public IMessage onMessage(DaySyncMessageServer message, MessageContext ctx) { 	
			IDate date = ctx.getServerHandler().player.getEntityWorld().getCapability(DateProvider.DATE, null);   
			if(date != null)    
			
				date.setDay(message.value);    
			return null;    
		}

	}
}