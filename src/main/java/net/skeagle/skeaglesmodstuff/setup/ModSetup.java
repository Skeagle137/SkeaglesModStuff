package net.skeagle.skeaglesmodstuff.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.skeagle.skeaglesmodstuff.Block.ModBlocks;
import net.skeagle.skeaglesmodstuff.item.ModItems;

public class ModSetup {

    public ItemGroup blockGroup = new ItemGroup("smodstuff") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.DUCKBLOCK);
        }
    };

    public ItemGroup itemGroup = new ItemGroup("smodstuff") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.BREADSTICKITEM);
        }
    };

    public void init() {

    }
}
