package net.skeagle.skeaglesmodstuff.entity.human.dad;

import net.minecraft.world.entity.EntityType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.skeagle.skeaglesmodstuff.entity.human.SMSHumanEntity;

public class DadEntity extends SMSHumanEntity {

    public DadEntity(EntityType<? extends SMSHumanEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }
}
