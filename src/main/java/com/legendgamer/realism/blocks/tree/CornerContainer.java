package com.legendgamer.realism.blocks.tree;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public final class CornerContainer {
    public static final PropertyEnum<EnumCorner> CORNER = PropertyEnum.create("corner", EnumCorner.class);

    public enum EnumCorner implements IStringSerializable {
        DLC("dlc"), //Down left corner
        DRC("drc"), //Down right corner
        ULC("ulc"), //Up left corner
        URC("urc"), //Up right corner
        LC("lc"), //Double left corner
        RC("rc"), //Double right corner
        UC("uc"), //Double up corner
        DC("dc"), //Double down corner
        DLD("dld"), // Double left diagonal
        DRD("drd"), // Double right diagonal
        TULC("tulc"), //Trouble up left corner
        TURC("turc"), //Trouble up right corner
        TDLC("tdlc"), //Trouble down left corner
        TDRC("tdrc"), //Trouble down right corner
        FULL("full"); //Full

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
    }
}
