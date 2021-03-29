package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.SMSFluids;
import net.skeagle.skeaglesmodstuff.SMSGroups;

import java.util.List;

public class EnchantedMilkBucketItem extends BucketItem {

    public EnchantedMilkBucketItem() {
        super(SMSFluids.MILK, new Item.Properties().containerItem(Items.BUCKET)
                .maxStackSize(1).group(SMSGroups.ITEMS_TAB).rarity(Rarity.RARE)
        );
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent(TextFormatting.BLUE + "Flowing Milk Pog"));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
