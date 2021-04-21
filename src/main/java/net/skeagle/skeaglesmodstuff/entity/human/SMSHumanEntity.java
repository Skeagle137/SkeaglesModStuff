package net.skeagle.skeaglesmodstuff.entity.human;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public abstract class SMSHumanEntity extends CreatureEntity {

    protected SMSHumanEntity(EntityType<? extends SMSHumanEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected final void registerGoals() {
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        registerhumanGoals();
    }

    protected abstract void registerhumanGoals();

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    public static boolean canSpawn(EntityType<? extends SMSHumanEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return world.getLightSubtracted(pos, 0) > 8;
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    protected abstract SoundEvent getStepSound();

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (damageSourceIn == DamageSource.ON_FIRE) {
            return SoundEvents.ENTITY_PLAYER_HURT_ON_FIRE;
        } else if (damageSourceIn == DamageSource.DROWN) {
            return SoundEvents.ENTITY_PLAYER_HURT_DROWN;
        } else {
            return damageSourceIn == DamageSource.SWEET_BERRY_BUSH ? SoundEvents.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH : SoundEvents.ENTITY_PLAYER_HURT;
        }
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }
}
