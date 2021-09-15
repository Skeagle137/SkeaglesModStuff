package net.skeagle.skeaglesmodstuff.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends AbstractContainerEventHandler {

    @Shadow @Final
    private boolean fading;
    @Shadow
    private long fadeInStart;

    @Inject(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screens/Screen;render(Lcom/mojang/blaze3d/vertex/PoseStack;IIF)V",
            shift = At.Shift.BEFORE),
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;IIF)V")
    private void render(PoseStack poseStack, int posX, int posY, float ticks, CallbackInfo ci) {
        Font font = Minecraft.getInstance().font;
        float f = this.fading ? (float)(Util.getMillis() - this.fadeInStart) / 1000.0F : 1.0F;
        float f1 = this.fading ? Mth.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = Mth.ceil(f1 * 255.0F) << 24;
        String s = "Skeagle's Mod Stuff " + SMSMain.getVersion();
        drawString(poseStack, font, ChatFormatting.LIGHT_PURPLE + s, 2, 2, 16777215 | l);
    }
}
