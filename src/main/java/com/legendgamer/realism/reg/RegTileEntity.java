package com.legendgamer.realism.reg;

import com.legendgamer.realism.blocks.tree.RealTreeTileEntity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegTileEntity {
	public static void registry() {
        GameRegistry.registerTileEntity(RealTreeTileEntity.class, "real_tree_tile");

	}
}
