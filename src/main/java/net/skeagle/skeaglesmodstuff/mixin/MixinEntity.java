package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.fluid.Fluid;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.ITag;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.SMSParticles;
import net.skeagle.skeaglesmodstuff.SMSTags;
import org.spongepowered.asm.mixin.*;
import java.util.Random;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow @Final protected Random rand;
    @Shadow private EntitySize size;
    @Shadow public World world;
    @Shadow public abstract void playSound(SoundEvent soundIn, float volume, float pitch);
    @Shadow protected abstract SoundEvent getHighspeedSplashSound();
    @Shadow protected abstract SoundEvent getSplashSound();
    @Shadow public abstract Entity getControllingPassenger();
    @Shadow public abstract boolean isBeingRidden();
    @Shadow public abstract boolean handleFluidAcceleration(ITag<Fluid> fluidTag, double motionScale);
    @Shadow public abstract double getPosX();
    @Shadow public abstract double getPosY();
    @Shadow public abstract double getPosZ();

    protected void doWaterSplashEffect() {
        Entity entity = this.isBeingRidden() && this.getControllingPassenger() != null ? this.getControllingPassenger() : (Entity) (Object) this;
        float f = entity == (Object) this ? 0.2F : 0.9F;
        Vector3d vector3d = entity.getMotion();
        float f1 = MathHelper.sqrt(vector3d.x * vector3d.x * (double)0.2F + vector3d.y * vector3d.y + vector3d.z * vector3d.z * (double)0.2F) * f;
        if (f1 > 1.0F)
            f1 = 1.0F;

        if ((double)f1 < 0.25D)
            this.playSound(this.getSplashSound(), f1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
        else
            this.playSound(this.getHighspeedSplashSound(), f1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);

        float f2 = (float) MathHelper.floor(this.getPosY());
        boolean flag = this.handleFluidAcceleration(SMSTags.Fluids.MILK, 0.014D);
        IParticleData data = flag ? SMSParticles.MILK_BUBBLE.get() : ParticleTypes.BUBBLE;
        IParticleData data2 = flag ? SMSParticles.MILK_SPLASH.get() : ParticleTypes.SPLASH;

        for(int i = 0; (float)i < 1.0F + this.size.width * 20.0F; ++i) {
            double d0 = (this.rand.nextDouble() * 2.0D - 1.0D) * (double)this.size.width;
            double d1 = (this.rand.nextDouble() * 2.0D - 1.0D) * (double)this.size.width;
            this.world.addParticle(data, this.getPosX() + d0, (f2 + 1.0F), this.getPosZ() + d1, vector3d.x, vector3d.y - this.rand.nextDouble() * (double)0.2F, vector3d.z);
        }

        for(int j = 0; (float)j < 1.0F + this.size.width * 20.0F; ++j) {
            double d2 = (this.rand.nextDouble() * 2.0D - 1.0D) * (double)this.size.width;
            double d3 = (this.rand.nextDouble() * 2.0D - 1.0D) * (double)this.size.width;
            this.world.addParticle(data2, this.getPosX() + d2, (f2 + 1.0F), this.getPosZ() + d3, vector3d.x, vector3d.y, vector3d.z);
        }
    }
}
