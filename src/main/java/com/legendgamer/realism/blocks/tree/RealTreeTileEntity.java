package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.BasicBlock.BasicLogBlockTile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class RealTreeTileEntity extends TileEntity { 


	public int stage;

	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("stage", this.stage);
		return super.writeToNBT(nbt);
	}
	public void readFromNBT(NBTTagCompound nbt)
	{
		this.stage = nbt.getInteger("stage");
		super.readFromNBT(nbt);
	}
	public int getStage() {
		return stage;
	}
	public void saveStage(int stage) {
		this.stage = stage;
		this.markDirty();
	}
	public IBlockState getStateFromStage() {
		if(world.getBlockState(pos) instanceof BasicLogBlockTile) {
		return world.getBlockState(pos).withProperty(BasicLogBlockTile.STAGE, stage);
		} else 
			return world.getBlockState(pos);
	}
 }
