package com.legendgamer.realism.API.BasicBlock;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicBlockWithInfo extends BasicBlock {
	String tooltips;
	public BasicBlockWithInfo(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,CreativeTabs tab, String tooltip) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		this.tooltips = tooltip;
	}
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
    	tooltip.add(I18n.translateToLocal(tooltips));
    	
    }
}
