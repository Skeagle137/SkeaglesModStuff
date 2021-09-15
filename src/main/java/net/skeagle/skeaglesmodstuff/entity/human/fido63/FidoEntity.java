package net.skeagle.skeaglesmodstuff.entity.human.fido63;

import net.minecraft.world.entity.EntityType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.skeagle.skeaglesmodstuff.entity.human.SMSHumanEntity;

public class FidoEntity extends SMSHumanEntity {

    public FidoEntity(EntityType<? extends SMSHumanEntity> type, Level worldIn) {
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
