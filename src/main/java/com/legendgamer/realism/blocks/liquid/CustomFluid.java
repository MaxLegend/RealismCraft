package com.legendgamer.realism.blocks.liquid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class CustomFluid extends Fluid
{

   public CustomFluid(String fluidName, String still, String flowing)
   {
       super(fluidName, new ResourceLocation("realism", "blocks/liquid/" + still), new ResourceLocation("realism", "blocks/liquid/" + flowing));
       setColor(0xFFFFFFFF);
       setDensity(10);
       
   }

}
