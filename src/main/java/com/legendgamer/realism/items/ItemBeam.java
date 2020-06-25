package com.legendgamer.realism.items;

import com.legendgamer.realism.API.metautils.BasicMetadataItem;
import com.legendgamer.realism.API.metautils.IItemEnum;
import com.legendgamer.realism.blocks.tree.XZBeam;
import com.legendgamer.realism.blocks.tree.YBeam;
import com.legendgamer.realism.reg.BlocksList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

//world\.setBlockState\((.*)\)+;
//return setBlockWithMayPlace\(world, \1, facing\);
public class ItemBeam extends BasicMetadataItem<ItemBeam.BeamType> {

    public ItemBeam(String name, int maxStackSize, CreativeTabs tab) {
        super(name, maxStackSize, tab);
    }

    @Override
    public Class<BeamType> setEnum() {
        return BeamType.class;
    }

    private boolean mayPlace(@Nonnull World world, BlockPos pos, EnumFacing sidePlacedOn, @Nonnull IBlockState newState) {
        IBlockState state = world.getBlockState(pos);
        if (newState.getBlock() instanceof XZBeam) {
            XZBeam block = (XZBeam) newState.getBlock();
            List<AxisAlignedBB> axisAlignedBB = block.getCollisionBoundingBoxList(newState, world, pos);
            for (AxisAlignedBB aabb : axisAlignedBB) return aabb == Block.NULL_AABB || world.checkNoEntityCollision(aabb.offset(pos));
            if (state.getBlock() instanceof XZBeam) return true;
            else return state.getBlock().isReplaceable(world, pos) && newState.getBlock().canPlaceBlockOnSide(world, pos, sidePlacedOn);
        }
        AxisAlignedBB aabb = newState.getBlock().getCollisionBoundingBox(state, world, pos);
        if (aabb != Block.NULL_AABB && !world.checkNoEntityCollision(aabb.offset(pos))) return false;
        return state.getBlock().isReplaceable(world, pos) && newState.getBlock().canPlaceBlockOnSide(world, pos, sidePlacedOn);
    }

