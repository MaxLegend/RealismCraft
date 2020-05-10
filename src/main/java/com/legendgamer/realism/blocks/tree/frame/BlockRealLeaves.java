package com.legendgamer.realism.blocks.tree.frame;

import java.util.Random;

import javax.annotation.Nullable;

import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import com.legendgamer.realism.blocks.tree.util.EnumTreeType;
import com.legendgamer.realism.blocks.tree.util.ITreeType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRealLeaves extends BasicBlock implements ITreeType{
	public static final PropertyBool DEFAULT = PropertyBool.create("default");
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
    
	private EnumTreeType type;
	private static Block fruitage;
	public BlockRealLeaves(Material materialIn, String name, float hardness, float resistanse, SoundType soundtype,
			CreativeTabs tab) {
		super(materialIn, name, hardness, resistanse, soundtype, tab);
		setTickRandomly(true);
	
		this.setDefaultState(this.blockState.getBaseState().withProperty(DEFAULT, true));
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	public Block setType(EnumTreeType type) {
		this.type = type;
		return this;
	}
	int[] surroundings;

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		boolean flag0 =  canLogConnectTo(worldIn, pos, EnumFacing.DOWN);
		boolean flag4 =  canLogConnectTo(worldIn, pos, EnumFacing.UP);
		boolean flag =  canLogConnectTo(worldIn, pos, EnumFacing.NORTH);
		boolean flag1 = canLogConnectTo(worldIn, pos, EnumFacing.EAST);
		boolean flag2 = canLogConnectTo(worldIn, pos, EnumFacing.SOUTH);
		boolean flag3 = canLogConnectTo(worldIn, pos, EnumFacing.WEST);
		return state
				.withProperty(DEFAULT, Boolean.valueOf(true))//!flag4 && !flag0 && !flag && !flag1 && !flag2 && !flag3))
				.withProperty(UP, Boolean.valueOf(flag4))
				.withProperty(NORTH, Boolean.valueOf(flag))
				.withProperty(EAST, Boolean.valueOf(flag1))
				.withProperty(SOUTH, Boolean.valueOf(flag2))
				.withProperty(WEST, Boolean.valueOf(flag3))
				.withProperty(DOWN, Boolean.valueOf(flag0));
	}
	//		@Override
	//		public void randomTick(World world, BlockPos pos, IBlockState state, Random random)
	//		{
	//			//листва пропадает зимой
	//			IDate date = world.getCapability(DateProvider.DATE, null);
	//			if(date.getMonth() == 11|| date.getMonth() == 0||date.getMonth() == 1 ) {
	//		//		world.setBlockToAir(pos);
	//			}
	//		}

	 public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;

	        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
	        {
	            i |= 4;
	        }

	        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
	        {
	            i |= 8;
	        }

	        return i;
	    }
	  public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	    }
	 public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	    {
	        if (!worldIn.isRemote)
	        {
	            if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean)state.getValue(DECAYABLE)).booleanValue())
	            {
	                int i = 4;
	                int j = 5;
	                int k = pos.getX();
	                int l = pos.getY();
	                int i1 = pos.getZ();
	                int j1 = 32;
	                int k1 = 1024;
	                int l1 = 16;

	                if (this.surroundings == null)
	                {
	                    this.surroundings = new int[32768];
	                }

	                if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent decaying leaves from updating neighbors and loading unloaded chunks
	                if (worldIn.isAreaLoaded(pos, 6)) // Forge: extend range from 5 to 6 to account for neighbor checks in world.markAndNotifyBlock -> world.updateObservingBlocksAt
	                {
	                    BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

	                    for (int i2 = -4; i2 <= 4; ++i2)
	                    {
	                        for (int j2 = -4; j2 <= 4; ++j2)
	                        {
	                            for (int k2 = -4; k2 <= 4; ++k2)
	                            {
	                                IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2));
	                                Block block = iblockstate.getBlock();

	                                if (!block.canSustainLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2)))
	                                {
	                                    if (block.isLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2)))
	                                    {
	                                        this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -2;
	                                    }
	                                    else
	                                    {
	                                        this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -1;
	                                    }
	                                }
	                                else
	                                {
	                                    this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = 0;
	                                }
	                            }
	                        }
	                    }

	                    for (int i3 = 1; i3 <= 4; ++i3)
	                    {
	                        for (int j3 = -4; j3 <= 4; ++j3)
	                        {
	                            for (int k3 = -4; k3 <= 4; ++k3)
	                            {
	                                for (int l3 = -4; l3 <= 4; ++l3)
	                                {
	                                    if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16] == i3 - 1)
	                                    {
	                                        if (this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2)
	                                        {
	                                            this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
	                                        }

	                                        if (this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2)
	                                        {
	                                            this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
	                                        }

	                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] == -2)
	                                        {
	                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] = i3;
	                                        }

	                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] == -2)
	                                        {
	                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] = i3;
	                                        }

	                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] == -2)
	                                        {
	                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] = i3;
	                                        }

	                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] == -2)
	                                        {
	                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] = i3;
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }

	                int l2 = this.surroundings[16912];

	                if (l2 >= 0)
	                {
	                    worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
	                }
	                else
	                {
	                    this.destroy(worldIn, pos);
	                }
	            }
	        }
	    }



	  public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        int i = 1;
	        int j = 2;
	        int k = pos.getX();
	        int l = pos.getY();
	        int i1 = pos.getZ();

	        if (worldIn.isAreaLoaded(new BlockPos(k - 2, l - 2, i1 - 2), new BlockPos(k + 2, l + 2, i1 + 2)))
	        {
	            for (int j1 = -1; j1 <= 1; ++j1)
	            {
	                for (int k1 = -1; k1 <= 1; ++k1)
	                {
	                    for (int l1 = -1; l1 <= 1; ++l1)
	                    {
	                        BlockPos blockpos = pos.add(j1, k1, l1);
	                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

	                        if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
	                        {
	                            iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
	                        }
	                    }
	                }
	            }
	        }
	    }
	    private void destroy(World worldIn, BlockPos pos)
	    {
	    //   this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
	        worldIn.setBlockToAir(pos);
	    }
	    @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	        if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isTopSolid() && rand.nextInt(15) == 1)
	        {
	            double d0 = (double)((float)pos.getX() + rand.nextFloat());
	            double d1 = (double)pos.getY() - 0.05D;
	            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
	            worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        }
	    }
	    @Override
	    public void beginLeavesDecay(IBlockState state, World world, BlockPos pos)
	    {
	        if (!(Boolean)state.getValue(CHECK_DECAY))
	        {
	            world.setBlockState(pos, state.withProperty(CHECK_DECAY, true), 4);
	        }
	    }
		

	private boolean canLogConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		Block connector = world.getBlockState(pos.offset(facing)).getBlock();
		IBlockState state_connector = world.getBlockState(pos.offset(facing));
		if(connector instanceof BlockTreeNewBranch || connector instanceof BlockThickBranch || connector instanceof BlockRealTrees) {
			return true;
		} else return false;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	} 	

	public Block setNewState() {
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {DEFAULT, DOWN, UP, NORTH, EAST, WEST, SOUTH, CHECK_DECAY, DECAYABLE});
	}
	public Block getFruitage() {
		return this.fruitage;
	}
	public void setFruitage(Block fruitage) {
		this.fruitage = fruitage;
	}
	@Override
	public EnumTreeType getType() {
		return type;
	}
}
