package net.skeagle.skeaglesmodstuff;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;

public class SMSPaintings {
    public static final RegistryObject<PaintingType> SANS_PAINTING = register("sans", 32, 32);
    public static final RegistryObject<PaintingType> DUCK_DAB_PAINTING = register("duck_dab", 32, 32);
    public static final RegistryObject<PaintingType> KARL_PAINTING = register("karl", 32, 32);

    private static RegistryObject<PaintingType> register(String name, int width, int height) {
        return Registry.PAINTINGS.register(name, () -> new PaintingType(width, height));
    }

    static void init() {}

}
