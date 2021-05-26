package net.skeagle.skeaglesmodstuff;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.skeagle.skeaglesmodstuff.entity.eduardo.EduardoRenderer;
import net.skeagle.skeaglesmodstuff.entity.giantskeleton.GiantSkeletonRenderer;
import net.skeagle.skeaglesmodstuff.entity.human.dad.DadRenderer;
import net.skeagle.skeaglesmodstuff.entity.human.fido63.FidoRenderer;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeRenderer;

public class SMSRendering {

    static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.MILK_CUBE.get(), MilkCubeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.FIDO.get(), FidoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.DAD.get(), DadRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.GIANT_SKELETON.get(), GiantSkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SMSEntities.EDUARDO.get(), EduardoRenderer::new);
    }
}
