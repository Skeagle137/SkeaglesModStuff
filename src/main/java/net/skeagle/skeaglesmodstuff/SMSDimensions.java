package net.skeagle.skeaglesmodstuff;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;

public class SMSDimensions {

    public static final ResourceKey<DimensionType> MILK_DIM = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation("milk_dimension"));
}
