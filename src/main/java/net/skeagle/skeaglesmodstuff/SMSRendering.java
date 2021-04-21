package net.skeagle.skeaglesmodstuff;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeRenderer;

public class SMSRendering {

    static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.MILK_CUBE.get(), MilkCubeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.FIDO.get(), MilkCubeRenderer::new);
    }
}
