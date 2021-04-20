package net.skeagle.skeaglesmodstuff;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SMSMain.MODID)
public class SMSMain {
    public static final String MODID = "smodstuff";

    public SMSMain() {
        Registry.register();
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupCommon);
        SMSClient client = new SMSClient(bus);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> client::register);
    }

    public void setupCommon(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            SMSEntities.registerSpawns();
        });
    }
}
