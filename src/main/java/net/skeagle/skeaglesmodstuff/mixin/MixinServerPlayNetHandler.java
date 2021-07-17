package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetHandler.class)
public abstract class MixinServerPlayNetHandler implements IServerPlayNetHandler {

    @Shadow
    public ServerPlayerEntity player;

    @ModifyConstant(method = "processUseEntity(Lnet/minecraft/network/play/client/CUseEntityPacket;)V",
            require = 2, allow = 2,
            constant = @Constant(doubleValue = 36.0))
    private double getReach(final double reach, final CUseEntityPacket packet) {
        return packet.getAction() == CUseEntityPacket.Action.ATTACK ? ReachUtil.getSqAttackReach(this.player, reach) : reach;
    }
}
