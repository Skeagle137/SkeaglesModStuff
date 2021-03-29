package net.skeagle.skeaglesmodstuff;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.skeagle.skeaglesmodstuff.item.*;

import static net.skeagle.skeaglesmodstuff.SMSSounds.MUSIC_DISC_CHUGJUG;

public class SMSItems {

    public static final RegistryObject<Item> BELT_ITEM = register("beltitem", new BeltItem());
    public static final RegistryObject<Item> BREAD_STICK_ITEM = register("breadstickitem", new BreadStickItem());
    public static final RegistryObject<Item> MUSIC_DISC_CHUGJUG_ITEM = register("music_disc_chugjug", new RecordItem(14, MUSIC_DISC_CHUGJUG));
    public static final RegistryObject<Item> TOY_HAMMER_ITEM = register("toy_hammer", new ToyHammerItem());
    public static final RegistryObject<Item> ENCHANTED_MILK_BUCKET = register("enchanted_milk_bucket", new EnchantedMilkBucketItem());

    private static RegistryObject<Item> register(String name, Item item) {
        return ModSetup.ITEMS.register(name, () -> item);
    }

    static void init() {}
}
