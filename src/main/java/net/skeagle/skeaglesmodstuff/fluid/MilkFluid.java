package net.skeagle.skeaglesmodstuff.fluid;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.skeagle.skeaglesmodstuff.*;

public abstract class MilkFluid extends ForgeFlowingFluid {

    public MilkFluid(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public ParticleOptions getDripParticle() {
        return SMSParticles.DRIPPING_MILK.get();
    }
}
