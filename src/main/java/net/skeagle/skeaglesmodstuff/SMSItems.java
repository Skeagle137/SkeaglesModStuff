package net.skeagle.skeaglesmodstuff;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.skeagle.skeaglesmodstuff.item.*;

import static net.skeagle.skeaglesmodstuff.SMSSounds.MUSIC_DISC_CHUGJUG;

public class SMSItems {

    public static final RegistryObject<Item> BELT_ITEM = register("beltitem", new BeltItem());
    public static final RegistryObject<Item> BREAD_STICK_ITEM = register("breadstickitem", new BreadStickItem());
    public static final RegistryObject<Item> MUSIC_DISC_CHUGJUG_ITEM = register("music_disc_chugjug", new RecordItem(14, MUSIC_DISC_CHUGJUG));
    public static final RegistryObject<Item> TOY_HAMMER_ITEM = register("toy_hammer", new ToyHammerItem());
    public static final RegistryObject<Item> ENCHANTED_MILK_BUCKET = register("enchanted_milk_bucket", new EnchantedMilkBucketItem());

    //spawn eggs
    public static final RegistryObject<Item> MILK_CUBE_SPAWN_EGG = register("milk_cube_spawn_egg", new SpawnEggItem(SMSEntities.MILK_CUBE_TYPE, 0xfcfcfc, 0xf5f1dc, (new Item.Properties()).group(ItemGroup.MISC)));
    public static final RegistryObject<Item> FIDO_SPAWN_EGG = register("fido_spawn_egg", new SpawnEggItem(SMSEntities.FIDO_TYPE, 0x363636, 0x460b49, (new Item.Properties()).group(ItemGroup.MISC)));
    public static final RegistryObject<Item> DAD_SPAWN_EGG = register("dad_spawn_egg", new SpawnEggItem(SMSEntities.DAD_TYPE, 0x4b362a, 0xf3f3f3, (new Item.Properties()).group(ItemGroup.MISC)));

    private static RegistryObject<Item> register(String name, Item item) {
        return Registry.ITEMS.register(name, () -> item);
    }

    static void init() {}
}
