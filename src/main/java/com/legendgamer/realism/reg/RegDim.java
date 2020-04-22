package com.legendgamer.realism.reg;

import com.legendgamer.realism.world.gen.chunk.RealismWorldProvider;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class RegDim {

	public static final DimensionType REALISM_DIM = DimensionType.register("realismWorld", "_realismWorld", 14, RealismWorldProvider.class, false);
	
	public static void registerDim() {
		DimensionManager.registerDimension(14, REALISM_DIM);
	}
}
