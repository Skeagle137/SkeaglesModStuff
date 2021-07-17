package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.IReloadableResourceManager;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer implements IReloadableResourceManager {

    @Shadow @Final
    private Minecraft mc;

    @ModifyConstant(method = "getMouseOver(F)V", constant = @Constant(doubleValue = 3.0))
    private double getReach0(final double reach) {
        if (mc.player != null) {
            return ReachUtil.getAttackReach(mc.player, reach);
        }
        return reach;
    }

    @ModifyVariable(at = @At(value = "STORE",
            opcode = Opcodes.DSTORE),
            ordinal = 1,
            method = "getMouseOver(F)V")
    private double getReach1(final double reach) {
        if (mc.player != null) {
            return ReachUtil.getSqAttackReach(mc.player, reach);
        }
        return reach;
    }

    @ModifyVariable(at = @At(value = "STORE"), method = "getMouseOver(F)V")
    private double getReach2(final double reach) {
        if (this.mc.playerController != null && mc.player != null && reach == (double) this.mc.playerController.getBlockReachDistance()) {
            return ReachUtil.getAttackReach(mc.player, reach);
        }
        return reach;
    }

    @ModifyConstant(method = "getMouseOver(F)V", constant = @Constant(doubleValue = 9.0))
    private double getReach3(final double reach) {
        if (mc.player != null) {
            return ReachUtil.getSqAttackReach(mc.player, reach);
        }
        return reach;
    }
}
