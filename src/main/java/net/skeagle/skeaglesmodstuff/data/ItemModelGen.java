package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.registry.SMSItems;

public class ItemModelGen extends ItemModelProvider {

    public ItemModelGen(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, SMSMain.MODID, helper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> object : SMSItems.ITEMS.getEntries()) {
            Item item = object.get();
            if (item instanceof ForgeSpawnEggItem) {
                this.spawnEgg(item);
            }
        }
    }

    private ItemModelBuilder spawnEgg(Item item) {
        return withExistingParent(item.getRegistryName().toString(), "item/template_spawn_egg");
    }

    @Override
    public String getName() {
        return "SMS Item Models";
    }

}
