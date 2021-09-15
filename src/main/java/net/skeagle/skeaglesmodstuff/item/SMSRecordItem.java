package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.skeagle.skeaglesmodstuff.SMSGroups;

import java.util.function.Supplier;

public class SMSRecordItem extends RecordItem {

    public SMSRecordItem(int comparator, Supplier<SoundEvent> soundEvent) {
        super(comparator, soundEvent, new Item.Properties().stacksTo(1).tab(SMSGroups.ITEMS_TAB).rarity(Rarity.RARE));
    }
}
