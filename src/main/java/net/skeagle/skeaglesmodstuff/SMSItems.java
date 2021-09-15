package net.skeagle.skeaglesmodstuff;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.skeagle.skeaglesmodstuff.item.*;

public class SMSItems {

    public static final RegistryObject<Item> BELT_ITEM = register("beltitem", new BeltItem());
    public static final RegistryObject<Item> BREAD_STICK_ITEM = register("breadstickitem", new BreadStickItem());
    public static final RegistryObject<Item> MUSIC_DISC_CHUGJUG_ITEM = register("music_disc_chugjug", new SMSRecordItem(14, SMSSounds.MUSIC_DISC_CHUGJUG));
    public static final RegistryObject<Item> TOY_HAMMER_ITEM = register("toy_hammer", new ToyHammerItem());
    public static final RegistryObject<BucketItem> ENCHANTED_MILK_BUCKET = register("enchanted_milk_bucket",
            new SMSBucketItem<>(SMSFluids.MILK, new TextComponent(ChatFormatting.BLUE + "Flowing Milk Pog")));

    //spawn eggs
    public static final RegistryObject<Item> MILK_CUBE_SPAWN_EGG = register("milk_cube_spawn_egg", new SpawnEggItem(SMSEntities.MILK_CUBE_TYPE, 0xfcfcfc, 0xf5f1dc, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> FIDO_SPAWN_EGG = register("fido_spawn_egg", new SpawnEggItem(SMSEntities.FIDO_TYPE, 0x363636, 0x460b49, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> DAD_SPAWN_EGG = register("dad_spawn_egg", new SpawnEggItem(SMSEntities.DAD_TYPE, 0x4b362a, 0xf3f3f3, (new Item.Properties()).tab(CreativeModeTab.TAB_MISC)));

    private static <T extends Item> RegistryObject<T> register(String name, T item) {
        return Registry.ITEMS.register(name, () -> item);
    }

    static void init() {}
}
