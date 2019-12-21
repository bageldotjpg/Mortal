package com.pugz.mortal.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockProperties {
    public static Block.Properties SMOKY_QUARTZ = Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F);
    public static Block.Properties SOUL_GLASS = Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F);
    public static Block.Properties SOUL_URN = Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0F);
}