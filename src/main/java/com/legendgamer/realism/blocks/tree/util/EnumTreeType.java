package com.legendgamer.realism.blocks.tree.util;

import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.util.IStringSerializable;

public enum EnumTreeType  {

	ASH(BlocksList.REAL_ASH,BlocksList.REAL_TB_ASH, BlocksList.REAL_ASH_BRANCH,BlocksList.REAL_ASH_LEAVES,0),
	OAK(BlocksList.REAL_OAK,BlocksList.REAL_TB_OAK, BlocksList.REAL_OAK_BRANCH,BlocksList.REAL_OAK_LEAVES,1),
	BIRCH(BlocksList.REAL_BIRCH,BlocksList.REAL_TB_BIRCH, BlocksList.REAL_BIRCH_BRANCH,BlocksList.REAL_BIRCH_LEAVES,2),
	LINDEN(BlocksList.REAL_LINDEN,BlocksList.REAL_TB_LINDEN, BlocksList.REAL_LINDEN_BRANCH,BlocksList.REAL_LINDEN_LEAVES,3),
	PINE(BlocksList.REAL_PINE,BlocksList.REAL_TB_PINE, BlocksList.REAL_PINE_BRANCH,BlocksList.REAL_PINE_LEAVES,4),
	SPRUCE(BlocksList.REAL_SPRUCE,BlocksList.REAL_TB_SPRUCE, BlocksList.REAL_SPRUCE_BRANCH,BlocksList.REAL_SPRUCE_LEAVES,5),
	POPLAR(BlocksList.REAL_POPLAR,BlocksList.REAL_TB_POPLAR, BlocksList.REAL_POPLAR_BRANCH,BlocksList.REAL_POPLAR_LEAVES,6),
	PEAR(BlocksList.REAL_PEAR,BlocksList.REAL_TB_PEAR, BlocksList.REAL_LINDEN_BRANCH,BlocksList.REAL_LINDEN_LEAVES,7),
	LARCH(BlocksList.REAL_LARCH,BlocksList.REAL_TB_LARCH, BlocksList.REAL_LARCH_BRANCH,BlocksList.REAL_LARCH_LEAVES,8);


	Block trunk;
	Block tb;
	Block branch;
	Block leaves;
	int id;
	
	private EnumTreeType(Block trunk, Block thickbranch, Block branch, Block leaves, int id) {
		this.id = id;
		this.trunk = trunk;
		this.tb = thickbranch;
		this.branch = branch;
		this.leaves = leaves;
		
	}
	public Block getTrunkFromType() {
		return this.trunk;
	}
	public Block getThickBranchFromType() {
		return this.tb;
	}
	public Block getBranchFromType() {
		return this.branch;
	}
	public Block getLeavesFromType() {
		return this.leaves;
	}

}
