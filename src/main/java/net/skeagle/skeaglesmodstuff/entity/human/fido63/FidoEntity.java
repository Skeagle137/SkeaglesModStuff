package net.skeagle.skeaglesmodstuff.entity.human.fido63;

import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.skeagle.skeaglesmodstuff.entity.human.SMSHumanEntity;

public class FidoEntity extends SMSHumanEntity {

    public FidoEntity(EntityType<? extends SMSHumanEntity> type, World worldIn) {
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
