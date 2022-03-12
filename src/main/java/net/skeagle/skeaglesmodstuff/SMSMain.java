package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Camera;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.skeagle.skeaglesmodstuff.registry.*;

import java.util.List;

@Mod.EventBusSubscriber
@Mod(SMSMain.MODID)
public class SMSMain {
    public static final String MODID = "smodstuff";
    public static final String[] capeAccounts = { "Skeagle_", "IcaLLJAckS", "Tamunda", "Valasa", "SerenadeSP", "Spoop_me_boopy", "YouCantBetMe", "DeadedAndBreaded" };
    public static final boolean DEV_MODE = true;

    public SMSMain() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        List<DeferredRegister<?>> registers = List.of(
                SMSEntities.ENTITIES,
                SMSItems.ITEMS,
                SMSBlocks.BLOCKS,
                SMSSounds.SOUNDS,
                SMSFluids.FLUIDS,
                SMSPaintings.PAINTINGS,
                SMSParticles.PARTICLES
        );
        registers.forEach(r -> r.register(bus));

        bus.addListener(this::setupCommon);
    }

    public void setupCommon(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            SMSNetwork.registerChannel();
            SMSEntities.registerSpawns();
        });
    }

    public static String getVersion() {
        return "v" + ModList.get().getModContainerById(MODID).get().getModInfo().getVersion().toString();
    }
}
