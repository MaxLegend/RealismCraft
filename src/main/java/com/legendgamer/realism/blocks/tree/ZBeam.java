package com.legendgamer.realism.blocks.tree;

import com.google.common.collect.Lists;
import com.legendgamer.realism.API.VectorUtils;
import com.legendgamer.realism.API.metautils.BasicMetadataBlock;
import com.legendgamer.realism.reg.ItemsList;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
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
import java.util.Random;

public class ZBeam extends BasicMetadataBlock implements BeamMetadata {

    public static final AxisAlignedBB LEFT_DOWN_Z = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 1.0D);
    public static final AxisAlignedBB RIGHT_DOWN_Z = VectorUtils.AABBInvert(LEFT_DOWN_Z, EnumFacing.Axis.X);
    public static final AxisAlignedBB LEFT_UP_Z = VectorUtils.AABBInvert(LEFT_DOWN_Z, EnumFacing.Axis.Y);
    public static final AxisAlignedBB RIGHT_UP_Z = VectorUtils.AABBInvert(RIGHT_DOWN_Z, EnumFacing.Axis.Y);

    public static final AxisAlignedBB HALF_UP = LEFT_UP_Z.union(RIGHT_UP_Z);
    public static final AxisAlignedBB HALF_DOWN = LEFT_DOWN_Z.union(RIGHT_DOWN_Z);

    public static final AxisAlignedBB HALF_LEFT_Z = LEFT_DOWN_Z.union(LEFT_UP_Z);
    public static final AxisAlignedBB HALF_RIGHT_Z = RIGHT_DOWN_Z.union(RIGHT_UP_Z);

    public static final PropertyEnum<CornerContainer.EnumCorner> CORNER = CornerContainer.CORNER;

    private final int itemMetadata;

    public ZBeam(int itemMetadata, Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
        this.itemMetadata = itemMetadata;
    }

    @Override
    public int getItemMetadata() {
        return itemMetadata;
    }

    @Override
    public int quantityDropped(@Nonnull IBlockState state, int fortune, Random random) {
        switch (state.getValue(CORNER)) {
            case DLC:
            case DRC:
            case ULC:
            case URC:
                return 1;
            case LC:
            case RC:
            case UC:
            case DC:
            case DLD:
            case DRD:
                return 2;
            case TULC:
            case TURC:
            case TDLC:
            case TDRC:
                return 3;
            default:
                return 4;
        }
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
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(CORNER)) {
            case DLC:
                return LEFT_DOWN_Z;
            case DRC:
                return RIGHT_DOWN_Z;
            case ULC:
            case DLD:
                return LEFT_UP_Z;
            case URC:
            case DRD:
                return RIGHT_UP_Z;
            case LC:
                return HALF_LEFT_Z;
            case RC:
                return HALF_RIGHT_Z;
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
    public void addCollisionBoxToList(@Nonnull IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        List<AxisAlignedBB> tempList = getCollisionBoundingBoxList(state, world, pos);
        for (AxisAlignedBB axisAlignedBB : tempList) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, axisAlignedBB);
        }
    }

    public List<AxisAlignedBB> getCollisionBoundingBoxList(@Nonnull IBlockState state, World world, BlockPos pos) {
        List<AxisAlignedBB> tempList = new ArrayList<>();
        tempList.add(getBoundingBox(state, world, pos));
        switch (state.getValue(CORNER)) {
            default:
                break;
            case TULC:
            case DRD:
                tempList.add(LEFT_DOWN_Z);
                break;
            case TURC:
            case DLD:
                tempList.add(RIGHT_DOWN_Z);
                break;
            case TDLC:
                tempList.add(LEFT_UP_Z);
                break;
            case TDRC:
                tempList.add(RIGHT_UP_Z);
        }
        return tempList;
    }

    @Override
    protected IProperty<?>[] createBlockProperties() {
        return new IProperty[]{CORNER};
    }

    @Override
    protected IBlockState createDefaultState() {
        return getDefaultState().withProperty(CORNER, CornerContainer.EnumCorner.DLC);
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
}
