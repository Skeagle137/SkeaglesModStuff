package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.registry.SMSTags;
import net.skeagle.skeaglesmodstuff.registry.SMSFluids;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class FluidTagGen extends FluidTagsProvider {

    public FluidTagGen(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, SMSMain.MODID, helper);
    }

    @Override
    protected void addTags() {
        this.tags(List.of(SMSTags.MILK, FluidTags.WATER), SMSFluids.MILK, SMSFluids.FLOWING_MILK);
    }

    @SafeVarargs
    private <T extends Fluid> void tags(List<TagKey<Fluid>> tags, Supplier<T>... fluids) {
        tags.forEach(tag -> Arrays.stream(fluids).map(Supplier::get).forEach(this.tag(tag)::add));
    }

    @Override
    public String getName() {
        return "SMS Fluid Tags";
    }
}
