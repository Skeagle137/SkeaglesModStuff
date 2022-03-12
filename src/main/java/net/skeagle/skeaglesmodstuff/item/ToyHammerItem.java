package net.skeagle.skeaglesmodstuff.item;

import com.google.common.collect.Multimap;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.skeagle.skeaglesmodstuff.registry.SMSCreativeTabs;

import java.util.List;
import java.util.Random;

import static net.skeagle.skeaglesmodstuff.registry.SMSSounds.TOY_HAMMER;

public class ToyHammerItem extends Item {

    public ToyHammerItem() {
        super(new Item.Properties()
                .tab(SMSCreativeTabs.ITEMS_TAB)
        );
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        //if (!target.isAttackable() || target.hurtTime != target.hurtDuration) return false;
        Random r = target.getRandom();
        target.playSound(TOY_HAMMER.get(), 1f, (r.nextFloat() - r.nextFloat()) * 0.15f + 1f);
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flag) {
        list.add(new TranslatableComponent(ChatFormatting.RED + "Just a toy hammer. Or is it?"));
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
        return true;
    }
}
