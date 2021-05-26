package net.skeagle.skeaglesmodstuff.entity.eduardo;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class EduardoRenderer  extends MobRenderer<EduardoEntity, CowModel<EduardoEntity>> {
    private static final ResourceLocation EDUARDO_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/animal/eduardo.png");

    public EduardoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
    }

    public ResourceLocation getEntityTexture(EduardoEntity entity) {
        return EDUARDO_TEXTURES;
    }
}
