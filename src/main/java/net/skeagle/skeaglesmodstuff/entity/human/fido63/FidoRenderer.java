package net.skeagle.skeaglesmodstuff.entity.human.fido63;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;

@OnlyIn(Dist.CLIENT)
public class FidoRenderer extends HumanoidMobRenderer<FidoEntity, PlayerModel<FidoEntity>> {

    private static final ResourceLocation FIDO_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/human/fido.png");

    public FidoRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER))));
    }

    public ResourceLocation getTextureLocation(FidoEntity entity) {
        return FIDO_TEXTURES;
    }
}
