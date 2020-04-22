package com.legendgamer.realism.event;

import com.legendgamer.realism.world.teleporter.RealismTeleporter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventTeleport {
	@SubscribeEvent
	public void travel(PlayerInteractEvent.RightClickBlock e) {
		if(e.getEntityPlayer().getEntityWorld().getBlockState(e.getPos()) == Blocks.BEDROCK.getDefaultState()) {
			travelToDimensionWithoutPortal(e.getEntityPlayer(), 14, e.getEntityPlayer().getPosition().getX(), e.getEntityPlayer().getPosition().getY(), e.getEntityPlayer().getPosition().getZ());
		}
		
	}

	public static void travelToDimensionWithoutPortal(Entity entity, int dimTo, double x, double y, double z) {
		if (dimTo == entity.dimension) entity.setPosition(x, y, z);
		if (entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) entity;
			WorldServer worldTo = player.mcServer.getWorld(dimTo);
			player.changeDimension(dimTo, new RealismTeleporter(worldTo, x, y, z));
		}
	}
}
