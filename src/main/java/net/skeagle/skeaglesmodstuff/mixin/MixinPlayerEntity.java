package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.utils.ReachUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

    MixinPlayerEntity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @ModifyConstant(method = "attackTargetEntityWithCurrentItem(Lnet/minecraft/entity/Entity;)V", constant = @Constant(doubleValue = 9.0))
    private double getReach(final double reach) {
        return ReachUtil.getSqAttackReach(this, reach);
    }
}
