package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.skeagle.skeaglesmodstuff.block.DuckBlock;
import net.skeagle.skeaglesmodstuff.block.DuckCake;
import net.skeagle.skeaglesmodstuff.block.MilkFluidBlock;

import java.util.function.Function;

public class SMSBlocks {

    //fluid blocks
    public static final RegistryObject<Block> MILK_FLUID_BLOCK = register("milk_fluid_block",
            new MilkFluidBlock(SMSFluids.MILK, BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100.0F).noDrops()), null);

    //normal blocks
    public static final RegistryObject<Block> DUCK_BLOCK = register("duckblock", new DuckBlock());
    public static final RegistryObject<Block> DUCK_CAKE = register("duckcake", new DuckCake());
    public static final RegistryObject<Block> MILK_GRASS = register("milk_grass",
            new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));

    //decoration
    /*public static final RegistryObject<Block> WHITE_ROSE = register("white rose",
            new FlowerBlock(Effects.LEVITATION, 5, AbstractBlock.Properties.create(Material.PLANTS)
                    .doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));*/


    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        BlockItem item = new BlockItem(block, new Item.Properties().tab(SMSGroups.BLOCKS_TAB));
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
