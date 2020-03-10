package net.skeagle.skeaglesmodstuff.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.skeagle.skeaglesmodstuff.Block.ModBlocks;

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
            return new ItemStack(ModBlocks.DUCKBLOCK);
        }
    };

    public void init() {

    }
}
