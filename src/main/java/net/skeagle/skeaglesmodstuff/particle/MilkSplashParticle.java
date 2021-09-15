package net.skeagle.skeaglesmodstuff.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MilkSplashParticle extends WaterDropParticle {

    private MilkSplashParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.gravity = 0.04F;
        if (motionY == 0.0D && (motionX != 0.0D || motionZ != 0.0D)) {
            this.xd = motionX;
            this.yd = 0.1D;
            this.zd = motionZ;
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet set;

        public Provider(SpriteSet set) {
            this.set = set;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MilkSplashParticle milkSplashParticle = new MilkSplashParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            milkSplashParticle.pickSprite(this.set);
            return milkSplashParticle;
        }
    }
}
