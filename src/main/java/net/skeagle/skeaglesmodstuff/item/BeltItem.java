package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.item.Item;
import net.skeagle.skeaglesmodstuff.SMSGroups;

public class BeltItem extends Item {

    public BeltItem() {
        super(new Item.Properties()
                .group(SMSGroups.ITEMS_TAB)
        );
        //setRegistryName("beltitem");
    }
}
