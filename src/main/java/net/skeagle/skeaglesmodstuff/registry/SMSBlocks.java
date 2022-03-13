package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.block.DuckBlock;
import net.skeagle.skeaglesmodstuff.block.DuckCake;
import net.skeagle.skeaglesmodstuff.block.MilkFluidBlock;
import net.skeagle.skeaglesmodstuff.utils.DropLoot;
import net.skeagle.skeaglesmodstuff.utils.ItemModel;
import net.skeagle.skeaglesmodstuff.utils.MakeBlockItem;

public class SMSBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SMSMain.MODID);

    //fluid blocks
    public static final RegistryObject<Block> MILK_FLUID_BLOCK = BLOCKS.register("milk_fluid_block",
            () -> new MilkFluidBlock(SMSFluids.MILK, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));

    //normal blocks
    @MakeBlockItem @DropLoot
    public static final RegistryObject<Block> DUCK_BLOCK = BLOCKS.register("duckblock", DuckBlock::new);
    @ItemModel
    public static final RegistryObject<Block> DUCK_CAKE = BLOCKS.register("duckcake", DuckCake::new);
    //@MakeBlockItem
    public static final RegistryObject<Block> MILK_GRASS = BLOCKS.register("milk_grass",
            () -> new TallGrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
    //@MakeBlockItem
    public static final RegistryObject<Block> MILK_GRASS_BLOCK = BLOCKS.register("milk_grass_block",
            () -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)));

    //decoration
    /*(public static final RegistryObject<Block> WHITE_ROSE = register("white rose",
            new FlowerBlock(Effects.LEVITATION, 5, AbstractBlock.Properties.create(Material.PLANTS)
                    .doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));*/
}
