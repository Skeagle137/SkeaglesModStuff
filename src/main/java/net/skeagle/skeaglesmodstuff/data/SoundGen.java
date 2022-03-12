package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.registry.SMSSounds;

import java.util.function.Supplier;

public class SoundGen extends SoundDefinitionsProvider {

    protected SoundGen(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, SMSMain.MODID, helper);
    }

    @Override
    public void registerSounds() {
        this.disc(SMSSounds.MUSIC_DISC_CHUG_JUG, "chug_jug");
        this.standardSound(SMSSounds.TOY_HAMMER, "item/toy_hammer", "subtitles.item.toy_hammer");
        this.standardSound(SMSSounds.RIP, "misc/rip", "subtitles.misc.rip");
    }

    private void disc(Supplier<SoundEvent> sound, String name) {
        this.baseSound(sound, "music_disc/" + name, true, null);
    }

    private void standardSound(Supplier<SoundEvent> sound, String name) {
        this.standardSound(sound, name,null);
    }

    private void standardSound(Supplier<SoundEvent> sound, String name, String subtitle) {
        this.baseSound(sound, name, false, subtitle);
    }

    private void baseSound(Supplier<SoundEvent> sound, String name, boolean stream, String subtitle) {
        SoundDefinition definition = SoundDefinition.definition().with(sound(SMSMain.MODID + ":" + name).stream(stream));
        this.add(sound, subtitle != null ? definition.subtitle(subtitle) : definition);
    }

    @Override
    public String getName()
    {
        return "SMS Sounds";
    }
}
