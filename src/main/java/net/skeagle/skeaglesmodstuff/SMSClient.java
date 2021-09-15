package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.skeagle.skeaglesmodstuff.particle.*;

public class SMSClient {

    private final IEventBus bus;

    public SMSClient(IEventBus bus) {
        this.bus = bus;
    }

    public static void setupClient(FMLClientSetupEvent e) {
        SMSRendering.registerEntityRenderers();
    }

    //particle registry
    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent e) {
        Minecraft.getInstance().particleEngine.register(SMSParticles.DRIPPING_MILK.get(), MilkParticle.DrippingMilkProvider::new);
        Minecraft.getInstance().particleEngine.register(SMSParticles.FALLING_MILK.get(), MilkParticle.FallingMilkProvider::new);
        Minecraft.getInstance().particleEngine.register(SMSParticles.MILK_SPLASH.get(), MilkSplashParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SMSParticles.MILK_BUBBLE.get(), MilkBubbleParticle.Provider::new);
    }

    void register() {
        bus.register(this.getClass());
        bus.addListener(SMSClient::setupClient);
    }
}
