package com.legendgamer.realism.creativetabs;

import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.ItemsList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabFruits extends CreativeTabs {
	public TabFruits(String label) {
		super(label);
	//	this.setBackgroundImageName(Realism.MODID + ":maincreativebg.png");
	}
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemsList.I_PEAR_FRUIT);
	}
}