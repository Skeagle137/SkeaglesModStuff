package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber
@Mod(SMSMain.MODID)
public class SMSMain {
    public static final String MODID = "smodstuff";
    public static final String VERSION = "0.3-1.16.5";
    public static final boolean DEV_MODE = true;

    public SMSMain() {
        Registry.register();
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupCommon);
        SMSClient client = new SMSClient(bus);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> client::register);
    }

    @SubscribeEvent
    public static void onFogRender(EntityViewRenderEvent.FogColors e) {
        ActiveRenderInfo renderInfo = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
        if (renderInfo.getFluidState().isTagged(SMSTags.Fluids.MILK)) {
            e.setRed(0.98F);
            e.setGreen(0.98F);
            e.setBlue(0.98F);
        }
    }

    public void setupCommon(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            SMSNetwork.registerChannel();
            SMSEntities.registerSpawns();
        });
    }
}
