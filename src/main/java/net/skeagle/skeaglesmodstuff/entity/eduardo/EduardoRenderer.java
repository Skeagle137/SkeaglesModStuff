package net.skeagle.skeaglesmodstuff.entity.eduardo;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.CowModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;

@OnlyIn(Dist.CLIENT)
public class EduardoRenderer extends MobRenderer<EduardoEntity, CowModel<EduardoEntity>> {
    private static final ResourceLocation EDUARDO_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/animal/eduardo.png");

    public EduardoRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public ResourceLocation getTextureLocation(EduardoEntity entity) {
        return EDUARDO_TEXTURES;
    }
}
