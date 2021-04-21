package net.skeagle.skeaglesmodstuff.entity.human.fido63;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FidoRenderer extends BipedRenderer<FidoEntity, PlayerModel<FidoEntity>> {

    private static final ResourceLocation FIDO_TEXTURES = new ResourceLocation("textures/entity/human/fido.png");

    public FidoRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
    }

    public ResourceLocation getEntityTexture(AbstractSkeletonEntity entity) {
        return FIDO_TEXTURES;
    }
}
