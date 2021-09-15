package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;

import java.util.function.Function;

public class SMSTags {

    private static <T> Tag.Named<T> getOrRegister(Function<ResourceLocation, Tag.Named<T>> tag, ResourceLocation loc) {
        return tag.apply(loc);
    }

    public static class Fluids {
        static void init() {
        }

        public static Tag.Named<Fluid> MILK = createTag("milk");

        private static Tag.Named<Fluid> createTag(String name) {
            return getOrRegister(loc -> FluidTags.bind(loc.toString()), new ResourceLocation(SMSMain.MODID, name));
        }
    }

    static void init() {
        Fluids.init();
    }
}
