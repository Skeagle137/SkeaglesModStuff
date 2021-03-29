package net.skeagle.skeaglesmodstuff;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.skeagle.skeaglesmodstuff.fluid.MilkFluid;

public class SMSFluids {

    public static final RegistryObject<FlowingFluid> FLOWING_MILK = register("flowing_milk", new MilkFluid.Flowing());
    public static final RegistryObject<FlowingFluid> MILK = register("milk", new MilkFluid.Source());

    private static <T extends Fluid> RegistryObject<T> register(String name, T fluid) {
        return ModSetup.FLUIDS.register(name, () -> fluid);
    }

    static void init() {}
}
