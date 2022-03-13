package net.skeagle.skeaglesmodstuff.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;
import net.skeagle.skeaglesmodstuff.registry.SMSItems;
import net.skeagle.skeaglesmodstuff.utils.*;

import java.lang.reflect.Field;

public class ItemModelGen extends ItemModelProvider {

    public ItemModelGen(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, SMSMain.MODID, helper);
    }

    @Override
    protected void registerModels() {
        Item item;
        for (Field field : SMSItems.class.getFields()) {
            try {
                if (!(field.get(null) instanceof RegistryObject)) {
                    continue;
                }
                item = ((RegistryObject<Item>) field.get(null)).get();
                if (item instanceof ForgeSpawnEggItem) {
                    this.spawnEgg(item);
                }
                if (field.isAnnotationPresent(ItemModel.class)) {
                    fieldToItem(field, item.getRegistryName());
                }
            }
            catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        Block block;
        for (Field field : SMSBlocks.class.getFields()) {
            try {
                if (field.isAnnotationPresent(ItemModel.class)) {
                    block = ((RegistryObject<Block>) field.get(null)).get();
                    fieldToItem(field, block.getRegistryName());
                    continue;
                }
                if (field.isAnnotationPresent(MakeBlockItem.class)) {
                    block = ((RegistryObject<Block>) field.get(null)).get();
                    withExistingParent(block.getRegistryName().toString(), modLoc("block/" + block.getRegistryName().getPath()));
                }
            }
            catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    private ItemModelBuilder spawnEgg(Item item) {
        return baseExisting(item, "item/template_spawn_egg");
    }

    private ItemModelBuilder baseExisting(Item item, String parent) {
        return withExistingParent(item.getRegistryName().toString(), parent);
    }

    private ItemModelBuilder fieldToItem(Field field, ResourceLocation registryName) {
        boolean handheld = field.getAnnotation(ItemModel.class).value() == ItemModel.ItemModelType.HANDHELD;
        String path = field.isAnnotationPresent(TexturePath.class) ? field.getAnnotation(TexturePath.class).value() : null;
        return withExistingParent(registryName.toString(), "item/" + (handheld ? "handheld" : "generated"))
                .texture("layer0", path != null ? mcLoc("item/" + path) : modLoc("item/" + registryName.getPath()));
    }

    @Override
    public String getName() {
        return "SMS Item Models";
    }

}
