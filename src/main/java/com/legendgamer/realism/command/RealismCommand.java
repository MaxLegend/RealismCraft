package com.legendgamer.realism.command;

import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.packets.NetworkHandler;
import com.legendgamer.realism.packets.Client.DaySyncMessage;
import com.legendgamer.realism.packets.Client.MonthSyncMessage;
import com.legendgamer.realism.packets.Client.YearSyncMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;


public class RealismCommand extends CommandBase {

	public RealismCommand(){}

	@Override
	public String getName() {
		return "real";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "'/real <command> <nickname> <amount> [args]'. Check '/real info' to get list of all available commands.";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getServer().getEntityWorld();


		EntityPlayer player = getCommandSenderAsPlayer(sender);
		int lenght = args.length;
		switch(lenght) {
		case 0:
			break;
		case 1: 

			if(player != null){
				player.sendMessage(new TextComponentString("/real set month <amount[0 - 11]> - set month"));
				player.sendMessage(new TextComponentString("/real set year <amount[any]> - set year"));
				player.sendMessage(new TextComponentString("/real set day <amount[1 - 30]> - set day"));
				player.sendMessage(new TextComponentString("/real set <nickname> temp <amount[34 - 42]> - set temp body") );
				player.sendMessage(new TextComponentString("/real set <nickname> weight <amount> - set weight"));
				player.sendMessage(new TextComponentString("/real set totaltime <amount> - reset calendar. <amount> - may be any"));
			} 
			break;
		case 3:
			Minecraft mc = Minecraft.getMinecraft();
			int chunkX = mc.player.getPosition().getX()* 16;
			int chunkY = mc.player.getPosition().getY();
			int chunkZ = mc.player.getPosition().getZ()* 16;
			BlockPos pos = new BlockPos(chunkX,chunkY,chunkZ);
			IDate cap = world.getCapability(DateProvider.DATE, null);
			String set = args[0];
			String amount = args[1];
			switch (set) {
			case "set": {
				String value = args[2];    
				if (amount.equals("day")) { 
					if(Byte.parseByte(value) == 0) {
						cap.setDay((byte)1); 
					}
					cap.setDay(Byte.parseByte(value)); 
					player.sendMessage(new TextComponentString("Set the day: " + Byte.parseByte(value)));
					NetworkHandler.network.sendTo(new DaySyncMessage(Byte.parseByte(value)),(EntityPlayerMP)player);
				} 
				if (amount.equals("month")) { 
					if(Byte.parseByte(value) == 0) {
						cap.setMonth((byte)0); 
					
						
					}
					cap.setMonth(Byte.parseByte(value)); 
					player.sendMessage(new TextComponentString("Set the month: " + Byte.parseByte(value)));
					NetworkHandler.network.sendTo(new MonthSyncMessage(cap.getMonth()),(EntityPlayerMP)player);
				} 
				if (amount.equals("year")) { 
					cap.setYear(Integer.parseInt(value)); 
					player.sendMessage(new TextComponentString("Set the year: " + Integer.parseInt(value)));
					NetworkHandler.network.sendTo(new YearSyncMessage(cap.getYear()),(EntityPlayerMP)player);
				} 
				if (amount.equals("datanull")) { 
					cap.setDay((byte)0); 
					cap.setMonth((byte)0); 
					cap.setYear(0); 
					world.setTotalWorldTime(0);
					world.setWorldTime(0);
					player.sendMessage(new TextComponentString("Set the World Time: " + world.getWorldTime()));
					player.sendMessage(new TextComponentString("Set the Total World Time: " + world.getTotalWorldTime()));
					player.sendMessage(new TextComponentString("Set the day: " + Byte.parseByte(value)));
					player.sendMessage(new TextComponentString("Set the month: " + Byte.parseByte(value)));
					player.sendMessage(new TextComponentString("Set the year: " + Integer.parseInt(value)));
					NetworkHandler.network.sendTo(new YearSyncMessage(cap.getYear()),(EntityPlayerMP)player);
				} 
				break;
			}

			}
			break;
		case 4:
	/*
			String nickname = args[1];
			String command = args[0];
			String type = args[2];
			EntityPlayer entityplayer = (EntityPlayer)getEntity(server, sender, nickname, EntityPlayer.class);

			BlockPos pos = entityplayer.getPosition();
			Biome biome = world.getBiome(pos);
			IPlayerCap capplayer = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);

			switch (command) {
			case "set": {
				String value = args[3];    
				if (type.equals("temp")) { 
					capplayer.setTempBody(Float.parseFloat(value));
					player.sendMessage(new TextComponentString("Set TempBody: " + Float.parseFloat(value)));
				}
				if (type.equals("weight")) { 
					capplayer.setWeight(Float.parseFloat(value));
					player.sendMessage(new TextComponentString("Set Weight: " + Float.parseFloat(value)));
				}
				break;
			}
			}
			break;*/
		}
	}


	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
}