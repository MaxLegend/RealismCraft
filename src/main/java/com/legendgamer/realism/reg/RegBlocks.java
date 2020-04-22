package com.legendgamer.realism.reg;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegBlocks {
	
	
	public static void register() {

		
		//ores
		registerBlock(BlocksList.MAGMATIC_COPPER);
		registerBlock(BlocksList.SEDIMENTARY_COPPER);
		registerBlock(BlocksList.METAMORPHIC_COPPER);	
		registerBlock(BlocksList.MAGMATIC_TIN);
		registerBlock(BlocksList.SEDIMENTARY_TIN);
		registerBlock(BlocksList.METAMORPHIC_TIN);
		registerBlock(BlocksList.SEDIMENTARY_COAL);
		registerBlock(BlocksList.MAGMATIC_COAL);
		registerBlock(BlocksList.METAMORPHIC_COAL);
		registerBlock(BlocksList.MAGMATIC_NICKEL);
		registerBlock(BlocksList.METAMORPHIC_NICKEL);
		registerBlock(BlocksList.MAGMATIC_IRON);
		registerBlock(BlocksList.SEDIMENTARY_IRON);
		registerBlock(BlocksList.MAGMATIC_CHROME);
		registerBlock(BlocksList.MAGMATIC_VANADIUM);
		registerBlock(BlocksList.METAMORPHIC_VANADIUM);
		registerBlock(BlocksList.SEDIMENTARY_TUNGSTEN);
		registerBlock(BlocksList.MAGMATIC_TITAN);
		registerBlock(BlocksList.METAMORPHIC_TITAN);
		
		//water
		registerBlock(BlocksList.FRESH_WATER);
		registerBlock(BlocksList.SALTY_WATER);
		registerBlock(BlocksList.SWAMP_WATER);
		
		registerBlock(BlocksList.REAL_ASH_SAPLING);
		registerBlock(BlocksList.REAL_OAK_SAPLING);
		registerBlock(BlocksList.REAL_PEAR_SAPLING);	
		registerBlock(BlocksList.REAL_PINE_SAPLING);
		registerBlock(BlocksList.REAL_SPRUCE_SAPLING);
		registerBlock(BlocksList.REAL_LARCH_SAPLING);
		registerBlock(BlocksList.REAL_LINDEN_SAPLING);
		registerBlock(BlocksList.REAL_BIRCH_SAPLING);
		registerBlock(BlocksList.REAL_POPLAR_SAPLING);
		
		registerBlock(BlocksList.REAL_ASH_BRANCH);
		registerBlock(BlocksList.REAL_BIRCH_BRANCH);
		registerBlock(BlocksList.REAL_LARCH_BRANCH);
		registerBlock(BlocksList.REAL_LINDEN_BRANCH);
		registerBlock(BlocksList.REAL_OAK_BRANCH);
		registerBlock(BlocksList.REAL_PEAR_BRANCH);
		registerBlock(BlocksList.REAL_PINE_BRANCH);
		registerBlock(BlocksList.REAL_POPLAR_BRANCH);
		registerBlock(BlocksList.REAL_SPRUCE_BRANCH);
		
		registerBlock(BlocksList.REAL_ASH);
		registerBlock(BlocksList.REAL_BIRCH);
		registerBlock(BlocksList.REAL_LARCH);
		registerBlock(BlocksList.REAL_LINDEN);
		registerBlock(BlocksList.REAL_OAK);
		registerBlock(BlocksList.REAL_PEAR);
		registerBlock(BlocksList.REAL_PINE);
		registerBlock(BlocksList.REAL_POPLAR);
		registerBlock(BlocksList.REAL_SPRUCE);
		
		registerBlock(BlocksList.REAL_ASH_LEAVES);
		registerBlock(BlocksList.REAL_BIRCH_LEAVES);
		registerBlock(BlocksList.REAL_LARCH_LEAVES);
		registerBlock(BlocksList.REAL_LINDEN_LEAVES);
		registerBlock(BlocksList.REAL_OAK_LEAVES);
		registerBlock(BlocksList.REAL_PEAR_LEAVES);
		registerBlock(BlocksList.REAL_PINE_LEAVES);
		registerBlock(BlocksList.REAL_POPLAR_LEAVES);
		registerBlock(BlocksList.REAL_SPRUCE_LEAVES);

		
		registerBlock(BlocksList.METAMORPHIC_SAND);

		registerBlock(BlocksList.METAMORPHIC_GRAVEL);
	
		registerBlock(BlocksList.METAMORPHIC_STONE);
		registerBlock(BlocksList.METAMORPHIC_CLAY);
		registerBlock(BlocksList.METAMORPHIC_CLAYSTONE);
		
		registerBlock(BlocksList.SEDIMENTARY_SAND);

		registerBlock(BlocksList.SEDIMENTARY_GRAVEL);
	
		registerBlock(BlocksList.SEDIMENTARY_STONE);
		registerBlock(BlocksList.SEDIMENTARY_CLAY);
		registerBlock(BlocksList.SEDIMENTARY_CLAYSTONE);
	
		registerBlock(BlocksList.MAGMATIC_DIRT);
		registerBlock(BlocksList.MAGMATIC_GRASS);
	
		registerBlock(BlocksList.METAMORPHIC_DIRT);	
		registerBlock(BlocksList.METAMORPHIC_GRASS);
	
		
		registerBlock(BlocksList.SEDIMENTARY_DIRT);
		registerBlock(BlocksList.SEDIMENTARY_GRASS);
		registerBlock(BlocksList.MAGMATIC_SAND);

		registerBlock(BlocksList.MAGMATIC_GRAVEL);
	
		registerBlock(BlocksList.MAGMATIC_STONE);
		registerBlock(BlocksList.MAGMATIC_CLAY);
		registerBlock(BlocksList.MAGMATIC_CLAYSTONE);
		
		//herbs
		registerBlock(BlocksList.FERN);
		registerBlock(BlocksList.SEDGE);
		registerBlock(BlocksList.WIPE);
		
	}
	public static void registerRender() {
		//herbs
		registerRenderBlock(BlocksList.SEDGE);
		registerRenderBlock(BlocksList.FERN);
		registerRenderBlock(BlocksList.WIPE);
		
		//ores
		registerRenderBlock(BlocksList.MAGMATIC_COPPER);
		registerRenderBlock(BlocksList.SEDIMENTARY_COPPER);
		registerRenderBlock(BlocksList.METAMORPHIC_COPPER);	
		registerRenderBlock(BlocksList.MAGMATIC_TIN);
		registerRenderBlock(BlocksList.SEDIMENTARY_TIN);
		registerRenderBlock(BlocksList.METAMORPHIC_TIN);
		registerRenderBlock(BlocksList.SEDIMENTARY_COAL);
		registerRenderBlock(BlocksList.MAGMATIC_COAL);
		registerRenderBlock(BlocksList.MAGMATIC_NICKEL);
		registerRenderBlock(BlocksList.METAMORPHIC_NICKEL);
		registerRenderBlock(BlocksList.MAGMATIC_IRON);
		registerRenderBlock(BlocksList.SEDIMENTARY_IRON);
		registerRenderBlock(BlocksList.MAGMATIC_CHROME);
		registerRenderBlock(BlocksList.MAGMATIC_VANADIUM);
		registerRenderBlock(BlocksList.METAMORPHIC_VANADIUM);
		registerRenderBlock(BlocksList.SEDIMENTARY_TUNGSTEN);
		registerRenderBlock(BlocksList.MAGMATIC_TITAN);
		registerRenderBlock(BlocksList.METAMORPHIC_TITAN);
		
		registerRenderBlock(BlocksList.REAL_ASH_SAPLING);
		registerRenderBlock(BlocksList.REAL_OAK_SAPLING);
		registerRenderBlock(BlocksList.REAL_PEAR_SAPLING);
		registerRenderBlock(BlocksList.REAL_PINE_SAPLING);
		registerRenderBlock(BlocksList.REAL_SPRUCE_SAPLING);
		registerRenderBlock(BlocksList.REAL_LARCH_SAPLING);
		registerRenderBlock(BlocksList.REAL_LINDEN_SAPLING);
		registerRenderBlock(BlocksList.REAL_BIRCH_SAPLING);
		registerRenderBlock(BlocksList.REAL_POPLAR_SAPLING);
		
	 	registerRenderBlock(BlocksList.REAL_ASH_BRANCH);
		registerRenderBlock(BlocksList.REAL_BIRCH_BRANCH);
		registerRenderBlock(BlocksList.REAL_LARCH_BRANCH);
	 	registerRenderBlock(BlocksList.REAL_LINDEN_BRANCH);
		registerRenderBlock(BlocksList.REAL_OAK_BRANCH);
		registerRenderBlock(BlocksList.REAL_PEAR_BRANCH);
	 	registerRenderBlock(BlocksList.REAL_PINE_BRANCH);
		registerRenderBlock(BlocksList.REAL_POPLAR_BRANCH);
		registerRenderBlock(BlocksList.REAL_SPRUCE_BRANCH);
		
		registerRenderBlock(BlocksList.REAL_ASH);
		registerRenderBlock(BlocksList.REAL_BIRCH);
		registerRenderBlock(BlocksList.REAL_LARCH);
		registerRenderBlock(BlocksList.REAL_LINDEN);
		registerRenderBlock(BlocksList.REAL_OAK);
		registerRenderBlock(BlocksList.REAL_PEAR);
		registerRenderBlock(BlocksList.REAL_PINE);
		registerRenderBlock(BlocksList.REAL_POPLAR);
		registerRenderBlock(BlocksList.REAL_SPRUCE);
		
		registerRenderBlock(BlocksList.REAL_ASH_LEAVES);
		registerRenderBlock(BlocksList.REAL_BIRCH_LEAVES);
		registerRenderBlock(BlocksList.REAL_LARCH_LEAVES);
		registerRenderBlock(BlocksList.REAL_LINDEN_LEAVES);
		registerRenderBlock(BlocksList.REAL_OAK_LEAVES);
		registerRenderBlock(BlocksList.REAL_PEAR_LEAVES);
		registerRenderBlock(BlocksList.REAL_PINE_LEAVES);
		registerRenderBlock(BlocksList.REAL_POPLAR_LEAVES);
		registerRenderBlock(BlocksList.REAL_SPRUCE_LEAVES);
		
		registerRenderBlock(BlocksList.MAGMATIC_SAND);
		registerRenderBlock(BlocksList.MAGMATIC_GRAVEL);
		registerRenderBlock(BlocksList.MAGMATIC_DIRT);
		registerRenderBlock(BlocksList.MAGMATIC_GRASS);
		registerRenderBlock(BlocksList.MAGMATIC_STONE);
		registerRenderBlock(BlocksList.MAGMATIC_CLAY);
		registerRenderBlock(BlocksList.MAGMATIC_CLAYSTONE);
		
		
		registerRenderBlock(BlocksList.SEDIMENTARY_SAND);
		registerRenderBlock(BlocksList.SEDIMENTARY_DIRT);
		registerRenderBlock(BlocksList.SEDIMENTARY_GRAVEL);
		registerRenderBlock(BlocksList.SEDIMENTARY_GRASS);
		registerRenderBlock(BlocksList.SEDIMENTARY_STONE);
		registerRenderBlock(BlocksList.SEDIMENTARY_CLAY);
		registerRenderBlock(BlocksList.SEDIMENTARY_CLAYSTONE);
		registerRenderBlock(BlocksList.METAMORPHIC_COAL);
		registerRenderBlock(BlocksList.METAMORPHIC_SAND);
		registerRenderBlock(BlocksList.METAMORPHIC_DIRT);
		registerRenderBlock(BlocksList.METAMORPHIC_GRAVEL);
		registerRenderBlock(BlocksList.METAMORPHIC_GRASS);
		registerRenderBlock(BlocksList.METAMORPHIC_STONE);
		registerRenderBlock(BlocksList.METAMORPHIC_CLAY);
		registerRenderBlock(BlocksList.METAMORPHIC_CLAYSTONE);
	}
	public static void registerBlock(Block block) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		


	}
	public static void registerBlockMeta(Block block, ItemBlock itemBlock) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getRegistryName()));
	}
	public static void registerRenderBlock(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	



}