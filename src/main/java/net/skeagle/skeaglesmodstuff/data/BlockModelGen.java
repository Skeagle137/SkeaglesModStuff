package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;

public class BlockModelGen extends BlockModelProvider {

    public BlockModelGen(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, SMSMain.MODID, helper);
    }

    @Override
    protected void registerModels() {
        this.fluid(SMSBlocks.MILK_FLUID_BLOCK.get(), "milk_still");
        this.baseCube(SMSBlocks.DUCK_BLOCK.get(), modLoc("block/duckblock")).texture("north", modLoc("block/duckblock_front"));
        this.cake(SMSBlocks.DUCK_CAKE.get());
    }

    private void fluid(LiquidBlock block, String texture) {
        sign(block.getRegistryName().toString(), modLoc("block/" + texture));
    }

    private BlockModelBuilder baseCube(Block block, ResourceLocation location) {
        return getBuilder(block.getRegistryName().toString()).parent(new ModelFile.UncheckedModelFile("block/cube"))
                .texture("down", location)
                .texture("up", location)
                .texture("north", location)
                .texture("east", location)
                .texture("south", location)
                .texture("west", location);
    }

    private void cake(CakeBlock block) {
        this.cakeTextures(block, block.getRegistryName().getPath(), "cake", "side", "bottom", "top", null);
        for (int i = 1; i <= 6; ++i) {
            this.cakeTextures(block, "slice" + i, "cake_slice" + i, "side", "bottom", "top", "inner");
        }
    }

    private BlockModelBuilder cakeTextures(CakeBlock block, String path, String parent, String side, String bottom, String top, String inner) {
        String s = "block/" + block.getRegistryName().getPath() + "/";
        BlockModelBuilder builder = withExistingParent("block/" + block.getRegistryName().getPath() + "/" + path, "block/" + parent)
                .texture("particle", s + side)
                .texture("bottom", s + bottom)
                .texture("top", s + top)
                .texture("side", s + side);
        return inner != null ? builder.texture("inner", s + side) : builder;
    }

    @Override
    public String getName() {
        return "SMS Block Models";
    }


}
