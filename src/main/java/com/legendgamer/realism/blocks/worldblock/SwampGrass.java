package com.legendgamer.realism.blocks.worldblock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SwampGrass extends GrassSided {

	//TODO добавить кастомный баундбокс поменьше высоты(0.9-0.88 от фуллблока)
	public SwampGrass(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab, String tooltip, Block setDirt) {
		super(materialIn, name, hardness, resistanse, soundtype, tab, tooltip, setDirt);
	
	}

}
