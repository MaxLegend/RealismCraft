package com.legendgamer.realism.blocks.tree.fruitleaves;

import java.util.Random;

import com.legendgamer.realism.blocks.tree.frame.BlockFruitLeaves;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LindenFL extends BlockFruitLeaves {

	public LindenFL(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	}
	
	@Override
	public void fruitUpdate(World w, BlockPos p, IBlockState s, Random r, IDate date) {
		
		if(date.getMonth() < 5 || date.getMonth() == 11) {
			w.setBlockState(p, BlocksList.REAL_LINDEN_LEAVES.getDefaultState());
		}
		//��������� ��������� - 5 - ����, ��������
		//� ���� �� �������� - �������� �����
		if(date.getMonth() > 5 && date.getMonth() < 9) {
			w.setBlockState(p, s.withProperty(STAGE, 1));
		} 
		//c �������� �� ������ ��������� ������� ������
		if(date.getMonth() >= 9 && date.getMonth() <= 10) {
			w.setBlockState(p, s.withProperty(STAGE, 2));
		}
		
	}
	
	//TODO ����� ����� ��������
//    public Item getItemDropped(IBlockState state, Random rand, int fortune)
//    {
//    	
//    }
}