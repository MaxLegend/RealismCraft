package com.legendgamer.realism.packets;

import com.legendgamer.realism.Realism;
import com.legendgamer.realism.packets.Client.DaySyncMessage;
import com.legendgamer.realism.packets.Client.MonthSyncMessage;
import com.legendgamer.realism.packets.Client.YearSyncMessage;
import com.legendgamer.realism.packets.Server.DaySyncMessageServer;
import com.legendgamer.realism.packets.Server.MonthSyncMessageServer;
import com.legendgamer.realism.packets.Server.YearSyncMessageServer;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static SimpleNetworkWrapper network;

    public static void init() {

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Realism.MODID);


        network.registerMessage(MonthSyncMessageServer.Handler.class, MonthSyncMessageServer.class, 3, Side.SERVER);
        network.registerMessage(MonthSyncMessage.Handler.class, MonthSyncMessage.class, 4, Side.CLIENT);
        
        network.registerMessage(DaySyncMessageServer.Handler.class, DaySyncMessageServer.class, 6, Side.SERVER);
        network.registerMessage(DaySyncMessage.Handler.class, DaySyncMessage.class, 7, Side.CLIENT);
        
        network.registerMessage(YearSyncMessageServer.Handler.class,YearSyncMessageServer.class, 8, Side.SERVER);
        network.registerMessage(YearSyncMessage.Handler.class, YearSyncMessage.class, 9, Side.CLIENT);
     //   network.registerMessage(PlayerWeightMessage.Handler.class, PlayerWeightMessage.class, 4, Side.CLIENT);
  //      network.registerMessage(PlayerWeightMessageServer.Handler.class, PlayerWeightMessageServer.class, 5, Side.SERVER);
    }


	public void sendTo(final IMessage message, final EntityPlayerMP player) {
		network.sendTo(message, player);
	}

	public void sendToServer(final IMessage message){
		network.sendToServer(message);
	}
}