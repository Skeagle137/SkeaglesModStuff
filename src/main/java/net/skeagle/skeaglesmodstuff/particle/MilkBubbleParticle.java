package net.skeagle.skeaglesmodstuff.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MilkBubbleParticle extends TextureSheetParticle {
    private MilkBubbleParticle(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z);
        this.setSize(0.02F, 0.02F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.2F;
        this.xd = motionX * (double)0.2F + (Math.random() * 2.0D - 1.0D) * (double)0.02F;
        this.yd = motionY * (double)0.2F + (Math.random() * 2.0D - 1.0D) * (double)0.02F;
        this.zd = motionZ * (double)0.2F + (Math.random() * 2.0D - 1.0D) * (double)0.02F;
        this.lifetime = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.yd += 0.002D;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.85F;
            this.yd *= 0.85F;
            this.zd *= 0.85F;
            if (!this.level.getFluidState(new BlockPos(this.x, this.y, this.z)).is(FluidTags.WATER)) {
                this.remove();
            }

        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet set;

        public Provider(SpriteSet set) {
            this.set = set;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MilkBubbleParticle particle = new MilkBubbleParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.set);
            return particle;
        }
    }
}