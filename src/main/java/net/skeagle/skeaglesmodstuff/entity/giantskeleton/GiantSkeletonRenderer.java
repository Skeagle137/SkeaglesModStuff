package net.skeagle.skeaglesmodstuff.entity.giantskeleton;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.resources.ResourceLocation;

public class GiantSkeletonRenderer extends SkeletonRenderer {

    private static final ResourceLocation GIANT_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public GiantSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(AbstractSkeleton entity) {
        return GIANT_SKELETON_TEXTURES;
    }
}
