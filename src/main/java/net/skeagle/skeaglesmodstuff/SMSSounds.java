package net.skeagle.skeaglesmodstuff;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class SMSSounds {

    public static final RegistryObject<SoundEvent> MUSIC_DISC_CHUGJUG = register("music_disc.chugjug");
    public static final RegistryObject<SoundEvent> TOY_HAMMER = register("item.toy_hammer");

    private static RegistryObject<SoundEvent> register(String id) {
        ResourceLocation rloc = new ResourceLocation(SMSMain.MODID, id);
        return ModSetup.SOUNDS.register(id, () -> new SoundEvent(rloc));
    }

    static void init() {}
}
