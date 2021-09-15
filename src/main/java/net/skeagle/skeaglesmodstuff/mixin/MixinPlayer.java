package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity {

    MixinPlayer(EntityType<? extends LivingEntity> type, Level world) {
        super(type, world);
    }

    @ModifyConstant(method = "attack(Lnet/minecraft/world/entity/Entity;)V", constant = @Constant(doubleValue = 9.0))
    private double getReach(final double reach) {
        return ReachUtil.getSqrAttackReach(this, reach);
    }
}
