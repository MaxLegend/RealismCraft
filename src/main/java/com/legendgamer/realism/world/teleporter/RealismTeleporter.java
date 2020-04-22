package com.legendgamer.realism.world.teleporter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

public class RealismTeleporter implements ITeleporter {
	WorldServer world;
	double x, y, z;
	public RealismTeleporter(WorldServer ws, double x, double y, double z) {
		super();
		world = ws;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		entity.posX = this.x;
		entity.posY = this.y;
		entity.posZ = this.z;
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (player.capabilities.allowFlying) player.capabilities.isFlying = true;
		}
		
	}
}
