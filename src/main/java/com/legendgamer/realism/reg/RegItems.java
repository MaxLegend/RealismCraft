package com.legendgamer.realism.reg;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegItems {
	
    public static void register() {
    	registerItem(ItemsList.I_ASH_FRUIT);
    	registerItem(ItemsList.I_BIRCH_FRUIT);
    	registerItem(ItemsList.I_LARCH_FRUIT);
    	registerItem(ItemsList.I_LINDEN_FRUIT);
    	registerItem(ItemsList.I_OAK_FRUIT);
    	registerItem(ItemsList.I_PEAR_FRUIT);
    	registerItem(ItemsList.I_PEAR_G_FRUIT);
    	registerItem(ItemsList.I_PINE_FRUIT);
    	registerItem(ItemsList.I_POPLAR_FRUIT);
    	registerItem(ItemsList.I_SPRUCE_FRUIT);
    }
    public static void registerRender() {
    	registerItemRender(ItemsList.I_ASH_FRUIT);
    	registerItemRender(ItemsList.I_BIRCH_FRUIT);
    	registerItemRender(ItemsList.I_LARCH_FRUIT);
    	registerItemRender(ItemsList.I_LINDEN_FRUIT);
    	registerItemRender(ItemsList.I_OAK_FRUIT);
    	registerItemRender(ItemsList.I_PEAR_FRUIT);
    	registerItemRender(ItemsList.I_PEAR_G_FRUIT);
    	registerItemRender(ItemsList.I_PINE_FRUIT);
    	registerItemRender(ItemsList.I_POPLAR_FRUIT);
    	registerItemRender(ItemsList.I_SPRUCE_FRUIT);
    }
    private static void registerItem(Item item) {
        ForgeRegistries.ITEMS.register(item);
    }
    private static void registerItemRender(Item item) {
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
