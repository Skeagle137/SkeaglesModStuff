package net.skeagle.skeaglesmodstuff.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.skeagle.skeaglesmodstuff.SMSBlockReg;
import net.skeagle.skeaglesmodstuff.SMSItemReg;

public class ModSetup {

    public ItemGroup blockGroup = new ItemGroup("smodstuff") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SMSBlockReg.DUCKBLOCK);
        }
    };

    public ItemGroup itemGroup = new ItemGroup("smodstuff") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SMSItemReg.BREADSTICKITEM);
        }
    };

    public void init() {

    }
}
