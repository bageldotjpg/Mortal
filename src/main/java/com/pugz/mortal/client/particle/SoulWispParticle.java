package com.pugz.mortal.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SoulWispParticle extends SpriteTexturedParticle {
    public SoulWispParticle(World world, double posX, double posY, double posZ, double xMotion, double yMotion, double zMotion) {
        super(world, posX, posY, posZ, xMotion, yMotion, zMotion);
        motionX *= 0.10000000149011612D;
        motionY *= 0.10000000149011612D;
        motionZ *= 0.10000000149011612D;
        motionX += xMotion * 0.4D;
        motionY += yMotion * 0.4D;
        motionZ += zMotion * 0.4D;
        particleRed = 1.0F;
        particleGreen = 1.0F;
        particleBlue = 1.0F;
        particleScale *= 0.75F;
        maxAge = Math.max((int)(6.0D / (Math.random() * 0.8D + 0.6D)), 1);
        canCollide = false;
        tick();
    }

    @Override
    public void tick() {
       prevPosX = posX;
       prevPosY = posY;
       prevPosZ = posZ;
        if (age++ >= maxAge) {
            setExpired();
        } else {
            move(motionX, motionY, motionZ);
            particleGreen = (float)((double)particleGreen * 0.96D);
            particleBlue = (float)((double)particleBlue * 0.9D);
            motionX *= 0.699999988079071D;
            motionY *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
            motionY -= 0.019999999552965164D;
            if (onGround) {
                motionX *= 0.699999988079071D;
                motionZ *= 0.699999988079071D;
            }

        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getBrightnessForRender(float partialTick) {
        float f = ((float) age + partialTick) / (float) maxAge;
        f = MathHelper.clamp(f, 0f, 1f);
        int i = super.getBrightnessForRender(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int) (f * 15f * 16f);
        if (j > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private IAnimatedSprite animatedSprite;

        public Factory(IAnimatedSprite sprite) {
            animatedSprite = sprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType type, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SoulWispParticle particle = new SoulWispParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.selectSpriteRandomly(animatedSprite);
            return particle;
        }
    }
}