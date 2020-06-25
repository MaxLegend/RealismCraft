package com.legendgamer.realism.API;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nonnull;

public class AABBUtils {
    public static AxisAlignedBB invert(AxisAlignedBB aabb, @Nonnull EnumFacing.Axis axis) {
        switch (axis) {
            case X:
                return new AxisAlignedBB(aabb.maxX, aabb.minY, aabb.minZ, aabb.minX, aabb.maxY, aabb.maxZ);
            case Y:
                return new AxisAlignedBB(aabb.minX, aabb.maxY, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ);
            case Z:
                return new AxisAlignedBB(aabb.minX, aabb.minY, aabb.maxZ, aabb.maxX, aabb.maxY, aabb.minZ);
            default:
                return aabb;
        }
    }

    public static AxisAlignedBB rotateForY(AxisAlignedBB aabb, @Nonnull Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
                return new AxisAlignedBB(aabb.maxZ, aabb.minY, aabb.minX, aabb.minZ, aabb.maxY, aabb.maxX);
            case CLOCKWISE_90:
                return new AxisAlignedBB(aabb.minZ, aabb.minY, aabb.minX, aabb.maxZ, aabb.maxY, aabb.maxX);
            case CLOCKWISE_180:
                return new AxisAlignedBB(aabb.maxX, aabb.minY, aabb.maxZ, aabb.minX, aabb.maxY, aabb.minZ);
            default:
                return aabb;
        }
    }
}