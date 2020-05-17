package com.legendgamer.realism.items;

import com.legendgamer.realism.API.metautils.BasicMetadataItem;
import com.legendgamer.realism.API.metautils.IItemEnum;
import com.legendgamer.realism.blocks.tree.XZBeam;
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
        if (!(world.getBlockState(pos).getBlock() instanceof XZBeam)) {
//            IBlockState state = BlockList.
        }
        else {

        }
        return EnumActionResult.PASS;
    }

    public enum BeamType implements IItemEnum {
        ASH("ash", 0),
        BIRCH("birch", 1),
        OAK("oak", 2),
        LINDEN("linden", 3),
        PEAR("pear", 4),
        POPLAR("poplar", 5),
        PINE("pine", 6),
        SPRUCE("spruce", 7),
        LARCH("larch", 8);

        private final String name;
        private final int meta;

        BeamType(String name, int meta) {
            this.name = name;
            this.meta = meta;
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