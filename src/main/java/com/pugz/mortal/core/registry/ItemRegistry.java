package com.pugz.mortal.core.registry;

import com.pugz.mortal.common.item.WispCrystalsItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mortal", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
    public static Item WISP_CRYSTALS = new WispCrystalsItem(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("wisp_crystals");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> registry) {
        registry.getRegistry().registerAll(
                WISP_CRYSTALS
        );
    }
}