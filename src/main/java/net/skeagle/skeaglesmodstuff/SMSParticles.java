package net.skeagle.skeaglesmodstuff;

import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.fml.RegistryObject;

public class SMSParticles {
    public static final RegistryObject<BasicParticleType> DRIPPING_MILK = register("dripping_milk", false);
    public static final RegistryObject<BasicParticleType> FALLING_MILK = register("falling_milk", false);
    public static final RegistryObject<BasicParticleType> MILK_SPLASH = register("milk_splash", false);
    public static final RegistryObject<BasicParticleType> MILK_BUBBLE = register("milk_bubble", false);

    private static RegistryObject<BasicParticleType> register(String name, boolean alwaysShow) {
        return Registry.PARTICLES.register(name, () -> new BasicParticleType(alwaysShow));
    }

    static void init() {}
}
