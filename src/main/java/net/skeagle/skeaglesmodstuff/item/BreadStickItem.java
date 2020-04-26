package net.skeagle.skeaglesmodstuff.item;


import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
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
                        .effect(new EffectInstance(Effects.SPEED, 120, 20, true, true), 1f)
                        .build())
        );
        setRegistryName("breadstickitem");


    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);

        if (slot == EquipmentSlotType.MAINHAND) {
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "generic.attackDamage", 999, AttributeModifier.Operation.ADDITION));
        }

        return modifiers;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
