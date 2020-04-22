package com.legendgamer.realism.creativetabs;

import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabOres extends CreativeTabs {

	public TabOres(String label) {
		super(label);
		
	}
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(BlocksList.MAGMATIC_COPPER));
	}
}

