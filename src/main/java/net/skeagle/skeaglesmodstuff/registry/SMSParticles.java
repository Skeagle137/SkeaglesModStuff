package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class SMSParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SMSMain.MODID);

    public static final RegistryObject<SimpleParticleType> DRIPPING_MILK = register("dripping_milk", false);
    public static final RegistryObject<SimpleParticleType> FALLING_MILK = register("falling_milk", false);
    public static final RegistryObject<SimpleParticleType> MILK_SPLASH = register("milk_splash", false);
    public static final RegistryObject<SimpleParticleType> MILK_BUBBLE = register("milk_bubble", false);

    private static RegistryObject<SimpleParticleType> register(String name, boolean alwaysShow) {
        return PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
    }
}
