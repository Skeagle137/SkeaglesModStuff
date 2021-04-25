package net.skeagle.skeaglesmodstuff.entity.giantskeleton;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;

public class GiantSkeletonRenderer extends SkeletonRenderer {

    private static final ResourceLocation GIANT_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public GiantSkeletonRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public ResourceLocation getEntityTexture(AbstractSkeletonEntity entity) {
        return GIANT_SKELETON_TEXTURES;
    }

    protected void preRenderCallback(AbstractSkeletonEntity entity, MatrixStack matrix, float tickTime) {
        matrix.scale(12.0F, 12.0F, 12.0F);
    }
}
