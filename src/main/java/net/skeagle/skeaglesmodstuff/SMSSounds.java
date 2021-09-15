package net.skeagle.skeaglesmodstuff;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

public class SMSSounds {

    public static final RegistryObject<SoundEvent> MUSIC_DISC_CHUGJUG = register("music_disc.chugjug");
    public static final RegistryObject<SoundEvent> TOY_HAMMER = register("item.toy_hammer");
    public static final RegistryObject<SoundEvent> RIP = register("sms_misc.rip");

    private static RegistryObject<SoundEvent> register(String id) {
        ResourceLocation rloc = new ResourceLocation(SMSMain.MODID, id);
        return Registry.SOUNDS.register(id, () -> new SoundEvent(rloc));
    }

    static void init() {}
}
