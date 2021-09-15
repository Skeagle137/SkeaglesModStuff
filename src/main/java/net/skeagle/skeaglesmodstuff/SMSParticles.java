package net.skeagle.skeaglesmodstuff;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fmllegacy.RegistryObject;

public class SMSParticles {
    public static final RegistryObject<SimpleParticleType> DRIPPING_MILK = register("dripping_milk", false);
    public static final RegistryObject<SimpleParticleType> FALLING_MILK = register("falling_milk", false);
    public static final RegistryObject<SimpleParticleType> MILK_SPLASH = register("milk_splash", false);
    public static final RegistryObject<SimpleParticleType> MILK_BUBBLE = register("milk_bubble", false);

    private static RegistryObject<SimpleParticleType> register(String name, boolean alwaysShow) {
        return Registry.PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
    }

    static void init() {}
}
