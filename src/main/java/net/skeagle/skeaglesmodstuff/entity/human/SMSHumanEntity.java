package net.skeagle.skeaglesmodstuff.entity.human;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

import java.util.Random;

public abstract class SMSHumanEntity extends PathfinderMob {

    protected SMSHumanEntity(EntityType<? extends SMSHumanEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    protected final void registerGoals() {
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        registerHumanGoals();
    }

    protected void registerHumanGoals() {

    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    public static boolean canSpawn(EntityType<? extends SMSHumanEntity> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) {
        return world.getRawBrightness(pos, 0) > 8;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    protected abstract SoundEvent getStepSound();

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (damageSourceIn == DamageSource.ON_FIRE) {
            return SoundEvents.PLAYER_HURT_ON_FIRE;
        } else if (damageSourceIn == DamageSource.DROWN) {
            return SoundEvents.PLAYER_HURT_DROWN;
        } else {
            return damageSourceIn == DamageSource.SWEET_BERRY_BUSH ? SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH : SoundEvents.PLAYER_HURT;
        }
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }
}
