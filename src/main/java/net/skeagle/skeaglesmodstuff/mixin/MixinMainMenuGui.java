package net.skeagle.skeaglesmodstuff.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.skeagle.skeaglesmodstuff.SMSMain;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(MainMenuScreen.class)
public class MixinMainMenuGui extends AbstractGui {

    @Shadow @Final
    private boolean showFadeInAnimation;
    @Shadow
    private long firstRenderTime;

    @Inject(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screen/Screen;render(Lcom/mojang/blaze3d/matrix/MatrixStack;IIF)V",
            shift = At.Shift.BEFORE),
            method = "render(Lcom/mojang/blaze3d/matrix/MatrixStack;IIF)V")
    private void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        float f = this.showFadeInAnimation ? (float)(Util.milliTime() - this.firstRenderTime) / 1000.0F : 1.0F;
        float f1 = this.showFadeInAnimation ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(f1 * 255.0F) << 24;
        String s = "Skeagle's Mod Stuff v" + SMSMain.VERSION;
        drawString(matrixStack, font, TextFormatting.LIGHT_PURPLE + s, 2, 2, 16777215 | l);
    }
}
