package net.skeagle.skeaglesmodstuff.item;


import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.skeagle.skeaglesmodstuff.SMSMain;

public class BreadStickItem extends Item {
    public BreadStickItem() {
        super(new Item.Properties()
                .group(SMSMain.setup.itemGroup)
                .food(new Food.Builder()
                        .saturation(1f)
                        .hunger(20)
                        .effect(() -> new EffectInstance(Effects.SPEED, 120, 20, true, true), 1f)
                        .build())
        );
        setRegistryName("breadstickitem");


    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND) {
            stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "generic.attack_damage",
                            1000, AttributeModifier.Operation.ADDITION), slot);
        }

        return stack.getAttributeModifiers(slot);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
