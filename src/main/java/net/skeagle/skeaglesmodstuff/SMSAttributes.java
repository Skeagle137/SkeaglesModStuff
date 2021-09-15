package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fmllegacy.RegistryObject;

public class SMSAttributes {

    public static final RegistryObject<Attribute> ATTACK_REACH = Registry.ATTRIBUTES.register("attack_reach",
            () -> new RangedAttribute("generic.attackReach", 0.0D, 0.0D, 1024.0D).setSyncable(true));

    static void init() {}
}
