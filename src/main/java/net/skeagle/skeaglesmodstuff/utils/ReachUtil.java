package net.skeagle.skeaglesmodstuff.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.skeagle.skeaglesmodstuff.SMSAttributes;

public class ReachUtil {

    public static double getAttackReach(LivingEntity entity, double baseReach) {
        ModifiableAttributeInstance reach = entity.getAttribute(SMSAttributes.ATTACK_REACH.get());
        return reach != null ? baseReach + reach.getValue() : baseReach;
    }

    public static double getSqAttackReach(LivingEntity entity, double sqBaseReach) {
        double reach = getAttackReach(entity, Math.sqrt(sqBaseReach));
        return reach * reach;
    }
}
