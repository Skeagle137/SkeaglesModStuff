package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.SMSAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    public MixinLivingEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Inject(at = @At("RETURN"),
            require = 1, allow = 1,
            method = "registerAttributes()Lnet/minecraft/entity/ai/attributes/AttributeModifierMap$MutableAttribute;")
    private static void addReachAttribute(CallbackInfoReturnable<AttributeModifierMap.MutableAttribute> cir) {
        AttributeModifierMap.MutableAttribute builder = cir.getReturnValue();
        builder.createMutableAttribute(SMSAttributes.ATTACK_REACH.get());
    }
}
