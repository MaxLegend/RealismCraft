package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.API.metautils.IBlockType;
import com.legendgamer.realism.items.ItemBeam;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class YBeam extends BasicBlock implements IBlockType<ItemBeam.BeamType> {
    private ItemBeam.BeamType beamType;
    public YBeam(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab, ItemBeam.BeamType beamType) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
        this.beamType = beamType;
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
    public ItemBeam.BeamType getBlockType(int meta) {
        return beamType;
    }
}