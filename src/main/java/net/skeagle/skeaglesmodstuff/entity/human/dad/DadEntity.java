package net.skeagle.skeaglesmodstuff.entity.human.dad;

import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.entity.human.SMSHumanEntity;

public class DadEntity extends SMSHumanEntity {

    public DadEntity(EntityType<? extends SMSHumanEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerHumanGoals() {

    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }
}
