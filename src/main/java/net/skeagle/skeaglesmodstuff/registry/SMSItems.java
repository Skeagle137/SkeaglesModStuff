package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.SMSMain;
import net.skeagle.skeaglesmodstuff.item.*;

public class SMSItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SMSMain.MODID);

    public static final RegistryObject<Item> BREAD_STICK_ITEM = ITEMS.register("breadstickitem", BreadStickItem::new);
    public static final RegistryObject<Item> MUSIC_DISC_CHUGJUG_ITEM = ITEMS.register("music_disc_chugjug", () -> new SMSRecordItem(15, SMSSounds.MUSIC_DISC_CHUG_JUG));
    public static final RegistryObject<Item> TOY_HAMMER_ITEM = ITEMS.register("toy_hammer", ToyHammerItem::new);
    public static final RegistryObject<BucketItem> ENCHANTED_MILK_BUCKET = ITEMS.register("enchanted_milk_bucket",
            () -> new SMSBucketItem<>(SMSFluids.MILK, new TextComponent(ChatFormatting.BLUE + "Flowing Milk Pog")));

    //spawn eggs
    public static final RegistryObject<Item> MILK_CUBE_SPAWN_EGG = ITEMS.register("milk_cube_spawn_egg",
            () -> new ForgeSpawnEggItem(SMSEntities.MILK_CUBE, 0xfcfcfc, 0xf5f1dc, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> FIDO_SPAWN_EGG = ITEMS.register("fido_spawn_egg",
            () -> new ForgeSpawnEggItem(SMSEntities.FIDO, 0x363636, 0x460b49, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> DAD_SPAWN_EGG = ITEMS.register("dad_spawn_egg",
            () -> new ForgeSpawnEggItem(SMSEntities.DAD, 0x4b362a, 0xf3f3f3, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
}
