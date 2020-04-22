package com.legendgamer.realism.creativetabs;

import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabRock extends CreativeTabs {
	Item icon;
	public TabRock(String label) {
		super(label);
		
	}
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(BlocksList.MAGMATIC_DIRT));
	}
}

