package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.skeagle.skeaglesmodstuff.registry.SMSCreativeTabs;

import java.util.function.Supplier;

public class SMSRecordItem extends RecordItem {

    public SMSRecordItem(int comparator, Supplier<SoundEvent> soundEvent) {
        super(comparator, soundEvent, new Item.Properties().stacksTo(1).tab(SMSCreativeTabs.ITEMS_TAB).rarity(Rarity.RARE));
    }
}
