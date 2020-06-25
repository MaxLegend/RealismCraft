package com.legendgamer.realism.blocks.tree;

import com.google.common.collect.Lists;
import com.legendgamer.realism.API.VectorUtils;
import com.legendgamer.realism.API.metautils.BasicMetadataBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class XZBeam extends BasicMetadataBlock {

    public static final AxisAlignedBB LEFT_DOWN_X = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
    public static final AxisAlignedBB RIGHT_DOWN_X = VectorUtils.AABBInvert(LEFT_DOWN_X, EnumFacing.Axis.Z);
    public static final AxisAlignedBB LEFT_UP_X = VectorUtils.AABBInvert(LEFT_DOWN_X, EnumFacing.Axis.Y);
    public static final AxisAlignedBB RIGHT_UP_X = VectorUtils.AABBInvert(RIGHT_DOWN_X, EnumFacing.Axis.Y);

    public static final AxisAlignedBB LEFT_DOWN_Z = VectorUtils.AABBRotateForY(LEFT_DOWN_X, Rotation.COUNTERCLOCKWISE_90);
    public static final AxisAlignedBB RIGHT_DOWN_Z = VectorUtils.AABBRotateForY(RIGHT_DOWN_X, Rotation.COUNTERCLOCKWISE_90);
    public static final AxisAlignedBB LEFT_UP_Z = VectorUtils.AABBInvert(LEFT_DOWN_Z, EnumFacing.Axis.Y);
    public static final AxisAlignedBB RIGHT_UP_Z = VectorUtils.AABBInvert(RIGHT_DOWN_Z, EnumFacing.Axis.Y);

    public static final AxisAlignedBB HALF_UP = LEFT_UP_X.union(RIGHT_UP_X);
    public static final AxisAlignedBB HALF_DOWN = LEFT_DOWN_X.union(RIGHT_DOWN_X);

    public static final AxisAlignedBB HALF_LEFT_X = LEFT_DOWN_X.union(LEFT_UP_X);
    public static final AxisAlignedBB HALF_RIGHT_X = RIGHT_DOWN_X.union(RIGHT_UP_X);

    public static final AxisAlignedBB HALF_LEFT_Z = LEFT_DOWN_Z.union(LEFT_UP_Z);
    public static final AxisAlignedBB HALF_RIGHT_Z = RIGHT_DOWN_Z.union(RIGHT_UP_Z);

    public static final PropertyEnum<EnumCorner> CORNER = PropertyEnum.create("corner", EnumCorner.class);
    public final EnumFacing.Axis axis;

    public XZBeam(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab, EnumFacing.Axis axis) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
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

    @Nullable
    @Override
    public RayTraceResult collisionRayTrace(IBlockState blockState, World world, BlockPos pos, Vec3d start, Vec3d end) {
        LinkedList<RayTraceResult> list = Lists.newLinkedList();
        for (AxisAlignedBB aabb : getCollisionBoundingBoxList(blockState, world, pos)) list.add(rayTrace(pos, start, end, aabb));

        RayTraceResult finalRay = null;
        double d1 = 0.0D;
        for (RayTraceResult raytraceresult : list) {
            if (raytraceresult != null) {
                double d0 = raytraceresult.hitVec.squareDistanceTo(end);
                if (d0 > d1) {
                    finalRay = raytraceresult;
                    d1 = d0;
                }
            }
        }
        return finalRay;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        boolean flagX = axis == EnumFacing.Axis.X;
        switch (state.getValue(CORNER)) {
            default:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(world, pos));
                break;
            case TULC:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(world, pos));
                addCollisionBoxToList(pos, entityBox, collidingBoxes, flagX ? LEFT_DOWN_X : LEFT_DOWN_Z);
                break;
            case TURC:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(world, pos));
                addCollisionBoxToList(pos, entityBox, collidingBoxes, flagX ? RIGHT_DOWN_X : RIGHT_DOWN_Z);
                break;
            case TDLC:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(world, pos));
                addCollisionBoxToList(pos, entityBox, collidingBoxes, flagX ? LEFT_UP_X : LEFT_UP_Z);
                break;
            case TDRC:
                addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(world, pos));
                addCollisionBoxToList(pos, entityBox, collidingBoxes, flagX ? RIGHT_UP_X : RIGHT_UP_Z);
        }
    }

    public List<AxisAlignedBB> getCollisionBoundingBoxList(@Nonnull IBlockState state, World world, BlockPos pos) {
        boolean flagX = axis == EnumFacing.Axis.X;
        List<AxisAlignedBB> tempList = new ArrayList<>();
        tempList.add(getBoundingBox(state, world, pos));
        switch (state.getValue(CORNER)) {
            default:
                break;
            case TULC:
                tempList.add(flagX ? LEFT_DOWN_X : LEFT_DOWN_Z);
                break;
            case TURC:
                tempList.add(flagX ? RIGHT_DOWN_X : RIGHT_DOWN_Z);
                break;
            case TDLC:
                tempList.add(flagX ? LEFT_UP_X : LEFT_UP_Z);
                break;
            case TDRC:
                tempList.add(flagX ? RIGHT_UP_X : RIGHT_UP_Z);
        }
        return tempList;
    }

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
