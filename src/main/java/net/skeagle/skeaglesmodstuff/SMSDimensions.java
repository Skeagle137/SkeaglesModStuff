package net.skeagle.skeaglesmodstuff;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;

public class SMSDimensions {

    public static final RegistryKey<DimensionType> MILK_DIM = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation("milk_dimension"));
}
