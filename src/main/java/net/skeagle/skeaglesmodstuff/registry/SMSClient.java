package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.entity.eduardo.EduardoEntity;
import net.skeagle.skeaglesmodstuff.entity.eduardo.EduardoRenderer;
import net.skeagle.skeaglesmodstuff.entity.giantskeleton.GiantSkeletonEntity;
import net.skeagle.skeaglesmodstuff.entity.giantskeleton.GiantSkeletonRenderer;
import net.skeagle.skeaglesmodstuff.entity.human.dad.DadEntity;
import net.skeagle.skeaglesmodstuff.entity.human.dad.DadRenderer;
import net.skeagle.skeaglesmodstuff.entity.human.fido63.FidoEntity;
import net.skeagle.skeaglesmodstuff.entity.human.fido63.FidoRenderer;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeEntity;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeRenderer;
import net.skeagle.skeaglesmodstuff.particle.*;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SMSClient {

    //entity renders registry
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(SMSEntities.MILK_CUBE.get(), MilkCubeRenderer::new);
        e.registerEntityRenderer(SMSEntities.FIDO.get(), FidoRenderer::new);
        e.registerEntityRenderer(SMSEntities.DAD.get(), DadRenderer::new);
        e.registerEntityRenderer(SMSEntities.GIANT_SKELETON.get(), GiantSkeletonRenderer::new);
        e.registerEntityRenderer(SMSEntities.EDUARDO.get(), EduardoRenderer::new);
    }

    //entity attributes
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        e.put(SMSEntities.MILK_CUBE.get(), MilkCubeEntity.registerAttributes().build());
        e.put(SMSEntities.FIDO.get(), FidoEntity.registerAttributes().build());
        e.put(SMSEntities.DAD.get(), DadEntity.registerAttributes().build());
        e.put(SMSEntities.GIANT_SKELETON.get(), GiantSkeletonEntity.registerAttributes().build());
        e.put(SMSEntities.EDUARDO.get(), EduardoEntity.createAttributes().build());
    }

    //particle registry
    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent e) {
        Minecraft.getInstance().particleEngine.register(SMSParticles.DRIPPING_MILK.get(), MilkParticle.DrippingMilkProvider::new);
        Minecraft.getInstance().particleEngine.register(SMSParticles.FALLING_MILK.get(), MilkParticle.FallingMilkProvider::new);
        Minecraft.getInstance().particleEngine.register(SMSParticles.MILK_SPLASH.get(), MilkSplashParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(SMSParticles.MILK_BUBBLE.get(), MilkBubbleParticle.Provider::new);
    }
}
