package net.skeagle.skeaglesmodstuff;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class SMSTags {

    private static <T> ITag.INamedTag<T> getOrRegister(Function<ResourceLocation, ITag.INamedTag<T>> tag, ResourceLocation loc) {
        return tag.apply(loc);
    }

    public static class Fluids {
        static void init() {
        }

        public static ITag.INamedTag<Fluid> MILK = createTag("milk");

        private static ITag.INamedTag<Fluid> createTag(String name) {
            return getOrRegister(loc -> FluidTags.makeWrapperTag(loc.toString()), new ResourceLocation(SMSMain.MODID, name));
        }
    }

    static void init() {
        Fluids.init();
    }
}
