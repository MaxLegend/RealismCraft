package com.legendgamer.realism.reg;

import com.legendgamer.realism.API.metautils.BasicMetadataItem;
import com.legendgamer.realism.Realism;
import com.legendgamer.realism.API.BasicItem.BasicItem;

import com.legendgamer.realism.items.ItemBeam;
import net.minecraft.item.Item;

public class ItemsList {
	/**
	 * Для каждого дерева - своя палка, свои плоды
	 */
	//public static final Item IL_ASH = new BasicItem("il_ash", 0, null);
	
	public static final Item I_ASH_FRUIT = new BasicItem("fruits/i_ash_fruit", 0, Realism.tabFruits);
	public static final Item I_BIRCH_FRUIT = new BasicItem("fruits/i_birch_fruit", 0, Realism.tabFruits);
	public static final Item I_LINDEN_FRUIT = new BasicItem("fruits/i_linden_fruit", 0, Realism.tabFruits);
	public static final Item I_OAK_FRUIT = new BasicItem("fruits/i_oak_fruit", 0, Realism.tabFruits);
	public static final Item I_SPRUCE_FRUIT = new BasicItem("fruits/i_spruce_fruit", 0, Realism.tabFruits);
	public static final Item I_PINE_FRUIT = new BasicItem("fruits/i_pine_fruit", 0, Realism.tabFruits);
	public static final Item I_LARCH_FRUIT = new BasicItem("fruits/i_larch_fruit", 0, Realism.tabFruits);
	public static final Item I_POPLAR_FRUIT = new BasicItem("fruits/i_poplar_fruit", 0, Realism.tabFruits);
	public static final Item I_PEAR_FRUIT = new BasicItem("fruits/i_pear_fruit", 0, Realism.tabFruits);
	public static final Item I_PEAR_G_FRUIT = new BasicItem("fruits/i_pear_g_fruit", 0, Realism.tabFruits);

	public static final BasicMetadataItem ITEM_BEAM = new ItemBeam("tree/item_beams", 4, Realism.tabTrees);
}
