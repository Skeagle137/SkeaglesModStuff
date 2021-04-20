package net.skeagle.skeaglesmodstuff.entity.milkcube;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;

@OnlyIn(Dist.CLIENT)
public class MilkCubeRenderer extends MobRenderer<MilkCubeEntity, SlimeModel<MilkCubeEntity>> {
    private static final ResourceLocation MILK_CUBE_TEXTURES = new ResourceLocation(SMSMain.MODID, "textures/entity/milk_cube/milk_cube.png");

    public MilkCubeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SlimeModel<>(16), 0.25F);
        this.addLayer(new SlimeGelLayer<>(this));
    }

    public void render(MilkCubeEntity entity, float yaw, float ticks, MatrixStack matrix, IRenderTypeBuffer buffer, int light) {
        this.shadowSize = 0.25F * (float) entity.getSlimeSize();
        super.render(entity, yaw, ticks, matrix, buffer, light);
    }

    protected void preRenderCallback(MilkCubeEntity entity, MatrixStack matrix, float time) {
        matrix.scale(0.999F, 0.999F, 0.999F);
        matrix.translate(0.0D, 0.001F, 0.0D);
        float f1 = (float) entity.getSlimeSize();
        float f2 = MathHelper.lerp(time, entity.prevSquishFactor, entity.squishFactor) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrix.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    public ResourceLocation getEntityTexture(MilkCubeEntity entity) {
        return MILK_CUBE_TEXTURES;
    }
}
