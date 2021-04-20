package net.skeagle.skeaglesmodstuff.entity.human.fido63;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import javax.annotation.Nullable;

public class SMSHumanEntity extends LivingEntity {
    public static final EntitySize STANDING_SIZE = EntitySize.flexible(0.6F, 1.8F);
    protected static final DataParameter<Byte> PLAYER_MODEL_FLAG = EntityDataManager.createKey(PlayerEntity.class, DataSerializers.BYTE);
    public double prevChasingPosX;
    public double prevChasingPosY;
    public double prevChasingPosZ;
    public double chasingPosX;
    public double chasingPosY;
    public double chasingPosZ;
    private ItemStack itemStackMainHand = ItemStack.EMPTY;
    @Nullable private Pose forcedPose;
}
