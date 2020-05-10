package com.legendgamer.realism.packets.Client;

import com.legendgamer.realism.blocks.tree.frame.BlockRealLeaves;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CheckPlayerDestrMessageToServer implements IMessage {  
	//Пример пакета  с отправкой булеана и координатами
	public boolean value;  
	public int x,y,z;
	public CheckPlayerDestrMessageToServer(){}
	
	public CheckPlayerDestrMessageToServer(boolean value, int x, int y, int z){  
		this.value = value;   
		this.x = x;
		this.y = y;
		this.z = z;
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		value = buf.readBoolean();   
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeBoolean(value); 
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}   

	public static class Handler implements IMessageHandler<CheckPlayerDestrMessageToServer, IMessage> {

		@Override
		public IMessage onMessage(CheckPlayerDestrMessageToServer message, MessageContext ctx) { 	
			BlockPos pos = new BlockPos(message.x,message.y,message.z);
			Block block = ctx.getServerHandler().player.getEntityWorld().getBlockState(pos).getBlock();
			if(block instanceof BlockRealLeaves) {
			//		((BlockRealLeaves)block).isPlayerBreak = message.value;	
			} 
			
			return null;    
		}

	}
}