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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoubleThinLog extends BasicMetadataBlock {

	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);
	public static final PropertyEnum<EnumHalf> HALF = PropertyEnum.<EnumHalf>create("half", EnumHalf.class);

	public BlockDoubleThinLog(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	}

	@Override
	protected IProperty<?>[] createBlockProperties() {
		return new IProperty<?>[] {AXIS, HALF};
	}

	@Override
	protected IBlockState createDefaultState() {
		return getDefaultState().withProperty(AXIS, EnumAxis.X).withProperty(HALF, EnumHalf.BOTTOM);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(hitY <= 0.5F) return getDefaultState().withProperty(HALF, EnumHalf.BOTTOM);
		else if(hitY >= 0.5F) return getDefaultState().withProperty(HALF, EnumHalf.TOP);
		return getDefaultState();
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

	public enum EnumAxis implements IStringSerializable {
		X("x"),
		Z("z");

		private final String name;

		EnumAxis(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

	}

	public enum EnumHalf implements IStringSerializable {
		TOP("top"),
		BOTTOM("bottom");

		private final String name;

		EnumHalf(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}
	}
}