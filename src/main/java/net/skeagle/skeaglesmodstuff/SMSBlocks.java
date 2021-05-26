package net.skeagle.skeaglesmodstuff;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.skeagle.skeaglesmodstuff.block.DuckBlock;
import net.skeagle.skeaglesmodstuff.block.DuckCake;
import net.skeagle.skeaglesmodstuff.block.MilkFluidBlock;

import java.util.function.Function;

public class SMSBlocks {

    //fluid blocks
    public static final RegistryObject<FlowingFluidBlock> MILK_FLUID_BLOCK = register("milk_fluid_block",
            new FlowingFluidBlock(SMSFluids.MILK, AbstractBlock.Properties.create(Material.WATER)
                .doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()), null);

    //normal blocks
    public static final RegistryObject<Block> DUCK_BLOCK = register("duckblock", new DuckBlock());
    public static final RegistryObject<Block> DUCK_CAKE = register("duckcake", new DuckCake());
    public static final RegistryObject<Block> MILK_GRASS = register("milk_grass",
            new GrassBlock(AbstractBlock.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));

    //decoration
    /*public static final RegistryObject<Block> WHITE_ROSE = register("white rose",
            new FlowerBlock(Effects.LEVITATION, 5, AbstractBlock.Properties.create(Material.PLANTS)
                    .doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));*/


    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(SMSGroups.BLOCKS_TAB));
        Registry.ITEMS.register(name, () -> item);
        return Registry.BLOCKS.register(name, () -> block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, T block, Function<T, BlockItem> item) {
        if (item != null)
            Registry.ITEMS.register(name, () -> item.apply(block));
        return Registry.BLOCKS.register(name, () -> block);
    }

    static void init() {}
}
