package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Camera;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber
@Mod(SMSMain.MODID)
public class SMSMain {
    public static final String MODID = "smodstuff";
    public static final String[] capeAccounts = { "Skeagle_", "IcaLLJAckS", "Tamunda", "Valasa", "Fido63", "Megaderp_", "Sockings", "SerenadeSP",
            "Spoop_me_boopy", "NinjaofTehSkai", "YouCantBetMe", "DeadedAndBreaded" };
    public static final boolean DEV_MODE = true;

    public SMSMain() {
        Registry.register();
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupCommon);
        bus.addListener(this::loadComplete);
        SMSClient client = new SMSClient(bus);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> client::register);
    }

    @SubscribeEvent
    public static void onFogRender(EntityViewRenderEvent.FogColors e) {
        Camera renderInfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        if (renderInfo.getEntity().level.getFluidState(renderInfo.getBlockPosition()).is(SMSTags.Fluids.MILK)) {
            e.setRed(0.98F);
            e.setGreen(0.98F);
            e.setBlue(0.98F);
        }
    }

    @SubscribeEvent
    public static void onInterModProcess(InterModProcessEvent event) {
        LiquidBlock block = (LiquidBlock) SMSBlocks.MILK_FLUID_BLOCK.get();
        block.fluid = block.getFluid();
        System.out.println(block.getFluid());
    }

    public void loadComplete(FMLLoadCompleteEvent e) {
        System.out.println(SMSItems.ENCHANTED_MILK_BUCKET.get());
        System.out.println(SMSFluids.MILK.get());
        System.out.println(SMSFluids.FLOWING_MILK.get());
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
