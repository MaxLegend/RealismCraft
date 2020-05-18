package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.AABBUtils;
import com.legendgamer.realism.API.metautils.BasicMetadataBlock;
import com.legendgamer.realism.blocks.tree.trunk.IBlockBeam;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class XZBeam extends BasicMetadataBlock implements IBlockBeam {

    public static final AxisAlignedBB LEFT_DOWN_X = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
    public static final AxisAlignedBB RIGHT_DOWN_X = AABBUtils.invert(LEFT_DOWN_X, EnumFacing.Axis.Z);
    public static final AxisAlignedBB LEFT_UP_X = AABBUtils.invert(LEFT_DOWN_X, EnumFacing.Axis.Y);
    public static final AxisAlignedBB RIGHT_UP_X = AABBUtils.invert(RIGHT_DOWN_X, EnumFacing.Axis.Y);

    public static final AxisAlignedBB LEFT_DOWN_Z = AABBUtils.rotateForY(LEFT_DOWN_X, Rotation.COUNTERCLOCKWISE_90);
    public static final AxisAlignedBB RIGHT_DOWN_Z = AABBUtils.rotateForY(RIGHT_DOWN_X, Rotation.COUNTERCLOCKWISE_90);
    public static final AxisAlignedBB LEFT_UP_Z = AABBUtils.invert(LEFT_DOWN_Z, EnumFacing.Axis.Y);
    public static final AxisAlignedBB RIGHT_UP_Z = AABBUtils.invert(RIGHT_DOWN_Z, EnumFacing.Axis.Y);

    public static final AxisAlignedBB HALF_UP = LEFT_UP_X.union(RIGHT_UP_X);
    public static final AxisAlignedBB HALF_DOWN = LEFT_DOWN_X.union(RIGHT_DOWN_X);

    public static final AxisAlignedBB HALF_LEFT_X = LEFT_DOWN_X.union(LEFT_UP_X);
    public static final AxisAlignedBB HALF_RIGHT_X = RIGHT_DOWN_X.union(RIGHT_UP_X);

    public static final AxisAlignedBB HALF_LEFT_Z = LEFT_DOWN_Z.union(LEFT_UP_Z);
    public static final AxisAlignedBB HALF_RIGHT_Z = RIGHT_DOWN_Z.union(RIGHT_UP_Z);

//    private Map<Integer, Set<AxisAlignedBB>> metaAABB = new HashMap<>();

    public static final PropertyEnum<EnumCorner> CORNER = PropertyEnum.create("corner", EnumCorner.class);
    private final EnumFacing.Axis axis;

    public XZBeam(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab, EnumFacing.Axis axis) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
        //Initialize map from class AxisAlignedBB values with reflection. This is slowly, but automatic
//        int x = 0;
//        for (Field e : getClass().getFields()) {
//            if (e.getType().equals(AxisAlignedBB.class)) {
//                Object o = null;
//                try { o = e.get(null); } catch (IllegalAccessException ex) { ex.printStackTrace(); }
//                metaAABB.put(x, Collections.singleton((AxisAlignedBB) o));
//            }
//            ++x;
//        }
        this.axis = axis;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        boolean flagX = axis == EnumFacing.Axis.X;
        switch (state.getValue(CORNER)) {
            case DLC:
                return flagX ? LEFT_DOWN_X : LEFT_DOWN_Z;
            case DRC:
                return flagX ? RIGHT_DOWN_X : RIGHT_DOWN_Z;
            case ULC:
                return flagX ? LEFT_UP_X : LEFT_UP_Z;
            case URC:
                return flagX ? RIGHT_UP_X : RIGHT_UP_Z;
            case LC:
                return flagX ? HALF_LEFT_X : HALF_LEFT_Z;
            case RC:
                return flagX ? HALF_RIGHT_X : HALF_RIGHT_Z;
            case UC:
            case TULC:
            case TURC:
                return HALF_UP;
            case DC:
            case TDLC:
            case TDRC:
                return HALF_DOWN;
            default:
                return FULL_BLOCK_AABB;
        }
    }

//    @Override
//    public EnumBlockRenderType getRenderType(IBlockState state) {
//        return EnumBlockRenderType.INVISIBLE;
//    }

    @Override
    protected IProperty<?>[] createBlockProperties() {
        return new IProperty[]{CORNER};
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

        EnumCorner(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
