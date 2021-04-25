package net.skeagle.skeaglesmodstuff.entity.giantskeleton;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class GiantSkeletonEntity extends SkeletonEntity {

    public GiantSkeletonEntity(EntityType<? extends GiantSkeletonEntity> entity, World world) {
        super(entity, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 100.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 50.0D);
    }

    public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
        return world.getDimensionType().getAmbientLight(world.getLight(pos)) - 0.5F;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 20.88F;
    }


}
