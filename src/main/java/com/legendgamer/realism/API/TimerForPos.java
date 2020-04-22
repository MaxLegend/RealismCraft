package com.legendgamer.realism.API;

import net.minecraft.util.math.BlockPos;

public class TimerForPos {
	public BlockPos pos;
	public int time;
	public TimerForPos(BlockPos pos, int time){
		this.pos = pos;
		this.time = time;
	}
}

