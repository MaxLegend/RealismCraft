package com.legendgamer.realism.blocks.tree.frame;


import com.legendgamer.realism.API.BasicBlock.BasicBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class BlockThickBranch extends BasicBlock {

    /*
     * Чтобы каждый раз не создавть - объявляем нашу мапу где первый аргумент типа (Integer - номер метадаты),
     * а второй аргумент типа (Pair<Integer, EnumAxis> - пара значений {STAGE; AXIS}
     */
    private final Map<Integer, Pair<Integer, EnumAxis>> metaToState = new HashMap<>();

    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 6);
    public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);

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

        setDefaultState(blockState.getBaseState().withProperty(STAGE, 0).withProperty(AXIS, EnumAxis.X));
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


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{STAGE, AXIS});
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
