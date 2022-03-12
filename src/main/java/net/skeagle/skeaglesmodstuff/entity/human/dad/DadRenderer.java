package net.skeagle.skeaglesmodstuff.entity.human.dad;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class DadRenderer extends HumanoidMobRenderer<DadEntity, PlayerModel<DadEntity>> {

    private static final ResourceLocation DAD_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/human/dad.png");

    public DadRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER))));
    }

    public ResourceLocation getTextureLocation(DadEntity entity) {
        return DAD_TEXTURES;
    }
}
