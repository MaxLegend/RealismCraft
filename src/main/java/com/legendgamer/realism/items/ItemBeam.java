package com.legendgamer.realism.items;

import com.legendgamer.realism.API.metautils.BasicMetadataItem;
import com.legendgamer.realism.API.metautils.IItemEnum;
import com.legendgamer.realism.blocks.tree.CornerContainer.EnumCorner;
import com.legendgamer.realism.blocks.tree.XBeam;
import com.legendgamer.realism.blocks.tree.ZBeam;
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

import static com.legendgamer.realism.blocks.tree.CornerContainer.CORNER;
import static com.legendgamer.realism.blocks.tree.CornerContainer.EnumCorner.*;
import static net.minecraft.util.EnumFacing.*;

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
        if (newState.getBlock() instanceof XBeam) {
            XBeam block = (XBeam) newState.getBlock();
            List<AxisAlignedBB> axisAlignedBB = block.getCollisionBoundingBoxList(newState, world, pos);
            for (AxisAlignedBB aabb : axisAlignedBB) return world.checkNoEntityCollision(aabb.offset(pos));
        }
        else if (newState.getBlock() instanceof ZBeam) {
            ZBeam block = (ZBeam) newState.getBlock();
            List<AxisAlignedBB> axisAlignedBB = block.getCollisionBoundingBoxList(newState, world, pos);
            for (AxisAlignedBB aabb : axisAlignedBB) return world.checkNoEntityCollision(aabb.offset(pos));
        }
        AxisAlignedBB aabb = newState.getBlock().getCollisionBoundingBox(state, world, pos);
        if (aabb != Block.NULL_AABB && !world.checkNoEntityCollision(aabb.offset(pos))) return false;
        return state.getBlock().isReplaceable(world, pos) && newState.getBlock().canPlaceBlockOnSide(world, pos, sidePlacedOn);
    }

    @Override
    public EnumActionResult onItemUse(@Nonnull EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState stateY = getType(stack).beamTypeY.getDefaultState();
        IBlockState stateX = getType(stack).beamTypeX.getDefaultState();
        IBlockState stateZ = getType(stack).beamTypeZ.getDefaultState();
        if (!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack)) {
            if (player.isSneaking()) return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);

            IBlockState state = world.getBlockState(pos);
            boolean flagZ = state.getBlock() instanceof ZBeam;
            float tempHit = flagZ ? hitX : hitZ;
            IBlockState tempState = flagZ ? stateZ : stateX;
            boolean isTargetBlock = flagZ || (state.getBlock() instanceof XBeam);
            if (!isTargetBlock) {
                return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            }

            EnumCorner corner = state.getValue(CORNER);

            if (facing == UP) {
                if (corner == DC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if (corner == DLC) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                else if (corner == DRC) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                else if (corner == TDLC && tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if (corner == TDRC && tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if (corner == DLD && tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                else if (corner == DRD && tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                else return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            }
            else if (facing == DOWN) {
                if (corner == UC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                }
                else if (corner == ULC) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                else if (corner == URC) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                else if (corner == TULC && tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if (corner == TURC && tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if (corner == DLD && tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                else if (corner == DRD && tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                else return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            }
            else if (flagZ ? (facing == NORTH || facing == SOUTH) : (facing == WEST || facing == EAST)) {
                return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            }
            else if (flagZ ? (facing == WEST) : (facing == NORTH)) {
                if (corner == DRC) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                if (corner == URC) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                else if (corner == RC) {
                    if (hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                }
                else if (corner == DRD && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                else if (corner == DLD && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                else if (corner == TDRC && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if (corner == TURC && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            }
            else if (flagZ ? (facing == EAST) : (facing == SOUTH)) {
                if (corner == DLC) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                if (corner == ULC) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                else if (corner == LC) {
                    if (hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                }
                else if (corner == DLD && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                else if (corner == DRD && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                else if (corner == TDLC && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if (corner == TULC && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else return setBlockOffset(world, pos, facing, player, stack, hitX, hitY, hitZ, stateX, stateY, stateZ);
            }
        }
        return EnumActionResult.FAIL;
    }

    public EnumActionResult setBlockOffset(@Nonnull World world, BlockPos pos, EnumFacing facing, EntityPlayer player, ItemStack stack, float hitX, float hitY, float hitZ, IBlockState stateX, IBlockState stateY, IBlockState stateZ) {
        pos = pos.offset(facing);
        IBlockState state = world.getBlockState(pos);
        boolean flagZ = state.getBlock() instanceof ZBeam;
        float tempHit = flagZ ? hitX : hitZ;
        IBlockState tempState = flagZ ? stateZ : stateX;
        boolean isTargetBlock = flagZ || (state.getBlock() instanceof XBeam);
        if (isTargetBlock) {
            EnumCorner corner = world.getBlockState(pos).getValue(CORNER);

            if (facing == UP) {
                if (corner == DLC || corner == DRC) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                else if (corner == ULC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == URC) {
                    if (tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == UC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                }
                else if (corner == LC || corner == DLD) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                else if (corner == RC || corner == DRD) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                else if (corner == TULC || corner == TURC) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
            }
            else if (facing == DOWN) {
                if (corner == ULC || corner == URC) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                else if (corner == DLC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == DRC) {
                    if (tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == DC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if (corner == LC || corner == DRD) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                else if (corner == RC || corner == DLD) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                else if (corner == TDLC || corner == TDRC) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
            }
            else if (flagZ ? (facing == SOUTH) : (facing == WEST)) {
                if (corner == ULC) {
                    if (tempHit > 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                    else if (tempHit <= 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == DLC) {
                    if (tempHit > 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                    else if (tempHit <= 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == DRC) {
                    if (tempHit > 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                    else if (tempHit <= 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == URC) {
                    if (tempHit > 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                    else if (tempHit <= 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == DLD) {
                    if (tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                }
                else if (corner == DRD) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if (corner == LC) {
                    if (hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                }
                else if (corner == RC) {
                    if (hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if (corner == UC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                }
                else if (corner == DC) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if ((corner == TDLC || corner == TDRC) && hitY > 0.5)
                    return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if ((corner == TULC || corner == TURC) && hitY <= 0.5)
                    return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
            }
            else if (flagZ ? (facing == NORTH) : (facing == EAST)) {
                if (corner == ULC) {
                    if (tempHit <= 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                    else if (tempHit > 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == DLC) {
                    if (tempHit <= 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                    else if (tempHit > 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == DRC) {
                    if (tempHit <= 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                    else if (tempHit > 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == URC) {
                    if (tempHit <= 0.5 && hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                    else if (tempHit > 0.5 && hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == DLD) {
                    if (tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                }
                else if (corner == DRD) {
                    if (tempHit <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if (corner == LC) {
                    if (hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                }
                else if (corner == RC) {
                    if (hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                }
                else if (corner == UC) {
                    if (tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                }
                else if (corner == DC) {
                    if (tempHit > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                }
                else if ((corner == TDLC || corner == TDRC) && hitY > 0.5)
                    return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
                else if ((corner == TULC || corner == TURC) && hitY <= 0.5)
                    return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
            }
            else if (flagZ ? (facing == EAST) : (facing == SOUTH)) {
                if (corner == DLC || corner == ULC) return setBlock(world, pos, tempState.withProperty(CORNER, LC), facing);
                else if (corner == DC || corner == DLD) return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                else if (corner == UC || corner == DRD) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                else if (corner == URC) {
                    if (hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == DRC) {
                    if (hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == RC) {
                    if (hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                }
                else if (corner == TDRC || corner == TURC) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
            }
            else if (flagZ ? (facing == WEST) : (facing == NORTH)) {
                if (corner == DRC || corner == URC) return setBlock(world, pos, tempState.withProperty(CORNER, RC), facing);
                else if (corner == DC || corner == DRD) return setBlock(world, pos, tempState.withProperty(CORNER, TDRC), facing);
                else if (corner == UC || corner == DLD) return setBlock(world, pos, tempState.withProperty(CORNER, TURC), facing);
                else if (corner == ULC) {
                    if (hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, UC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DLD), facing);
                }
                else if (corner == DLC) {
                    if (hitY <= 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, DC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, DRD), facing);
                }
                else if (corner == LC) {
                    if (hitY > 0.5) return setBlock(world, pos, tempState.withProperty(CORNER, TULC), facing);
                    else return setBlock(world, pos, tempState.withProperty(CORNER, TDLC), facing);
                }
                else if (corner == TDLC || corner == TULC) return setBlock(world, pos, tempState.withProperty(CORNER, FULL), facing);
            }

            return EnumActionResult.FAIL;
        }
        return setBlockStart(world, pos, facing, player, stack, stateX, stateY, stateZ, hitX, hitY, hitZ);
    }

    private EnumActionResult setBlock(World world, BlockPos pos, IBlockState state, EnumFacing facing) {
        if (mayPlace(world, pos, facing, state)) {
            world.setBlockState(pos, state);
            return EnumActionResult.SUCCESS;
        }
        else return EnumActionResult.FAIL;
    }

    private EnumActionResult setBlockStart(@Nonnull World world, BlockPos pos, EnumFacing facing, @Nonnull EntityPlayer player, @Nonnull ItemStack stack, IBlockState stateX, IBlockState stateY, IBlockState stateZ, float hitX, float hitY, float hitZ) {
        if (player.canPlayerEdit(pos, facing, stack)) {
            if (facing == DOWN || facing == UP) {
                return setBlock(world, pos, stateY, facing);
            }
            if (facing == WEST || facing == EAST) {
                if (hitZ > 0.5 && hitY > 0.5)
                    return setBlock(world, pos, stateX.withProperty(CORNER, URC), facing);
                else if (hitZ > 0.5 && hitY <= 0.5)
                    return setBlock(world, pos, stateX.withProperty(CORNER, DRC), facing);
                else if (hitZ <= 0.5 && hitY > 0.5)
                    return setBlock(world, pos, stateX.withProperty(CORNER, ULC), facing);
                else if (hitZ <= 0.5 && hitY <= 0.5)
                    return setBlock(world, pos, stateX.withProperty(CORNER, DLC), facing);
                else return EnumActionResult.FAIL;
            }
            if (facing == SOUTH || facing == NORTH) {
                if (hitX > 0.5 && hitY > 0.5)
                    return setBlock(world, pos, stateZ.withProperty(CORNER, URC), facing);
                else if (hitX > 0.5 && hitY <= 0.5)
                    setBlock(world, pos, stateZ.withProperty(CORNER, DRC), facing);
                else if (hitX <= 0.5 && hitY > 0.5)
                    setBlock(world, pos, stateZ.withProperty(CORNER, ULC), facing);
                else if (hitX <= 0.5 && hitY <= 0.5)
                    setBlock(world, pos, stateZ.withProperty(CORNER, DLC), facing);
                else return EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.FAIL;
    }

    public enum BeamType implements IItemEnum {
        ASH("ash", 0, BlocksList.ASH_LOG_TY, BlocksList.ASH_LOG_TX, BlocksList.ASH_LOG_TZ),
        BIRCH("birch", 1, BlocksList.BIRCH_LOG_TY, BlocksList.BIRCH_LOG_TX, BlocksList.BIRCH_LOG_TZ),
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

        public Block getBeamBlock(@Nonnull String axis) {
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