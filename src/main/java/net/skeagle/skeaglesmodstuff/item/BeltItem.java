package net.skeagle.skeaglesmodstuff.item;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.skeagle.skeaglesmodstuff.SMSAttributes;
import net.skeagle.skeaglesmodstuff.SMSGroups;
import net.skeagle.skeaglesmodstuff.SMSNetwork;

import java.util.List;
import java.util.UUID;

public class BeltItem extends Item {

    public BeltItem() {
        super(new Item.Properties()
                .group(SMSGroups.ITEMS_TAB)
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        if (slot == EquipmentSlotType.MAINHAND) {
            stack.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 1000, AttributeModifier.Operation.ADDITION), slot);
            stack.addAttributeModifier(SMSAttributes.ATTACK_REACH.get(),
                    new AttributeModifier(UUID.fromString("3a56f29b-964b-49bc-9901-752fc5dbc328"), "Reach modifier", 100, AttributeModifier.Operation.ADDITION), slot);
        }
        return stack.getAttributeModifiers(slot);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent(TextFormatting.DARK_RED + "Nobody escapes the belt."));
    }
}
