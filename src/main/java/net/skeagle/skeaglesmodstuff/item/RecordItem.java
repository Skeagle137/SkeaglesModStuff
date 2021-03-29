package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSGroups;

public class RecordItem extends MusicDiscItem {

    public RecordItem(int comparator, RegistryObject<SoundEvent> soundEvent) {
        super(comparator, soundEvent, new Item.Properties().maxStackSize(1).group(SMSGroups.ITEMS_TAB).rarity(Rarity.RARE));
        //setRegistryName("music_disc_" + name);
    }
}
