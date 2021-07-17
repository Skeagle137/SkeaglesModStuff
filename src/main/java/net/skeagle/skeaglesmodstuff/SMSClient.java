package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.EntityViewRenderEvent;
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
        Minecraft.getInstance().particles.registerFactory(SMSParticles.DRIPPING_MILK.get(), MilkParticle.DrippingMilkFactory::new);
        Minecraft.getInstance().particles.registerFactory(SMSParticles.FALLING_MILK.get(), MilkParticle.FallingMilkFactory::new);
        Minecraft.getInstance().particles.registerFactory(SMSParticles.MILK_SPLASH.get(), MilkSplashParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(SMSParticles.MILK_BUBBLE.get(), MilkBubbleParticle.Factory::new);
    }

    void register() {
        bus.register(this.getClass());
        bus.addListener(SMSClient::setupClient);
    }
}
