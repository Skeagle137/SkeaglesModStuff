package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skeagle.skeaglesmodstuff.particle.MilkParticle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SMSMain.MODID)
public class SMSMain {
    public static final String MODID = "smodstuff";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public SMSMain() {
        ModSetup.register();
    }

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent e) {
        Minecraft.getInstance().particles.registerFactory(SMSParticles.DRIPPING_MILK.get(), MilkParticle.DrippingMilkFactory::new);
        Minecraft.getInstance().particles.registerFactory(SMSParticles.FALLING_MILK.get(), MilkParticle.FallingMilkFactory::new);
    }
}
