package net.skeagle.skeaglesmodstuff.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSFluids;
import net.skeagle.skeaglesmodstuff.SMSParticles;

@OnlyIn(Dist.CLIENT)
public class MilkParticle extends TextureSheetParticle {
    protected boolean isGlowing;

    public MilkParticle(ClientLevel world, double x, double y, double z) {
        super(world, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float f) {
        return this.isGlowing ? 240 : super.getLightColor(f);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
                BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
                FluidState fluidstate = this.level.getFluidState(blockpos);
                if (fluidstate.getType() == SMSFluids.MILK.get() && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                    this.remove();
                }

            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- < 1) {
            this.remove();
        }
    }

    protected void postMoveUpdate() {

    }

    @OnlyIn(Dist.CLIENT)
    static class Dripping extends MilkParticle {
        protected final ParticleOptions options;

        private Dripping(ClientLevel world, double x, double y, double z, ParticleOptions options) {
            super(world, x, y, z);
            this.gravity *= 0.02F;
            this.lifetime = 40;
            this.options = options;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- < 1) {
                this.remove();
                this.level.addParticle(this.options, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        protected void postMoveUpdate() {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class Falling extends MilkParticle {
        protected final ParticleOptions options;

        public Falling(ClientLevel world, double x, double y, double z, ParticleOptions options) {
            super(world, x, y, z);
            this.lifetime = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
            this.options = options;
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.options, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class DrippingMilkProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet set;

        public DrippingMilkProvider(SpriteSet set) {
            this.set = set;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Dripping drip = new Dripping(world, x, y, z, SMSParticles.FALLING_MILK.get());
            drip.setColor(0.985F, 0.998F, 0.998F);
            drip.pickSprite(this.set);
            return drip;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FallingMilkProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet set;

        public FallingMilkProvider(SpriteSet set) {
            this.set = set;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Falling fall = new MilkParticle.Falling(world, x, y, z, SMSParticles.MILK_SPLASH.get());
            fall.setColor(0.985F, 0.998F, 0.998F);
            fall.pickSprite(this.set);
            return fall;
        }
    }
}
