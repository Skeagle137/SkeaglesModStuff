package net.skeagle.skeaglesmodstuff.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSFluids;
import net.skeagle.skeaglesmodstuff.SMSParticles;

@OnlyIn(Dist.CLIENT)
public class MilkParticle extends SpriteTexturedParticle {
    private final IParticleData data;
    private boolean fullbright;

    public MilkParticle(ClientWorld world, double x, double y, double z, IParticleData data) {
        super(world, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.0012F;
        this.data = data;
        this.maxAge = 40;
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getBrightnessForRender(float partialTick) {
        return this.fullbright ? 240 : super.getBrightnessForRender(partialTick);
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.ageParticle();
        if (!this.isExpired) {
            this.motionY -= this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.updateMotion();
            if (!this.isExpired) {
                this.motionX *= 0.98F;
                this.motionY *= 0.98F;
                this.motionZ *= 0.98F;
                BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
                FluidState fluidstate = this.world.getFluidState(blockpos);
                if (fluidstate.getFluid() == SMSFluids.MILK.get() && this.posY < (double)((float)blockpos.getY() + fluidstate.getActualHeight(this.world, blockpos)))
                    this.setExpired();
            }
        }
    }

    protected void ageParticle() {
        if (this.maxAge-- <= 0) {
            this.setExpired();
            this.world.addParticle(this.data, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }
    }

    protected void updateMotion() {
        this.motionX *= 0.02D;
        this.motionY *= 0.02D;
        this.motionZ *= 0.02D;
    }

    @OnlyIn(Dist.CLIENT)
    static class FallingMilk extends MilkParticle {
        protected final IParticleData data;

        public FallingMilk(ClientWorld world, double x, double y, double z, IParticleData data) {
            super(world, x, y, z, data);
            this.data = data;
            this.maxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
        }

        protected void updateMotion() {
            if (this.onGround) {
                this.setExpired();
                this.world.addParticle(this.data, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class DrippingMilkFactory implements IParticleFactory<BasicParticleType> {
        protected final IAnimatedSprite spriteSet;

        public DrippingMilkFactory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MilkParticle milk = new MilkParticle(worldIn, x, y, z, SMSParticles.FALLING_MILK.get());
            milk.setColor(0.985F, 0.998F, 0.998F);
            milk.selectSpriteRandomly(this.spriteSet);
            return milk;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FallingMilkFactory implements IParticleFactory<BasicParticleType> {
        protected final IAnimatedSprite spriteSet;

        public FallingMilkFactory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MilkParticle milk = new MilkParticle.FallingMilk(worldIn, x, y, z, ParticleTypes.SPLASH);
            milk.setColor(0.985F, 0.998F, 0.998F);
            milk.selectSpriteRandomly(this.spriteSet);
            return milk;
        }
    }
}
