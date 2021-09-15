package net.skeagle.skeaglesmodstuff;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.skeagle.skeaglesmodstuff.entity.eduardo.EduardoRenderer;
import net.skeagle.skeaglesmodstuff.entity.giantskeleton.GiantSkeletonRenderer;
import net.skeagle.skeaglesmodstuff.entity.human.dad.DadRenderer;
import net.skeagle.skeaglesmodstuff.entity.human.fido63.FidoRenderer;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeRenderer;

public class SMSRendering {

    static void registerEntityRenderers() {
        EntityRenderers.register(SMSEntities.MILK_CUBE_TYPE, MilkCubeRenderer::new);
        EntityRenderers.register(SMSEntities.FIDO_TYPE, FidoRenderer::new);
        EntityRenderers.register(SMSEntities.DAD_TYPE, DadRenderer::new);
        EntityRenderers.register(SMSEntities.GIANT_SKELETON_TYPE, GiantSkeletonRenderer::new);
        EntityRenderers.register(SMSEntities.EDUARDO_ENTITY_TYPE, EduardoRenderer::new);
    }
}
