package net.skeagle.skeaglesmodstuff.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MilkSplashParticle extends RainParticle {

    private MilkSplashParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.particleGravity = 0.04F;
        if (motionY == 0.0D && (motionX != 0.0D || motionZ != 0.0D)) {
            this.motionX = motionX;
            this.motionY = 0.1D;
            this.motionZ = motionZ;
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MilkSplashParticle milkSplashParticle = new MilkSplashParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            milkSplashParticle.selectSpriteRandomly(this.spriteSet);
            return milkSplashParticle;
        }
    }
}
