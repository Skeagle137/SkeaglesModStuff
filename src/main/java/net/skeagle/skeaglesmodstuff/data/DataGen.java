package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.skeagle.skeaglesmodstuff.SMSMain;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();

        gen.addProvider(new RecipeGen(gen));
        gen.addProvider(new FluidTagGen(gen, helper));
        gen.addProvider(new BlockLootGen(gen));
        gen.addProvider(new BlockModelGen(gen, helper));

        if (e.includeClient()) {
            gen.addProvider(new ItemModelGen(gen, helper));
            gen.addProvider(new SoundGen(gen, helper));
        }
    }

}
