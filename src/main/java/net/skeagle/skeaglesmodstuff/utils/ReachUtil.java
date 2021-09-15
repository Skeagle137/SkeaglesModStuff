package net.skeagle.skeaglesmodstuff.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.skeagle.skeaglesmodstuff.SMSAttributes;

public class ReachUtil {

    public static double getAttackReach(LivingEntity entity, double baseReach) {
        AttributeInstance reach = entity.getAttribute(SMSAttributes.ATTACK_REACH.get());
        return reach != null ? baseReach + reach.getValue() : baseReach;
    }

    public static double getSqrAttackReach(LivingEntity entity, double sqBaseReach) {
        double reach = getAttackReach(entity, Math.sqrt(sqBaseReach));
        return reach * reach;
    }

    public static boolean isEntityWithinRange(LivingEntity entity, Entity target) {
        return entity.distanceToSqr(target) <= getSqrAttackReach(entity, 64.0);
    }
}
