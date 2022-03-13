package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;
import net.skeagle.skeaglesmodstuff.registry.SMSCreativeTabs;
import net.skeagle.skeaglesmodstuff.utils.ItemModel;
import net.skeagle.skeaglesmodstuff.utils.MakeBlockItem;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistrationHelper {

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> e) {
        for (Field field : SMSBlocks.class.getFields()) {
            try {
                if (field.isAnnotationPresent(MakeBlockItem.class) || field.isAnnotationPresent(ItemModel.class)) {
                    Block block = ((RegistryObject<Block>) field.get(null)).get();
                    e.getRegistry().register(new BlockItem(block, new Item.Properties().tab(SMSCreativeTabs.BLOCKS_TAB)).setRegistryName(block.getRegistryName()));
                }
            }
            catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
}
