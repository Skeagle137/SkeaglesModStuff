package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class SMSCreativeTabs {

    public static CreativeModeTab BLOCKS_TAB = new CreativeModeTab(SMSMain.MODID + ".blocks") {

        @Override
        public CreativeModeTab setRecipeFolderName(String pRecipeFolderName) {
            return super.setRecipeFolderName(SMSMain.MODID);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SMSBlocks.DUCK_BLOCK.get());
        }
    };

    public static CreativeModeTab ITEMS_TAB = new CreativeModeTab(SMSMain.MODID + ".items") {
        @Override
        public CreativeModeTab setRecipeFolderName(String pRecipeFolderName) {
            return super.setRecipeFolderName(SMSMain.MODID);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SMSItems.BREAD_STICK_ITEM.get());
        }
    };
}
