package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class MixinServerGamePacketListener implements ServerGamePacketListener {

    @Shadow public ServerPlayer player;

    @ModifyConstant(method = "handleInteract(Lnet/minecraft/network/protocol/game/ServerboundInteractPacket;)V",
            require = 2, allow = 2,
            constant = @Constant(doubleValue = 36.0))
    private double getReach(final double reach, final ServerboundInteractPacket packet) {
        return ReachUtil.getSqrAttackReach(player, reach);
    }
}
