package net.skeagle.skeaglesmodstuff.entity.milkcube;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;

@OnlyIn(Dist.CLIENT)
public class MilkCubeRenderer extends MobRenderer<MilkCubeEntity, SlimeModel<MilkCubeEntity>> {
    private static final ResourceLocation MILK_CUBE_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/milk_cube/milk_cube.png");

    public MilkCubeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel<>(context.bakeLayer(ModelLayers.SLIME)), 0.25F);
        this.addLayer(new SlimeOuterLayer<>(this, context.getModelSet()));
    }

    public void render(MilkCubeEntity entity, float yaw, float ticks, PoseStack matrix, MultiBufferSource buffer, int light) {
        this.shadowRadius = 0.25F * (float) entity.getSize();
        super.render(entity, yaw, ticks, matrix, buffer, light);
    }

    protected void scale(MilkCubeEntity entity, PoseStack matrix, float time) {
        matrix.scale(0.999F, 0.999F, 0.999F);
        matrix.translate(0.0D, 0.001F, 0.0D);
        float f1 = (float) entity.getSize();
        float f2 = Mth.lerp(time, entity.oSquish, entity.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrix.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    public ResourceLocation getTextureLocation(MilkCubeEntity entity) {
        return MILK_CUBE_TEXTURES;
    }
}
