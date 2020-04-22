package com.legendgamer.realism.creativetabs;

import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabTrees extends CreativeTabs {
	public TabTrees(String label) {
		super(label);
	//	this.setBackgroundImageName(Realism.MODID + ":maincreativebg.png");
	}
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(BlocksList.REAL_LARCH));
	}
}

