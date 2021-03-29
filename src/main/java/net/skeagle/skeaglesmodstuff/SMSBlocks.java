package net.skeagle.skeaglesmodstuff;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.skeagle.skeaglesmodstuff.block.DuckBlock;
import net.skeagle.skeaglesmodstuff.block.DuckCake;
import net.skeagle.skeaglesmodstuff.block.MilkFluidBlock;

import java.util.function.Function;

public class SMSBlocks {

    public static final RegistryObject<Block> DUCK_BLOCK = register("duckblock", new DuckBlock());
    public static final RegistryObject<Block> DUCK_CAKE = register("duckcake", new DuckCake());
    public static final RegistryObject<FlowingFluidBlock> MILK_FLUID_BLOCK = register("milk_fluid_block", new MilkFluidBlock(), null);

    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(SMSGroups.BLOCKS_TAB));
        ModSetup.ITEMS.register(name, () -> item);
        return ModSetup.BLOCKS.register(name, () -> block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, T block, Function<T, BlockItem> item) {
        if (item != null)
            ModSetup.ITEMS.register(name, () -> item.apply(block));
        return ModSetup.BLOCKS.register(name, () -> block);
    }

    static void init() {}
}
