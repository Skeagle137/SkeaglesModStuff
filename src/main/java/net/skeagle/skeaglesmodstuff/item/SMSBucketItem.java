package net.skeagle.skeaglesmodstuff.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.skeagle.skeaglesmodstuff.SMSGroups;

import java.util.List;
import java.util.function.Supplier;

public class SMSBucketItem<T extends Fluid> extends BucketItem {

    private final TextComponent hoverText;

    public SMSBucketItem(Supplier<T> fluid) {
        this(fluid, null);
    }

    public SMSBucketItem(Supplier<T> fluid, TextComponent hoverText) {
        super(fluid, new Item.Properties().stacksTo(1).tab(SMSGroups.ITEMS_TAB).rarity(Rarity.RARE).craftRemainder(Items.BUCKET));
        this.hoverText = hoverText;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flag) {
        if (hoverText != null) {
            list.add(hoverText);
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
