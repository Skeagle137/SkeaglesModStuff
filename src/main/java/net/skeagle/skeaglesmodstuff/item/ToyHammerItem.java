package net.skeagle.skeaglesmodstuff.item;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.SMSGroups;

import java.util.List;
import java.util.Random;

import static net.skeagle.skeaglesmodstuff.SMSSounds.TOY_HAMMER;

public class ToyHammerItem extends Item {

    public ToyHammerItem() {
        super(new Item.Properties()
                .group(SMSGroups.ITEMS_TAB)
        );
        //setRegistryName("toy_hammer");
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.canBeAttackedWithItem() || target.hurtTime != target.maxHurtTime) return false;
        Random r = target.getRNG();
        target.playSound(TOY_HAMMER.get(), 1f, (r.nextFloat() - r.nextFloat()) * 0.15f + 1f);
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent(TextFormatting.RED + "Just a toy hammer. Or is it?"));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND)
            stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 1000, AttributeModifier.Operation.ADDITION), slot);
        return stack.getAttributeModifiers(slot);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
