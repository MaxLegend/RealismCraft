package com.legendgamer.realism.API.BasicItem;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
/**
 * Basics API class by Damage Item
 * @author LegendGamer
 */
public class BasicDamageItem extends Item{
	public BasicDamageItem(String name,int maxStackSize,int maxDamage,CreativeTabs tab)
	{
		this.setRegistryName(name);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxStackSize);
		this.setMaxDamage(maxDamage);
	}
	  @Override
	  public ItemStack getContainerItem(ItemStack stack)
	  {
	    stack.attemptDamageItem(1, new Random(), null);
	    return stack.copy();
	  }
	  
	  @Override
	  public boolean hasContainerItem(ItemStack stack) {
	         return true;
	  }
}

