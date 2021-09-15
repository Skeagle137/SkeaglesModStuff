package net.skeagle.skeaglesmodstuff;

import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.skeagle.skeaglesmodstuff.fluid.MilkFluid;

public class SMSFluids {

    private static final ResourceLocation MILK_STILL = new ResourceLocation(SMSMain.MODID,"block/milk_still");
    private static final ResourceLocation MILK_FLOWING = new ResourceLocation(SMSMain.MODID,"block/milk_flow");
    private static final ResourceLocation MILK_OVERLAY = new ResourceLocation(SMSMain.MODID, "block/milk_overlay");

    public static RegistryObject<ForgeFlowingFluid> MILK = register("milk", new MilkFluid.Source(createMilkProperties()));
    public static RegistryObject<ForgeFlowingFluid> FLOWING_MILK = register("flowing_milk", new MilkFluid.Flowing(createMilkProperties()));

    private static ForgeFlowingFluid.Properties createMilkProperties() {
        return new ForgeFlowingFluid.Properties(MILK, FLOWING_MILK,
            FluidAttributes.builder(MILK_STILL, MILK_FLOWING).overlay(MILK_OVERLAY).color(0xFFFFFFF0).density(1000).luminosity(2))
                .bucket(SMSItems.ENCHANTED_MILK_BUCKET);
                //.block(SMSBlocks.MILK_FLUID_BLOCK);
    }

    private static <T extends Fluid> RegistryObject<T> register(String name, T fluid) {
        return Registry.FLUIDS.register(name, () -> fluid);
    }

    static void init() {}
}
