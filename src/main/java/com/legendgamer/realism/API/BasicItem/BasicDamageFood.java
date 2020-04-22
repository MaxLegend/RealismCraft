package com.legendgamer.realism.API.BasicItem;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class BasicDamageFood extends BasicFood {
	public BasicDamageFood(int amount, float saturation, boolean isWolfFood, String name, int itemUseDuration,int maxEating,CreativeTabs tab)
	{
		super(amount, saturation, isWolfFood, name, itemUseDuration, tab);
		this.setMaxStackSize(1);
		this.setMaxDamage(maxEating);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {

		return true;
	}
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if (entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			if (entityplayer instanceof EntityPlayerMP)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
			}
		}
	
		stack.getItem().setDamage(stack, stack.getItemDamage() + 1);
		if(stack.getItemDamage() >= stack.getMaxDamage()) stack.shrink(1);
		System.out.println(stack.getItemDamage());
		return stack;
	}

}
