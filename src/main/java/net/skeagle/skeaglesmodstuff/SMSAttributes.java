package net.skeagle.skeaglesmodstuff;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;

public class SMSAttributes {

    public static final RegistryObject<Attribute> ATTACK_REACH = Registry.ATTRIBUTES.register("attack_reach",
            () -> new RangedAttribute("generic.attackReach", 3.0D, 0.0D, 1024.0D).setShouldWatch(true));

    static void init() {}
}
