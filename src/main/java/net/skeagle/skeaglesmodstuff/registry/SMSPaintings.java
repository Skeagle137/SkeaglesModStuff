package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class SMSPaintings {

    public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, SMSMain.MODID);

    public static final RegistryObject<Motive> SANS_PAINTING = register("sans", 32, 32);
    public static final RegistryObject<Motive> DUCK_DAB_PAINTING = register("duck_dab", 32, 32);
    public static final RegistryObject<Motive> KARL_PAINTING = register("karl", 32, 32);

    private static RegistryObject<Motive> register(String name, int width, int height) {
        return PAINTINGS.register(name, () -> new Motive(width, height));
    }
}
