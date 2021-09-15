package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.server.network.ServerGamePacketListenerImpl$1")
public abstract class MixinServerboundInteractPacket implements ServerboundInteractPacket.Handler {
    @Shadow(aliases = "this$0") @Final
    private ServerGamePacketListenerImpl this$0;
    @Shadow(aliases = "val$entity") @Final
    private Entity val$entity;

    @Inject(method = "onAttack()V", at = @At("HEAD"),
            require = 1, allow = 1,
            cancellable = true)
    private void checkReachRange(CallbackInfo ci) {
        if (!ReachUtil.isEntityWithinRange(this$0.player, val$entity)) {
            ci.cancel();
        }
    }
}
