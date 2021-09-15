package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.fmllegacy.RegistryObject;

public class SMSPaintings {
    public static final RegistryObject<Motive> SANS_PAINTING = register("sans", 32, 32);
    public static final RegistryObject<Motive> DUCK_DAB_PAINTING = register("duck_dab", 32, 32);
    public static final RegistryObject<Motive> KARL_PAINTING = register("karl", 32, 32);

    private static RegistryObject<Motive> register(String name, int width, int height) {
        return Registry.PAINTINGS.register(name, () -> new Motive(width, height));
    }

    static void init() {}

}
