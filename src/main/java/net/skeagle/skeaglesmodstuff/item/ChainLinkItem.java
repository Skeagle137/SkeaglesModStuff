package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.item.Item;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class ChainLinkItem extends Item {
    public ChainLinkItem() {
        super(new Item.Properties()
                .group(SMSMain.setup.itemGroup)
        );
        setRegistryName("chainlinkitem");
    }
}
