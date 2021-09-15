package net.skeagle.skeaglesmodstuff.entity.milkcube;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.skeagle.skeaglesmodstuff.SMSParticles;
import net.skeagle.skeaglesmodstuff.SMSTags;

import java.util.Random;

public class MilkCubeEntity extends Slime {
    public MilkCubeEntity(EntityType<? extends MilkCubeEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED,0.2F);
    }

    public static boolean canSpawn(EntityType<MilkCubeEntity> type, LevelAccessor world, MobSpawnType spawnType, BlockPos pos, Random rand) {
        return world.getDifficulty() != Difficulty.PEACEFUL;
    }

    protected void setSize(int size, boolean resetHealth) {
        super.setSize(size, resetHealth);
        this.getAttribute(Attributes.ARMOR).setBaseValue(size * 3);
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness() {
        return 1.05F;
    }

    protected ParticleOptions getParticleType() {
        return SMSParticles.MILK_SPLASH.get();
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay() {
        return super.getJumpDelay() * 4;
    }

    protected void decreaseSquish() {
        this.targetSquish *= 0.9F;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jumpFromGround() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, (this.getJumpPower() + (float)this.getSize() * 0.5F), vec3.z);
        this.hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
    }

    protected void jumpInLiquid(Tag<Fluid> fluid) {
        if (fluid == SMSTags.Fluids.MILK) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, (0.5F + (float)this.getSize() * 0.05F), vec3.z);
            this.hasImpulse = true;
        } else {
            super.jumpInLiquid(fluid);
        }
    }

    protected float getAttackDamage() {
        return super.getAttackDamage() + 2.0F;
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.MAGMA_CUBE_JUMP;
    }
}
