package com.legendgamer.realism.blocks.tree.sapling;

import com.legendgamer.realism.API.BlockSide;
import com.legendgamer.realism.API.BasicBlock.BasicBlockBush;
import com.legendgamer.realism.API.BasicBlock.BasicBlockSide;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.world.gen.trees.layergen.PineTreeGenerator;
import com.legendgamer.realism.world.gen.trees.layergen.SpruceTreeGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPineSapling extends BasicBlockBush {
	Block logBlock;
	Block leavesBlock;
	Block branchBlock;
	public BlockPineSapling(Material materialIn, String name, CreativeTabs tab, Block log, Block branch, Block leaves) {
		super(materialIn, name, 0, 0, SoundType.PLANT, tab);
		this.logBlock = log;
		this.branchBlock = branch;
		this.leavesBlock = leaves;
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote) {
			new PineTreeGenerator(logBlock, branchBlock, leavesBlock).generate(world, world.rand, pos);
			return true;
		}
		return false;
	}
	
}

