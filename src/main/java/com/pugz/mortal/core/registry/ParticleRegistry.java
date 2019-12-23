package com.pugz.mortal.core.registry;

import com.pugz.mortal.client.particle.SoulWispParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mortal", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {
    public static BasicParticleType SOUL_WISP = new BasicParticleType(false);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<ParticleType<?>> registry) {
        SOUL_WISP.setRegistryName("soul_wisp");
        registry.getRegistry().registerAll(
                SOUL_WISP
        );
    }

    @SubscribeEvent
    public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(SOUL_WISP, SoulWispParticle.Factory::new);
    }
}