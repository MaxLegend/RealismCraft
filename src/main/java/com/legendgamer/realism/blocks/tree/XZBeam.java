package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.metautils.BasicMetadataBlock;
import com.legendgamer.realism.API.metautils.IBlockType;
import com.legendgamer.realism.items.ItemBeam;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class XZBeam extends BasicMetadataBlock implements IBlockType<ItemBeam.BeamType> {

    public static final PropertyEnum<EnumCorner> CORNER = PropertyEnum.create("corner", EnumCorner.class);
    private ItemBeam.BeamType beamType;

    public XZBeam(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab, ItemBeam.BeamType beamType) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
        this.beamType = beamType;
    }

    @Override
    protected IProperty<?>[] createBlockProperties() {
        return new IProperty[] {CORNER};
    }

    @Override
    protected IBlockState createDefaultState() {
        return getDefaultState().withProperty(CORNER, EnumCorner.DLC);
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

    public enum EnumCorner implements IStringSerializable {
        DLC("dlc"), //Down left corner
        DRC("drc"), //Down right corner
        ULC("ulc"), //Up left corner
        URC("urc"), //Up right corner
        LC("lc"), //Double left corner
        RC("rc"), //Double right corner
        UC("uc"), //Double up corner
        DC("dc"), //Double down corner
        TULC("tulc"), //Trouble up left corner
        TURC("turc"), //Trouble up right corner
        TDLC("tdlc"), //Trouble down left corner
        TDRC("tdrc"), //Trouble down right corner
        FULL("full"); //Full

        private final String name;

        EnumCorner(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
