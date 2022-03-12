package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.skeagle.skeaglesmodstuff.registry.SMSParticles;
import net.skeagle.skeaglesmodstuff.registry.SMSTags;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow public
    abstract boolean updateFluidHeightAndDoFluidPushing(TagKey<Fluid> fluidTag, double motionScale);

    @ModifyArg(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"),
            index = 0,
            method = "doWaterSplashEffect()V")
    private ParticleOptions doWaterSplashEffect(ParticleOptions type) {
        if (this.updateFluidHeightAndDoFluidPushing(SMSTags.MILK, 0.014D)) {
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
