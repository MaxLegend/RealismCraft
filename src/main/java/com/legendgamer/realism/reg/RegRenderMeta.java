package com.legendgamer.realism.reg;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegRenderMeta {
	@SubscribeEvent
	public void registerMeta(ModelRegistryEvent e) {
		RegItems.registerMetaRender();
	}
}