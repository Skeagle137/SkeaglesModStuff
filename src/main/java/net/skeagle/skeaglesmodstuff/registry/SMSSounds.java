package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class SMSSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SMSMain.MODID);

    public static final RegistryObject<SoundEvent> MUSIC_DISC_CHUG_JUG = register("music_disc.chug_jug");
    public static final RegistryObject<SoundEvent> TOY_HAMMER = register("item.toy_hammer");
    public static final RegistryObject<SoundEvent> RIP = register("misc.rip");

    private static RegistryObject<SoundEvent> register(String id) {
        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(SMSMain.MODID, id)));
    }
}
