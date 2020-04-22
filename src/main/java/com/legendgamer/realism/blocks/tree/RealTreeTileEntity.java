package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.BasicBlock.BasicLogBlockTile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class RealTreeTileEntity extends TileEntity implements ITickable { 

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
	@Override
	public void update() {
		IBlockState state = world.getBlockState(pos);
		int stage = this.stage;
		
		if(world.getTotalWorldTime() % 60 == 0 && stage < 6 && !world.isRemote){
		    stage++;
		    world.setBlockState(pos, state.withProperty(BasicLogBlockTile.STAGE, stage));
		    this.saveStage(stage);
		    System.out.println("stage " +  stage);
		}
		
	}
}
