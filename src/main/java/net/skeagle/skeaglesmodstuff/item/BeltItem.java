package net.skeagle.skeaglesmodstuff.item;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.skeagle.skeaglesmodstuff.registry.SMSAttributes;
import net.skeagle.skeaglesmodstuff.registry.SMSCreativeTabs;

import java.util.List;
import java.util.UUID;

public class BeltItem extends Item {

    public BeltItem() {
        super(new Item.Properties()
                .tab(SMSCreativeTabs.ITEMS_TAB)
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 1000, AttributeModifier.Operation.ADDITION), slot);
            stack.addAttributeModifier(SMSAttributes.ATTACK_REACH.get(),
                    new AttributeModifier(UUID.fromString("3a56f29b-964b-49bc-9901-752fc5dbc328"), "Reach modifier", 100, AttributeModifier.Operation.ADDITION), slot);
        }
        return stack.getAttributeModifiers(slot);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flag) {
        list.add(new TextComponent(ChatFormatting.DARK_RED + "Nobody escapes the belt."));
    }
}
