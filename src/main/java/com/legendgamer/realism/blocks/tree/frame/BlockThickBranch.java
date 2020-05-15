package com.legendgamer.realism.blocks.tree.frame;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.tuple.Pair;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.capability.world_cap.DateProvider;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockThickBranch extends BasicBlock  {

	/*
	 * Чтобы каждый раз не создавть - объявляем нашу мапу где первый аргумент типа (Integer - номер метадаты),
	 * а второй аргумент типа (Pair<Integer, EnumAxis> - пара значений {STAGE; AXIS}
	 */
	private final Map<Integer, Pair<Integer, EnumAxis>> metaToState = new HashMap<>();

	public static final PropertyBool IS_HORIZONTAL = PropertyBool.create("is_h");
	public static final PropertyBool IS_VERTICAL = PropertyBool.create("is_v");

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
	public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);
	protected static final AxisAlignedBB[] AABB_STAGER_X = new AxisAlignedBB[] {
			new AxisAlignedBB(0D, 0.377D, 0.377D, 1D, 0.623D, 0.623D),
			new AxisAlignedBB(0D, 0.315D, 0.315D, 1D, 0.685D, 0.685D),
			new AxisAlignedBB(0D, 0.25D, 0.25D, 1D, 0.75D, 0.75D),
			new AxisAlignedBB(0D, 0.19D, 0.19D, 1D, 0.81D, 0.81D),
			new AxisAlignedBB(0D, 0.127D, 0.127D, 1D, 0.873D, 0.873D),
			new AxisAlignedBB(0D, 0.065D, 0.065D, 1D, 0.935D, 0.935D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};
	protected static final AxisAlignedBB[] AABB_STAGER_Z = new AxisAlignedBB[] {
			new AxisAlignedBB(0.377D, 0.377D, 0D, 0.623D,  0.623D, 1D),
			new AxisAlignedBB(0.315D, 0.315D, 0D, 0.685D, 0.685D, 1D),
			new AxisAlignedBB(0.25D, 0.25D, 0D, 0.75D, 0.75D, 1D),
			new AxisAlignedBB(0.19D, 0.19D, 0D, 0.81D, 0.81D, 1D),
			new AxisAlignedBB(0.127D, 0.127D, 0D, 0.873D, 0.873D, 1D),
			new AxisAlignedBB(0.065D, 0.065D, 0D, 0.935D, 0.935D, 1D),
			new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D),
	};

	protected Block leaves;
	
	protected Block tn;
	protected int tickUpdate;
	
	public BlockThickBranch(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype, CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
	
		/*
		 * Инициализируем нашу мапу. i - значение поворота 0 - 1
		 */
		for (int i = 0; i < EnumAxis.values().length; i++) {
			//Текущее значение поворота
			EnumAxis axis = EnumAxis.values()[i];
			// j - значение стадии роста
			for (int j = 0; j < 6; j++) {
				metaToState.put(
						/* Это индекс или проще говоря наша метадата. Если вкратце -
						 * для каждого поворота мы берём все стадии роста по очереди.
						 * Чтобы лучше понять, посчитай для каждой метадаты от нуля*/
						(i * 6) + j,
						/* Эта пара значений {STAGE; AXIS} - собственно уникальная комбинация значений
						 * для каждой из 12 метадат (0 - 11)*/
						Pair.of(j, axis));
			}
		}

		setDefaultState(blockState.getBaseState().withProperty(STAGE, 0).withProperty(AXIS, EnumAxis.X).withProperty(IS_HORIZONTAL, false).withProperty(IS_VERTICAL, false));
	}
	public Block getTB() {
		return tn;
	}
	public Block getTN() {
		return tn;
	}
	private boolean canConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		IBlockState state1 = world.getBlockState(pos);
		IBlockState state = world.getBlockState(pos.offset(facing));

		Block connector_branch = world.getBlockState(pos.offset(facing)).getBlock();
		if(state1.getBlock() instanceof BlockThickBranch && state.getBlock() instanceof BlockThickBranch) {
			if(state1.getValue(AXIS) != state.getValue(AXIS) && connector_branch == getTB() ) {
				return true;
			}
		} 

		if( connector_branch == getTN() || connector_branch == getLeaves()) {
			return true;
		} else return false;
	}
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{

		boolean isUp = canConnectTo(worldIn, pos, EnumFacing.UP);
		boolean isDown =  canConnectTo(worldIn, pos, EnumFacing.DOWN);
		boolean isEast = canConnectTo(worldIn, pos, EnumFacing.EAST);
		boolean isWest = canConnectTo(worldIn, pos, EnumFacing.WEST);	

		boolean isSouth = canConnectTo(worldIn, pos, EnumFacing.SOUTH);
		boolean isNorth = canConnectTo(worldIn, pos, EnumFacing.NORTH);		

		boolean isZHConnect = isEast || isWest;
		boolean isXHConnect = isNorth || isSouth;

		boolean isVConnect = isUp || isDown;
		if(state.getValue(AXIS) == EnumAxis.Z) {
			return	state.withProperty(IS_HORIZONTAL, isZHConnect).withProperty(IS_VERTICAL, isVConnect);
		}
		if(state.getValue(AXIS) == EnumAxis.X) {
			return state.withProperty(IS_HORIZONTAL, isXHConnect).withProperty(IS_VERTICAL, isVConnect);
		}
		return state.withProperty(IS_HORIZONTAL, isXHConnect).withProperty(IS_VERTICAL, isVConnect);


		//		
		//		boolean isEast = canConnectTo(worldIn, pos, EnumFacing.EAST);
		//		boolean isWest =  canConnectTo(worldIn, pos, EnumFacing.WEST);
		//		
		//		boolean isXConnect = isEast || isWest;
		//		boolean isSouth = canConnectTo(worldIn, pos, EnumFacing.SOUTH);
		//		boolean isNorth = canConnectTo(worldIn, pos, EnumFacing.NORTH);
		//		boolean isZConnect = isNorth || isSouth;
		//		
		//		return state.withProperty(IS_HORIZONTAL, isHConnect).withProperty(IS_BRANCH_Z, isZConnect);
	}
	public IBlockState getStateFromMeta(int meta) {
		/* Так как наша карта (HashMap) имеет вид <ИМЯ-КЛЮЧ, ОБЪЕКТ-ЗНАЧЕНИЕ>,
		 * мы обратимся к объекту указав в качестве ключа метадату и получим пару значений {STAGE; AXIS}
		 * уникальных для этой метадаты
		 */
		Pair<Integer, EnumAxis> nowPair = metaToState.get(meta);
		//Пара будет равняться null если для метадаты не существует стэйта, то-есть если метадата больше 11
		if (nowPair != null) {
			return getDefaultState().withProperty(STAGE, nowPair.getLeft()).withProperty(AXIS, nowPair.getRight());
		} else return getDefaultState();
	}

	public int getMetaFromState(IBlockState state) {
		/* Это не магические числа, мы просто выполняем
		 * операцию которую производили в конструкторе (i * 6) + j
		 * Грубо говоря, мы просто вычисляем метадату, так же как придумали раньше
		 */
		return (state.getValue(AXIS).ordinal() * 6) + state.getValue(STAGE);
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (state.getValue(STAGE) < 6) world.setBlockState(pos, state.withProperty(STAGE, state.getValue(STAGE) + 1));
		return true;
	}
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if(state.getValue(AXIS) == EnumAxis.X) {
			switch(state.getValue(STAGE)) {
			case 0:	return AABB_STAGER_X[0];
			case 1:	return AABB_STAGER_X[1];
			case 2:	return AABB_STAGER_X[2];
			case 3:	return AABB_STAGER_X[3];
			case 4:	return AABB_STAGER_X[4];
			case 5:	return AABB_STAGER_X[5];
			case 6:	return AABB_STAGER_X[6];
			}
		}
		if(state.getValue(AXIS) == EnumAxis.Z) {
			switch(state.getValue(STAGE)) {
			case 0:	return AABB_STAGER_Z[0];
			case 1:	return AABB_STAGER_Z[1];
			case 2:	return AABB_STAGER_Z[2];
			case 3:	return AABB_STAGER_Z[3];
			case 4:	return AABB_STAGER_Z[4];
			case 5:	return AABB_STAGER_Z[5];
			case 6:	return AABB_STAGER_Z[6];
			}
		}
		return new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D);
	}
	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		IBlockState state = world.getBlockState(pos);
		for (net.minecraft.block.properties.IProperty<?> prop : state.getProperties().keySet()) {
			if (prop.getName().equals("axis")) {
				world.setBlockState(pos, state.cycleProperty(prop));
				return true;
			}
		}
		return false;
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		return getStateFromMeta(meta).withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:

			switch ((EnumAxis) state.getValue(AXIS)) {
			case X:
				return state.withProperty(AXIS, EnumAxis.Z);
			case Z:
				return state.withProperty(AXIS, EnumAxis.X);
			default:
				return state;
			}

		default:
			return state;
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		if (state.getValue(STAGE) == 6) {
			return true;
		}
		return false;
	}
	public int getFrequencyTick() {
		return tickUpdate;
	}
	@Override
	public void randomTick(World w, BlockPos pos, IBlockState state, Random r)
	{
		IDate date = w.getCapability(DateProvider.DATE, null);
		if(w.getWorldTime() % getFrequencyTick() == 0) {
			this.lifeActivity(w, pos, state, date, ThreadLocalRandom.current());
		}
	}
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}
	public void neighborChanged(IBlockState state, World w, BlockPos pos, Block blockIn, BlockPos fromPos)
	{

	}
	public void lifeActivity(World w, BlockPos p, IBlockState s, IDate date, ThreadLocalRandom tr) {
	}
	public void onBlockDestroyedByPlayer(World w, BlockPos pos, IBlockState state)
	{
		//	state.getBlock().breakBlock(w,  pos,  state);
	}
	public void breakBlock(World w, BlockPos pos, IBlockState state)
	{
		boolean isLeaves = true;
		for(EnumFacing f : EnumFacing.VALUES) {

			if(w.getBlockState(pos.offset(f)).getBlock() instanceof BlockRealLeaves) {
				isLeaves = false;
				w.destroyBlock(pos.offset(f), isLeaves);
			}
			if(w.getBlockState(pos.offset(f)).getBlock() == state.getBlock() ) {


				w.destroyBlock(pos.offset(f), isLeaves);
			}
		}
	}
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if(world.getBlockState(pos).getBlock() != BlocksList.REAL_TB_PINE) {
			for(EnumFacing f : EnumFacing.VALUES) {
				if(world.isAirBlock(pos.offset(f))) {

					world.setBlockState(pos.offset(f), getLeaves().getDefaultState());
				}
			}
		}
	}
	public Block getLeaves() {
		return leaves;
	}
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{STAGE, AXIS, IS_HORIZONTAL, IS_VERTICAL});
	}

	public static enum EnumAxis implements IStringSerializable {
		X("x"),
		Z("z");

		private final String name;

		private EnumAxis(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		public static EnumAxis fromFacingAxis(Axis axis) {
			switch (axis) {
			case X:
				return X;
			case Z:
				return Z;
			default:
				return X;
			}
		}

		public String getName() {
			return name;
		}

	}


}

