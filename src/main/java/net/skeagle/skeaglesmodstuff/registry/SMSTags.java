package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class SMSTags {

    //fluid tags
    public static TagKey<Fluid> MILK = FluidTags.create(new ResourceLocation(SMSMain.MODID, "milk"));
}
