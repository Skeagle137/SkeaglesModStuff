package net.skeagle.skeaglesmodstuff.item;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.skeagle.skeaglesmodstuff.SMSGroups;

public class BreadStickItem extends Item {
    public BreadStickItem() {
        super(new Item.Properties()
                .tab(SMSGroups.ITEMS_TAB)
                .food(new FoodProperties.Builder()
                        .saturationMod(1f)
                        .nutrition(20)
                        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 20, true, true), 1f)
                        .build())
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND)
            stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 1000, AttributeModifier.Operation.ADDITION), slot);
        return stack.getAttributeModifiers(slot);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack);
    }
}
