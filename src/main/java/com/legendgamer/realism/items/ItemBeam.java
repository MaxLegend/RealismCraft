package com.legendgamer.realism.items;

import com.legendgamer.realism.API.metautils.BasicMetadataItem;
import com.legendgamer.realism.API.metautils.IItemEnum;
import com.legendgamer.realism.blocks.tree.XZBeam;
import com.legendgamer.realism.reg.BlocksList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBeam extends BasicMetadataItem<ItemBeam.BeamType> {

    public ItemBeam(String name, int maxStackSize, CreativeTabs tab) {
        super(name, maxStackSize, tab);
    }

    @Override
    public Class<BeamType> setEnum() {
        return BeamType.class;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState stateY = getBeamType(player.getActiveItemStack()).beamTypeY.getDefaultState();
        IBlockState stateX = getBeamType(player.getActiveItemStack()).beamTypeX.getDefaultState();
        IBlockState stateZ = getBeamType(player.getActiveItemStack()).beamTypeZ.getDefaultState();
        if (!(world.getBlockState(pos).getBlock() instanceof XZBeam)) {
            BlockPos placePos = pos.offset(facing);
            if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) world.setBlockState(placePos, stateY);
            else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) world.setBlockState(placePos, stateX);
            else world.setBlockState(placePos, stateZ);
        } else {
//            System.out.println("shfsdfhgf");
        }
        return EnumActionResult.PASS;
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