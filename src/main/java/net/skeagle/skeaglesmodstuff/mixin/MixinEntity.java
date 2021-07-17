package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.ITag;
import net.skeagle.skeaglesmodstuff.SMSParticles;
import net.skeagle.skeaglesmodstuff.SMSTags;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow public
    abstract boolean handleFluidAcceleration(ITag<Fluid> fluidTag, double motionScale);

    @ModifyArg(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"),
            index = 0,
            method = "doWaterSplashEffect()V")
    private IParticleData doWaterSplashEffect(IParticleData type) {
        if (this.handleFluidAcceleration(SMSTags.Fluids.MILK, 0.014D)) {
            if (type == ParticleTypes.BUBBLE) {
                return SMSParticles.MILK_BUBBLE.get();
            }
            if (type == ParticleTypes.SPLASH) {
                return SMSParticles.MILK_SPLASH.get();
            }
        }
        return type;
    }
}
