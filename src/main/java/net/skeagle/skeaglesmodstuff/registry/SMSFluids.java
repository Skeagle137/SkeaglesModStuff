package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.fluid.MilkFluid;

public class SMSFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SMSMain.MODID);

    public static RegistryObject<FlowingFluid> MILK = FLUIDS.register("milk", MilkFluid.Source::new);
    public static RegistryObject<FlowingFluid> FLOWING_MILK = FLUIDS.register("flowing_milk", MilkFluid.Flowing::new);
}
