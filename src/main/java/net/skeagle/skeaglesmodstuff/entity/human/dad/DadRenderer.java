package net.skeagle.skeaglesmodstuff.entity.human.dad;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class DadRenderer extends BipedRenderer<DadEntity, PlayerModel<DadEntity>> {

    private static final ResourceLocation DAD_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/human/dad.png");

    public DadRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
    }

    public ResourceLocation getEntityTexture(DadEntity entity) {
        return DAD_TEXTURES;
    }
}
