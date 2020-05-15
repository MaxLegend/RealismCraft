package com.legendgamer.realism.proxy;

import javax.annotation.Nullable;

import com.legendgamer.realism.Realism;
import com.legendgamer.realism.reg.BlocksList;
import com.legendgamer.realism.reg.RegBlocks;
import com.legendgamer.realism.reg.RegEvents;
import com.legendgamer.realism.reg.RegFluids;
import com.legendgamer.realism.reg.RegItems;
import com.legendgamer.realism.reg.RegRenderMetaBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		RegFluids.registerRender();
	//	  OBJLoader.INSTANCE.addDomain(Realism.MODID);

		MinecraftForge.EVENT_BUS.register(new RegRenderMetaBlocks());
	}

	@Override
	public void init(FMLInitializationEvent event) {

		
		RegBlocks.registerRender();
		RegItems.registerRender();
		
		RegEvents.Client();

		BlockColors blockcolor = FMLClientHandler.instance().getClient().getBlockColors(); 
		blockcolor.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
			
				 return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
			}
			
		},BlocksList.FERN, 
		BlocksList.WIPE, 
		BlocksList.SEDGE, 
		BlocksList.MAGMATIC_GRASS,BlocksList.MAGMATIC_SWAMP_GRASS,
		BlocksList.METAMORPHIC_GRASS,BlocksList.METAMORPHIC_SWAMP_GRASS,
		BlocksList.SEDIMENTARY_GRASS,BlocksList.SEDIMENTARY_SWAMP_GRASS,
		BlocksList.REAL_ASH_LEAVES,BlocksList.REAL_ASH_SAPLING
		,BlocksList.REAL_BIRCH_LEAVES,BlocksList.REAL_BIRCH_SAPLING
		,BlocksList.REAL_LARCH_LEAVES,BlocksList.REAL_LARCH_SAPLING
		,BlocksList.REAL_OAK_LEAVES,BlocksList.REAL_OAK_SAPLING
		,BlocksList.REAL_PINE_LEAVES,BlocksList.REAL_PINE_SAPLING
		,BlocksList.REAL_LINDEN_LEAVES,BlocksList.REAL_LINDEN_SAPLING
		,BlocksList.REAL_PEAR_LEAVES,BlocksList.REAL_PEAR_SAPLING
		,BlocksList.REAL_SPRUCE_LEAVES,BlocksList.REAL_SPRUCE_SAPLING
		,BlocksList.REAL_POPLAR_LEAVES,BlocksList.REAL_POPLAR_SAPLING,
		BlocksList.OAK_FRUIT,BlocksList.ASH_FRUIT,BlocksList.LINDEN_FRUIT,BlocksList.BIRCH_FRUIT,BlocksList.PEAR_FRUIT,BlocksList.LARCH_FRUIT,BlocksList.PINE_FRUIT,BlocksList.POPLAR_FRUIT,BlocksList.SPRUCE_FRUIT
				);
		
		super.init(event);
	}
	


	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);


	}
}
