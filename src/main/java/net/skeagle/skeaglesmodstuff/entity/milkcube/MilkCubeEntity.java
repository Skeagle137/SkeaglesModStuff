package net.skeagle.skeaglesmodstuff.entity.milkcube;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.loot.LootTables;
import net.minecraft.particles.IParticleData;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.SMSParticles;

import java.util.Random;

public class MilkCubeEntity extends SlimeEntity {
    public MilkCubeEntity(EntityType<? extends MilkCubeEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        //registerAttributes() equivalent in MonsterEntity
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MOVEMENT_SPEED,0.2F);
    }

    public static boolean canSpawn(EntityType<MilkCubeEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return world.getDifficulty() != Difficulty.PEACEFUL;
    }

    public boolean isNotColliding(IWorldReader worldIn) {
        return worldIn.checkNoEntityCollision(this) && !worldIn.containsAnyLiquid(this.getBoundingBox());
    }

    protected void setSlimeSize(int size, boolean resetHealth) {
        super.setSlimeSize(size, resetHealth);
        this.getAttribute(Attributes.ARMOR).setBaseValue(size * 3);
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness() {
        return 1.05F;
    }

    protected IParticleData getSquishParticle() {
        return SMSParticles.MILK_SPLASH.get();
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning() {
        return false;
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay() {
        return super.getJumpDelay() * 4;
    }

    protected void alterSquishAmount() {
        this.squishAmount *= 0.9F;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump() {
        this.setMotion(getMotion().x, this.getJumpUpwardsMotion() + ((float)this.getSlimeSize() * 0.1F), getMotion().z);
        this.isAirBorne = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
    }

    protected void handleFluidJump(ITag<Fluid> fluidTag) {
        if (fluidTag == FluidTags.LAVA) {
            this.setMotion(getMotion().x, 0.22F + ((float)this.getSlimeSize() * 0.05F), getMotion().z);
            this.isAirBorne = true;
        }
        else
            super.handleFluidJump(fluidTag);

    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected boolean canDamagePlayer() {
        return this.isServerWorld();
    }

    protected float func_225512_er_() {
        return super.func_225512_er_() + 0.8F;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return this.isSmallSlime() ? SoundEvents.ENTITY_MAGMA_CUBE_HURT_SMALL : SoundEvents.ENTITY_MAGMA_CUBE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isSmallSlime() ? SoundEvents.ENTITY_MAGMA_CUBE_DEATH_SMALL : SoundEvents.ENTITY_MAGMA_CUBE_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return this.isSmallSlime() ? SoundEvents.ENTITY_MAGMA_CUBE_SQUISH_SMALL : SoundEvents.ENTITY_MAGMA_CUBE_SQUISH;
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_MAGMA_CUBE_JUMP;
    }
}
