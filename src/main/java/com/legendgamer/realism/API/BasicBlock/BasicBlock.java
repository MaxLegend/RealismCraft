package com.legendgamer.realism.API.BasicBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Basics API class by block
 * @author LegendGamer
 */
public class BasicBlock extends Block {

	public BasicBlock(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype,CreativeTabs tab) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setCreativeTab(tab);
	}
}