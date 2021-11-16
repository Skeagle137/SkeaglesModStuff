package net.skeagle.skeaglesmodstuff.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;

@Mixin(Gui.class)
public abstract class MixinGui {

    @Shadow @Final
    protected Random random;

    @Shadow
    protected abstract void renderHeart(PoseStack poseStack, Gui.HeartType type, int p_168703_, int p_168704_, int p_168705_, boolean p_168706_, boolean p_168707_);

    @Inject(at = @At(value = "HEAD"),
            method = "renderHearts(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/player/Player;IIIIFIIIZ)V",
            cancellable = true)
    protected void renderHearts(PoseStack poseStack, Player player, int left, int top, int rowHeight, int regenPos, float healthMax,
                                int health, int healthLast, int absorbHealth, boolean highlighted, CallbackInfo ci) {
        if (healthMax != 684) {
            return;
        }
        Gui.HeartType heartType = Gui.HeartType.forPlayer(player);
        int textureOffset = 9 * (player.level.getLevelData().isHardcore() ? 5 : 0);
        int maxHearts = Mth.ceil(healthMax / 2.0D);
        int absorbAmount = Mth.ceil(absorbHealth / 2.0D);
        int actualMaxHealth = maxHearts * 2;

        for(int i = maxHearts + absorbAmount - 1; i >= 0; --i) {
            int heartY;
            int heartX;
            boolean renderOffset = false;
            if (i < 60) {
                heartY = i / 6;
                heartX = i % 6;
            }
            else if (i >= 80 && i < 262) {
                renderOffset = true;
                heartY = (i + 76) / 13;
                heartX = (i - 2) % 13;
            }
            else {
                heartY = (i + (i >= 262 ? -2 : 40)) / 10;
                heartX = (i - 2) % 10;
            }

            int offsetX;
            if (i < 60) {
                offsetX = left + (i % 6 <= 2 ? 0 : 32) + heartX * 8;
            }
            else {
                offsetX = left - (renderOffset ? 24 : 0) + (heartX * 8);
            }
            int offsetY = top - heartY * rowHeight;
            if (health + absorbHealth <= 4) {
                offsetY += this.random.nextInt(2); //low health shaking effect
            }

            if (i < maxHearts && i == regenPos) {
                offsetY -= 2; //regen "wave" effect
            }

            //render highlights
            this.renderHeart(poseStack, Gui.HeartType.CONTAINER, offsetX, offsetY, textureOffset, highlighted, false);

            //if the player has absorption, then render the hearts
            int actualHealth = i * 2;
            boolean hasAbsorb = i >= maxHearts;
            if (hasAbsorb) {
                int absorbHearts = actualHealth - actualMaxHealth;
                if (absorbHearts < absorbHealth) {
                    boolean shouldDrawFull = absorbHearts + 1 == absorbHealth;
                    this.renderHeart(poseStack, heartType == Gui.HeartType.WITHERED ? heartType : Gui.HeartType.ABSORBING, offsetX, offsetY, textureOffset, false, shouldDrawFull);
                }
            }

            //damage and natural regen "highlight" effect
            if (highlighted && actualHealth < healthLast) {
                boolean shouldDrawFull = actualHealth + 1 == healthLast;
                this.renderHeart(poseStack, heartType, offsetX, offsetY, textureOffset, true, shouldDrawFull);
            }

            //normal health no effect
            if (actualHealth < health) {
                boolean shouldDrawFull = actualHealth + 1 == health;
                if ((renderOffset && heartX > 7 && heartY >= 20) || (!renderOffset && heartX > 4 && heartY > 25 && heartY <= 28)) {
                    heartType = Gui.HeartType.FROZEN;
                }
                else {
                    heartType = Gui.HeartType.forPlayer(player);
                }
                this.renderHeart(poseStack, heartType, offsetX, offsetY, textureOffset, false, shouldDrawFull);
            }
        }
        ci.cancel();
    }
}
