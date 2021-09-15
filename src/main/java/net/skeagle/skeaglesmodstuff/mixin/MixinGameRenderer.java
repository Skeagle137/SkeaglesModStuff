package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer implements ReloadableResourceManager {

    @Shadow @Final
    private Minecraft minecraft;

    @ModifyConstant(method = "pick(F)V", constant = @Constant(doubleValue = 3.0D))
    private double getReach0(final double reach) {
        if (minecraft.player != null && this.minecraft.gameMode != null) {
            return ReachUtil.getAttackReach(minecraft.player, reach);
        }
        return reach;
    }

    @ModifyConstant(method = "pick(F)V", require = 1, allow = 1, constant = @Constant(doubleValue = 6.0D))
    private double getReach1(final double reach) {
        if (minecraft.player != null) {
            return ReachUtil.getAttackReach(minecraft.player, reach);
        }
        return reach;
    }

    @ModifyVariable(at = @At(value = "STORE"),
            ordinal = 1,
            method = "pick(F)V")
    private double getReach2(final double reach) {
        if (minecraft.player != null) {
            return ReachUtil.getSqrAttackReach(minecraft.player, reach);
        }
        return reach;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;pick(DFZ)Lnet/minecraft/world/phys/HitResult;"), method = "pick(F)V")
    private double getReach3(double d) {
        if (this.minecraft.gameMode != null && !this.minecraft.gameMode.hasFarPickRange()) {
            return this.minecraft.gameMode.getPickRange();
        }
        return d;
    }

    @ModifyVariable(at = @At(value = "STORE"), require = 1, allow = 1, method = "pick(F)V")
    private double getReach4(final double reach) {
        if (this.minecraft.gameMode != null && minecraft.player != null && !this.minecraft.gameMode.hasFarPickRange()) {
            return ReachUtil.getAttackReach(minecraft.player, reach);
        }
        return reach;
    }

    @ModifyConstant(method = "pick(F)V", constant = @Constant(doubleValue = 9.0D))
    private double getReach5(final double reach) {
        if (minecraft.player != null) {
            System.out.println("d2 " + reach);
            System.out.println(ReachUtil.getAttackReach(minecraft.player, reach));
            return ReachUtil.getSqrAttackReach(minecraft.player, reach);
        }
        return reach;
    }
}
