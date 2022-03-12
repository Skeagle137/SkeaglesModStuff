package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {

    public RecipeGen(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected final void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(SMSBlocks.DUCK_BLOCK.get())
                .pattern("FFF")
                .pattern("F F")
                .pattern("FFF")
                .define('F', Tags.Items.FEATHERS)
                .unlockedBy("has_feather", has(Items.FEATHER))
                .save(consumer);
    }

    @Override
    public String getName() {
        return "SMS Recipes";
    }
}
