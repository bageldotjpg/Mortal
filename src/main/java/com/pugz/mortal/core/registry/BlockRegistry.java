package com.pugz.mortal.core.registry;

import com.pugz.mortal.common.block.*;
import com.pugz.mortal.core.util.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mortal", bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {
    public static Block SOUL_URN = new SoulUrnBlock(BlockProperties.SOUL_URN).setRegistryName("soul_urn");
    public static Block SOUL_GLASS = new SoulGlassBlock(BlockProperties.SOUL_GLASS).setRegistryName("soul_glass");

    public static Block SMOKY_QUARTZ = new SmokyQuartzBlock(BlockProperties.SMOKY_QUARTZ).setRegistryName("smoky_quartz");
    public static Block SMOKY_QUARTZ_STAIRS = new SmokyQuartzStairsBlock(SMOKY_QUARTZ.getDefaultState(), BlockProperties.SMOKY_QUARTZ).setRegistryName("smoky_quartz_stairs");
    public static Block SMOKY_QUARTZ_SLAB = new SmokyQuartzSlabBlock(BlockProperties.SMOKY_QUARTZ).setRegistryName("smoky_quartz_slab");
    public static Block SMOKY_QUARTZ_WALL = new SmokyQuartzWallBlock(BlockProperties.SMOKY_QUARTZ).setRegistryName("smoky_quartz_wall");
    public static Block SMOKY_QUARTZ_PILLAR = new RotatedSmokyQuartzPillarBlock(BlockProperties.SMOKY_QUARTZ).setRegistryName("smoky_quartz_pillar");
    public static Block CHISELED_SMOKY_QUARTZ = new SmokyQuartzBlock(BlockProperties.SMOKY_QUARTZ).setRegistryName("chiseled_smoky_quartz");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registry) {
        registry.getRegistry().registerAll(
                SOUL_URN,
                SOUL_GLASS,
                SMOKY_QUARTZ, SMOKY_QUARTZ_STAIRS, SMOKY_QUARTZ_SLAB, SMOKY_QUARTZ_WALL, SMOKY_QUARTZ_PILLAR, CHISELED_SMOKY_QUARTZ
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> registry) {
        Item.Properties decorations = new Item.Properties().group(ItemGroup.DECORATIONS);
        Item.Properties buildingBlocks = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
        registry.getRegistry().registerAll(
                new BlockItem(SOUL_URN, decorations).setRegistryName(SOUL_URN.getRegistryName()),
                new BlockItem(SOUL_GLASS, decorations).setRegistryName(SOUL_GLASS.getRegistryName()),
                new BlockItem(SMOKY_QUARTZ, buildingBlocks).setRegistryName(SMOKY_QUARTZ.getRegistryName()),
                new BlockItem(SMOKY_QUARTZ_STAIRS, buildingBlocks).setRegistryName(SMOKY_QUARTZ_STAIRS.getRegistryName()),
                new BlockItem(SMOKY_QUARTZ_SLAB, buildingBlocks).setRegistryName(SMOKY_QUARTZ_SLAB.getRegistryName()),
                new BlockItem(SMOKY_QUARTZ_WALL, buildingBlocks).setRegistryName(SMOKY_QUARTZ_WALL.getRegistryName()),
                new BlockItem(SMOKY_QUARTZ_PILLAR, buildingBlocks).setRegistryName(SMOKY_QUARTZ_PILLAR.getRegistryName()),
                new BlockItem(CHISELED_SMOKY_QUARTZ, buildingBlocks).setRegistryName(CHISELED_SMOKY_QUARTZ.getRegistryName())
        );
    }
}