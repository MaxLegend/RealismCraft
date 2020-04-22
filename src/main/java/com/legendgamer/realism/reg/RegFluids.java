package com.legendgamer.realism.reg;

import com.legendgamer.realism.blocks.liquid.CustomFluid;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegFluids
{

   public static final Fluid FRESH_WATER = new CustomFluid("fresh_water", "fresh_water_still", "fresh_water_flow");
   public static final Fluid SALTY_WATER = new CustomFluid("salty_water", "salty_water_still", "salty_water_flow");
   public static final Fluid SWAMP_WATER = new CustomFluid("swamp_water", "swamp_water_still", "swamp_water_flow");
   
   public static void register()
   {
       FluidRegistry.registerFluid(FRESH_WATER);
       FluidRegistry.registerFluid(SALTY_WATER);
       FluidRegistry.registerFluid(SWAMP_WATER);
   }

   public static void registerRender()
   {
       modelLoader(BlocksList.FRESH_WATER, "fresh_water");
       modelLoader(BlocksList.SALTY_WATER, "salty_water");
       modelLoader(BlocksList.SWAMP_WATER, "swamp_water"); 
   }

   @SideOnly(Side.CLIENT)
   public static void modelLoader(Block block, String variant)
   {
       ModelResourceLocation loc = new ModelResourceLocation("realism:fluid", variant);
       Item milk = Item.getItemFromBlock(block);

       ModelBakery.registerItemVariants(milk);
       ModelLoader.setCustomMeshDefinition(milk, stack -> loc);
       ModelLoader.setCustomStateMapper(block, new StateMapperBase()
       {
           protected ModelResourceLocation getModelResourceLocation(IBlockState state)
           {
               return loc;
           }
       });
   }
}
