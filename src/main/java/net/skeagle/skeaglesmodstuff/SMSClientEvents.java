package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skeagle.skeaglesmodstuff.registry.SMSTags;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, value = Dist.CLIENT)
public class SMSClientEvents {

    @SubscribeEvent
    public static void onFogRender(EntityViewRenderEvent.FogColors e) {
        Camera renderInfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        if (renderInfo.getEntity().level.getFluidState(renderInfo.getBlockPosition()).is(SMSTags.MILK)) {
            e.setRed(0.98F);
            e.setGreen(0.98F);
            e.setBlue(0.98F);
        }
    }

}
