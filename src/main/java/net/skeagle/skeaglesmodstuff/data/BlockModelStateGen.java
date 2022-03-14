package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;

public class BlockModelStateGen extends BlockStateProvider {

    public BlockModelStateGen(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, SMSMain.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.fluid(SMSBlocks.MILK_FLUID_BLOCK.get(), "milk_still");
        this.baseCube(SMSBlocks.DUCK_BLOCK.get(), modLoc("block/duckblock")).texture("north", modLoc("block/duckblock_front"));
        this.cake(SMSBlocks.DUCK_CAKE.get());
    }

    private void fluid(LiquidBlock block, String texture) {
        this.models().sign(block.getRegistryName().toString(), modLoc("block/" + texture));
        this.simpleBlock(block, new ModelFile.ExistingModelFile(modLoc("block/" + block.getRegistryName().getPath()), models().existingFileHelper));
    }

    private BlockModelBuilder baseCube(Block block, ResourceLocation location) {
        this.directionalBlock(block, new ModelFile.ExistingModelFile(modLoc("block/" + block.getRegistryName().getPath()), models().existingFileHelper));
        return models().getBuilder(block.getRegistryName().toString()).parent(new ModelFile.UncheckedModelFile("block/cube"))
                .texture("down", location)
                .texture("up", location)
                .texture("north", location)
                .texture("east", location)
                .texture("south", location)
                .texture("west", location);
    }

    private void cake(CakeBlock block) {
        VariantBlockStateBuilder.PartialBlockstate state = this.getVariantBuilder(block).partialState();
        for (int i = 0; i <= 6; ++i) {
            BlockModelBuilder builder = this.cakeTextures(block, block.getRegistryName().getPath(), "cake", null);
            if (i != 0) {
                builder = this.cakeTextures(block, "slice" + i, "cake_slice" + i, "inner");
            }
            state.with(BlockStateProperties.BITES, i).addModels(new ConfiguredModel(builder));
        }
    }

    private BlockModelBuilder cakeTextures(CakeBlock block, String path, String parent, String inner) {
        String s = "block/" + block.getRegistryName().getPath() + "/";
        BlockModelBuilder builder = models().withExistingParent("block/" + block.getRegistryName().getPath() + "/" + path, "block/" + parent)
                .texture("particle", s + "side")
                .texture("bottom", s + "bottom")
                .texture("top", s + "top")
                .texture("side", s + "side");
        return inner != null ? builder.texture("inner", s + "side") : builder;
    }

    @Override
    public String getName() {
        return "SMS Block Models and States";
    }
}
