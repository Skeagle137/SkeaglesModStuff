package net.skeagle.skeaglesmodstuff;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SMSGroups {

    public static ItemGroup BLOCKS_TAB = new ItemGroup("SMS: Blocks") {
        @Override @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(SMSBlocks.DUCK_BLOCK.get());
        }
    };

    public static ItemGroup ITEMS_TAB = new ItemGroup("SMS: Items") {
        @Override @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(SMSItems.BREAD_STICK_ITEM.get());
        }
    };
}
