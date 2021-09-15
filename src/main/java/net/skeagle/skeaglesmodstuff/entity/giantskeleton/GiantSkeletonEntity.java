package net.skeagle.skeaglesmodstuff.entity.giantskeleton;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

public class GiantSkeletonEntity extends Skeleton {

    public GiantSkeletonEntity(EntityType<? extends GiantSkeletonEntity> entity, Level world) {
        super(entity, world);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 50.0D);
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader world) {
        return world.dimensionType().brightness(world.getLightEmission(pos)) - 0.5F;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 20.88F;
    }
}
