package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.reg.ItemsList;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class YBeam extends BasicBlock implements BeamMetadata {

    public static final AxisAlignedBB Y = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

    private final int itemMetadata;

    public YBeam(int itemMetadata, Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
        this.itemMetadata = itemMetadata;
    }

    @Override
    public int getItemMetadata() {
        return itemMetadata;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemsList.ITEM_BEAM;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return itemMetadata;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return Y;
    }
}