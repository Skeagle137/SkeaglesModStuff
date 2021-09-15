package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SMSGroups {

    public static CreativeModeTab BLOCKS_TAB = new CreativeModeTab("SMS: Blocks") {
        @Override @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(SMSBlocks.DUCK_BLOCK.get());
        }
    };

    public static CreativeModeTab ITEMS_TAB = new CreativeModeTab("SMS: Items") {
        @Override @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(SMSItems.BREAD_STICK_ITEM.get());
        }
    };
}
