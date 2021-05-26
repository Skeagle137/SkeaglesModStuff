package net.skeagle.skeaglesmodstuff.entity.eduardo;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.skeagle.skeaglesmodstuff.SMSItems;

public class EduardoEntity extends CowEntity {

    public EduardoEntity(EntityType<? extends EduardoEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    @Override
    public EduardoEntity createChild(ServerWorld world, AgeableEntity mate) {
        return (EduardoEntity) this.getType().create(world);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public ActionResultType getEntityInteractionResult(PlayerEntity player, Hand hand) {
        ItemStack item = player.getHeldItem(hand);
        if (item.getItem() == Items.BUCKET && !this.isChild()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack item1 = DrinkHelper.fill(item, player, SMSItems.ENCHANTED_MILK_BUCKET.get().getDefaultInstance());
            player.setHeldItem(hand, item1);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            return super.getEntityInteractionResult(player, hand);
        }
    }
}
