package com.legendgamer.realism.blocks.liquid;

import javax.annotation.Nonnull;

import com.legendgamer.realism.Realism;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomWater extends BlockFluidClassic
{

   public CustomWater(Fluid fluid, String name)
   {
       super(fluid, Material.WATER);
       setCreativeTab(Realism.tabRocks);
       setUnlocalizedName(name);
       setRegistryName(name);
       this.setLightOpacity(300);
       setRenderLayer(BlockRenderLayer.TRANSLUCENT);
      
   }
//   @Override
//   public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos)
//   {
//       return 1;
//   }

//   protected int getRenderedDepth(IBlockState state)
//   {
//       int i = this.getDepth(state);
//       return i >= 8 ? 0 : i;
//   }


   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state)
   {
       super.onBlockAdded(world, pos, state);
       mergerFluids(pos, world);
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
   {
       super.neighborChanged(state, world, pos, blockIn, fromPos);
       mergerFluids(pos, world);
   }

   private void mergerFluids(BlockPos pos, World world)
   {
       for(EnumFacing facing : EnumFacing.values())
       {
           Block block = world.getBlockState(pos.offset(facing)).getBlock();
           //Если вода, то ставим камень
           if(block == Blocks.WATER || block == Blocks.FLOWING_WATER)
           {
               world.setBlockState(pos.offset(facing), Blocks.STONE.getDefaultState());
           }
           //Если лава, ставим кирпичный блок
           else if(block == Blocks.LAVA || block == Blocks.FLOWING_LAVA)
           {
               world.setBlockState(pos.offset(facing), Blocks.BRICK_BLOCK.getDefaultState());
           }
       }
   }
}