    @Override
    public EnumActionResult onItemUse(@Nonnull EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState stateY = getBeamType(stack).beamTypeY.getDefaultState();
        IBlockState stateX = getBeamType(stack).beamTypeX.getDefaultState();
        IBlockState stateZ = getBeamType(stack).beamTypeZ.getDefaultState();
        if (!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack)) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof XZBeam) {
                XZBeam block = (XZBeam) state.getBlock();
                XZBeam.EnumCorner corner = state.getValue(XZBeam.CORNER);
                if (block.axis == EnumFacing.Axis.Z) {
                    if (facing == EnumFacing.UP) {
                        switch (corner) {
                            case DC:
                                if (hitX <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                                else
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            case DLC:
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            case DRC:
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            case TDLC:
                                if (hitX > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else
                                    return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            case TDRC:
                                if (hitX <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else
                                    return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            default:
                                return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                        }
                    }
                    else if (facing == EnumFacing.DOWN) {
                        switch (corner) {
                            case UC:
                                if (hitX <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                                else
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            case ULC:
                                if (hitX <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            case URC:
                                if (hitX > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            case TULC:
                                if (hitX > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else
                                    return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            case TURC:
                                if (hitX < 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else
                                    return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            default:
                                return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                        }
                    }
                    else if (facing == EnumFacing.WEST) {
                        switch (corner) {
                            case DRC:
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            case RC:
                                if (hitY <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                                else return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            case TDRC:
                                if (hitY > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            case TURC:
                                if (hitY < 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            default:
                                return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
                        }
                    }
                    else if (facing == EnumFacing.EAST) {
                        switch (corner) {
                            case DLC:
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            case LC:
                                if (hitY <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                                else return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            case TDLC:
                                if (hitY > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            case TULC:
                                if (hitY < 0.5)
                                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            default:
                                return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
                        }
                    }
                    else if (facing == EnumFacing.SOUTH || facing == EnumFacing.NORTH) {
                        return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
                    }
                    else return EnumActionResult.FAIL;
                }
                else if (block.axis == EnumFacing.Axis.X) {
                    if (facing == EnumFacing.UP) {
                        switch (corner) {
                            case DC:
                                if (hitZ <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                                else return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            case DLC:
                                if (hitZ <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            case DRC:
                                if (hitZ > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            case TDLC:
                                if (hitZ > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            case TDRC:
                                if (hitZ <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            default:
                                return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                        }
                    }
                    else if (facing == EnumFacing.DOWN) {
                        switch (corner) {
                            case UC:
                                if (hitZ <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                                else return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            case ULC:
                                if (hitZ <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            case URC:
                                if (hitZ > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            case TULC:
                                if (hitZ > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            case TURC:
                                if (hitZ < 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                            default:
                                return setBlock(world, pos.offset(facing), facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
                        }
                    }
                    else if (facing == EnumFacing.WEST || facing == EnumFacing.EAST) {
                        return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
                    }
                    else if (facing == EnumFacing.SOUTH) {
                        switch (corner) {
                            case DRC:
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            case RC:
                                if (hitY <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                                else return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            case TDRC:
                                if (hitY > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            case TURC:
                                if (hitY < 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            default:
                                return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
                        }
                    }
                    else if (facing == EnumFacing.NORTH) {
                        switch (corner) {
                            case DLC:
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            case LC:
                                if (hitY <= 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                                else return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            case TDLC:
                                if (hitY > 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            case TULC:
                                if (hitY < 0.5)
                                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                                else return EnumActionResult.FAIL;
                            default:
                                return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
                        }
                    }
                    else return EnumActionResult.FAIL;
                }

            }
            else return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            return EnumActionResult.SUCCESS;
        }
        else return EnumActionResult.FAIL;
    }

    public EnumActionResult setBlockOffset(@Nonnull World world, BlockPos pos, EnumFacing facing, EntityPlayer player, ItemStack stack, float hitX, float hitY, float hitZ, IBlockState stateX, IBlockState stateY, IBlockState stateZ) {
        pos = pos.offset(facing);
        Block blockIn = world.getBlockState(pos).getBlock();
        if (blockIn instanceof XZBeam) {
            XZBeam block = (XZBeam) blockIn;
            XZBeam.EnumCorner corner = world.getBlockState(pos).getValue(XZBeam.CORNER);
            if (block.axis == EnumFacing.Axis.Z) {
                if (facing == EnumFacing.UP) {
                    switch (corner) {
                        case DLC:
                        case DRC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                        case ULC:
                            if (hitX <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case URC:
                            if (hitX > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case UC:
                            if (hitX <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else if (hitX > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                        case RC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                        case TULC:
                        case TURC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.DOWN) {
                    switch (corner) {
                        case ULC:
                        case URC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                        case DLC:
                            if (hitX <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case DRC:
                            if (hitX > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case DC:
                            if (hitX <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else if (hitX > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                        case RC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                        case TDLC:
                        case TDRC:
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.WEST) {
                    if (corner == XZBeam.EnumCorner.DLC) {
                        if (hitY <= 0.5)
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                        else return EnumActionResult.FAIL;
                    }
                    else if (corner == XZBeam.EnumCorner.DRC) {
                        return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.URC) //TODO: Hey, I'm here. Dammet Java, dammet Forge, dammet MCP, dammet mojang and dammet Minecraft. Ok, I said everything :/
                    else if (corner == XZBeam.EnumCorner.RC) {
                        if (hitY <= 0.5)
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                        else
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.TDLC) {
                        return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.TULC) {
                        return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                    }
                    else return EnumActionResult.FAIL;
                }
                else if (facing == EnumFacing.EAST) {
                    if (corner == XZBeam.EnumCorner.DLC) {
                        return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.DRC) {
                        if (hitY <= 0.5)
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.LC) {
                        if (hitY <= 0.5)
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                        else
                            return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.TDRC) {
                        return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                    }
                    else if (corner == XZBeam.EnumCorner.TURC) {
                        return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                    }
                    else return EnumActionResult.FAIL;
                }
                else if (facing == EnumFacing.SOUTH) {
                    switch (corner) {
                        case ULC:
                            if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case DLC:
                            if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case DRC:
                            if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else return EnumActionResult.FAIL;
                        case URC:
                            if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else return EnumActionResult.FAIL;
                        case RC:
                            if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else return EnumActionResult.FAIL;
                        case UC:
                            if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else return EnumActionResult.FAIL;
                        case DC:
                            if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else return EnumActionResult.FAIL;
                        case TDLC:
                        case TDRC:
                            if (hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        case TULC:
                        case TURC:
                            if (hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.NORTH) {
                    switch (corner) {
                        case ULC:
                            if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else return EnumActionResult.FAIL;
                        case DLC:
                            if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else return EnumActionResult.FAIL;
                        case DRC:
                            if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case URC:
                            if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else return EnumActionResult.FAIL;
                        case RC:
                            if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else return EnumActionResult.FAIL;
                        case UC:
                            if (hitX > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else if (hitX <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else return EnumActionResult.FAIL;
                        case DC:
                            if (hitX > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else if (hitX <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else return EnumActionResult.FAIL;
                        case TDLC:
                        case TDRC:
                            if (hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        case TULC:
                        case TURC:
                            if (hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else return EnumActionResult.FAIL;
            }
            else if (block.axis == EnumFacing.Axis.X) {
                if (facing == EnumFacing.UP) {
                    switch (corner) {
                        case DLC:
                        case DRC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                        case ULC:
                            if (hitZ <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case URC:
                            if (hitZ > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case UC:
                            if (hitZ <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else if (hitZ > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                        case RC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                        case TULC:
                        case TURC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.DOWN) {
                    switch (corner) {
                        case ULC:
                        case URC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                        case DLC:
                            if (hitZ <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case DRC:
                            if (hitZ > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case DC:
                            if (hitZ <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else if (hitX > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                        case RC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                        case TDLC:
                        case TDRC:
                            return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.WEST) {
                    switch (corner) {
                        case ULC:
                            if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case DLC:
                            if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else return EnumActionResult.FAIL;
                        case DRC:
                            if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else return EnumActionResult.FAIL;
                        case URC:
                            if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else return EnumActionResult.FAIL;
                        case RC:
                            if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else return EnumActionResult.FAIL;
                        case UC:
                            if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else return EnumActionResult.FAIL;
                        case DC:
                            if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else return EnumActionResult.FAIL;
                        case TDLC:
                        case TDRC:
                            if (hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        case TULC:
                        case TURC:
                            if (hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.EAST) {
                    switch (corner) {
                        case ULC:
                            if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else return EnumActionResult.FAIL;
                        case DLC:
                            if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.LC), facing);
                            else if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else return EnumActionResult.FAIL;
                        case DRC:
                            if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DC), facing);
                            else if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case URC:
                            if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.UC), facing);
                            else if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.RC), facing);
                            else return EnumActionResult.FAIL;
                        case LC:
                            if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else return EnumActionResult.FAIL;
                        case RC:
                            if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else return EnumActionResult.FAIL;
                        case UC:
                            if (hitZ > 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TURC), facing);
                            else if (hitZ <= 0.5 && hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TULC), facing);
                            else return EnumActionResult.FAIL;
                        case DC:
                            if (hitZ > 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDRC), facing);
                            else if (hitZ <= 0.5 && hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.TDLC), facing);
                            else return EnumActionResult.FAIL;
                        case TDLC:
                        case TDRC:
                            if (hitY > 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        case TULC:
                        case TURC:
                            if (hitY <= 0.5)
                                return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.FULL), facing);
                            else return EnumActionResult.FAIL;
                        default:
                            return EnumActionResult.FAIL;
                    }
                }
                else if (facing == EnumFacing.SOUTH) {
                    return EnumActionResult.FAIL;
                }
                else if (facing == EnumFacing.NORTH) {
                    return EnumActionResult.FAIL;
                }
                else return EnumActionResult.FAIL;
            }
            else return EnumActionResult.FAIL;
        }
        else if (!(blockIn instanceof YBeam)) return setBlock(world, pos, facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
        else return EnumActionResult.FAIL;
    }

    private EnumActionResult setBlockWithMayPlace(World world, BlockPos pos, IBlockState state, EnumFacing facing) {
        if (mayPlace(world, pos, facing, state)) {
            world.setBlockState(pos, state);
            return EnumActionResult.SUCCESS;
        }
        else return EnumActionResult.FAIL;
    }

    private EnumActionResult setBlock(@Nonnull World world, BlockPos pos, EnumFacing facing, @Nonnull EntityPlayer player, @Nonnull ItemStack stack, IBlockState stateX, IBlockState stateY, IBlockState stateZ, float hitX, float hitY, float hitZ) {
        if (player.canPlayerEdit(pos, facing, stack)) {
            if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
                return setBlockWithMayPlace(world, pos, stateY, facing);
            }
            if (facing == EnumFacing.WEST || facing == EnumFacing.EAST) {
                if (hitZ > 0.5 && hitY > 0.5)
                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.URC), facing);
                else if (hitZ > 0.5 && hitY <= 0.5)
                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DRC), facing);
                else if (hitZ <= 0.5 && hitY > 0.5)
                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.ULC), facing);
                else if (hitZ <= 0.5 && hitY <= 0.5)
                    return setBlockWithMayPlace(world, pos, stateX.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DLC), facing);
                else return EnumActionResult.FAIL;
            }
            if (facing == EnumFacing.SOUTH || facing == EnumFacing.NORTH) {
                if (hitX > 0.5 && hitY > 0.5)
                    return setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.URC), facing);
                else if (hitX > 0.5 && hitY <= 0.5)
                    setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DRC), facing);
                else if (hitX <= 0.5 && hitY > 0.5)
                    setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.ULC), facing);
                else if (hitX <= 0.5 && hitY <= 0.5)
                    setBlockWithMayPlace(world, pos, stateZ.withProperty(XZBeam.CORNER, XZBeam.EnumCorner.DLC), facing);
                else return EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.FAIL;
    }

    public enum BeamType implements IItemEnum {
        ASH("ash", 0, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        BIRCH("birch", 1, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        OAK("oak", 2, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        LINDEN("linden", 3, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        PEAR("pear", 4, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        POPLAR("poplar", 5, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        PINE("pine", 6, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        SPRUCE("spruce", 7, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        LARCH("larch", 8, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ);

        private final String name;
        private final int meta;
        private final Block beamTypeY;
        private final Block beamTypeX;
        private final Block beamTypeZ;

        BeamType(String name, int meta, Block beamTypeY, Block beamTypeX, Block beamTypeZ) {
            this.name = name;
            this.meta = meta;
            this.beamTypeY = beamTypeY;
            this.beamTypeX = beamTypeX;
            this.beamTypeZ = beamTypeZ;
        }

        public Block getBeamBlock(String axis) {
            return axis.equals("x") ? beamTypeX : axis.equals("z") ? beamTypeZ : beamTypeY;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getMetadata() {
            return meta;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}