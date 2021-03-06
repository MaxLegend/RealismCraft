package com.legendgamer.realism.blocks.tree;

import com.legendgamer.realism.API.metautils.BasicMetadataBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockThinLog extends BasicMetadataBlock {

    public static final PropertyEnum<EnumCorner> CORNER = PropertyEnum.<EnumCorner>create("corner", EnumCorner.class);

    public BlockThinLog(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
        super(materialIn, name, hardness, resistanse, soundtype, tab);
    }

    @Override
    protected IProperty<?>[] createBlockProperties() {
        return new IProperty<?>[]{CORNER};
    }

    @Override
    protected IBlockState createDefaultState() {
        return getDefaultState().withProperty(CORNER, EnumCorner.XLLC);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    //	@Override
//	@SideOnly(Side.CLIENT)
//	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
//		return false;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public boolean isFullCube(IBlockState state) {
//		return false;
//	}
//
//    public boolean onBlockActivated(World w, BlockPos pos, IBlockState s, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)  {
//		if(s.getValue(CORNER) == EnumCorner.XLLC  ||s.getValue(CORNER) == EnumCorner.XLRC) {
//			w.setBlockState(pos, BlocksList.ASH_LOG_TD.getDefaultState().withProperty(BlockDoubleThinLog.HALF, BlockDoubleThinLog.EnumHalf.BOTTOM).withProperty(BlockDoubleThinLog.AXIS, BlockDoubleThinLog.EnumAxis.X));
//			return true;
//		} else  if(s.getValue(CORNER) == EnumCorner.XULC  ||s.getValue(CORNER) == EnumCorner.XURC) {
//			w.setBlockState(pos, BlocksList.ASH_LOG_TD.getDefaultState().withProperty(BlockDoubleThinLog.HALF, BlockDoubleThinLog.EnumHalf.TOP).withProperty(BlockDoubleThinLog.AXIS, BlockDoubleThinLog.EnumAxis.X));
//			return true;
//		} if(s.getValue(CORNER) == EnumCorner.ZLLC  ||s.getValue(CORNER) == EnumCorner.ZLRC) {
//			w.setBlockState(pos, BlocksList.ASH_LOG_TD.getDefaultState().withProperty(BlockDoubleThinLog.HALF, BlockDoubleThinLog.EnumHalf.BOTTOM).withProperty(BlockDoubleThinLog.AXIS, BlockDoubleThinLog.EnumAxis.Z));
//			return true;
//		} else  if(s.getValue(CORNER) == EnumCorner.ZULC  ||s.getValue(CORNER) == EnumCorner.ZURC) {
//			w.setBlockState(pos, BlocksList.ASH_LOG_TD.getDefaultState().withProperty(BlockDoubleThinLog.HALF, BlockDoubleThinLog.EnumHalf.TOP).withProperty(BlockDoubleThinLog.AXIS, BlockDoubleThinLog.EnumAxis.Z));
//			return true;
//		}
//		else {
//			w.setBlockState(pos.offset(facing), s);
//			return false;
//		}
//    }
    @Override
    public boolean canPlaceBlockAt(World w, BlockPos pos) {
        IBlockState s = w.getBlockState(pos);
//		  if(s.getBlock() instanceof BlockThinLog) {
//			  
//			  if(s.getValue(CORNER) == EnumCorner.XLLC  ||s.getValue(CORNER) == EnumCorner.XLRC) {
//				  
//			  } else  if(s.getValue(CORNER) == EnumCorner.XULC  ||s.getValue(CORNER) == EnumCorner.XURC) {
//				  
//			  }
//		  } 
        return s.getBlock().isReplaceable(w, pos);
    }

    @Override
    public IBlockState getStateForPlacement(World w, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState def = super.getStateForPlacement(w, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(CORNER, EnumCorner.XLRC);
        if (facing.getAxis() == EnumFacing.Axis.X) {
            if (hitY <= 0.5F && hitZ <= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.XLLC);
            }
            else if (hitY <= 0.5F && hitZ >= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.XLRC);
            }
            else if (hitY > 0.5F && hitZ <= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.XULC);
            }
            else if (hitY > 0.5F && hitZ >= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.XURC);
            }
        }
        if (facing.getAxis() == EnumFacing.Axis.Z) {
            if (hitY <= 0.5F && hitX <= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.ZLLC);
            }
            if (hitY <= 0.5F && hitX >= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.ZLRC);
            }
            if (hitY >= 0.5F && hitX <= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.ZULC);
            }
            if (hitY >= 0.5F && hitX >= 0.5F) {
                return getDefaultState().withProperty(CORNER, EnumCorner.ZURC);
            }
        }
        else if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
            return getDefaultState().withProperty(CORNER, EnumCorner.Y);
        }
        return def;
    }

    public enum EnumCorner implements IStringSerializable {
        XLLC("xllc"),
        XLRC("xlrc"),
        XULC("xulc"),
        XURC("xurc"),
        Y("y"),
        ZLLC("zllc"),
        ZLRC("zlrc"),
        ZULC("zulc"),
        ZURC("zurc");

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

        public boolean isLower(EnumCorner e) {
            return e == XLLC || e == XLRC;
        }

        public boolean isUpper(EnumCorner e) {
            return e == XULC || e == XURC;
        }
    }
}