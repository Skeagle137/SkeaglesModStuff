package net.skeagle.skeaglesmodstuff.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

@Mod.EventBusSubscriber
public class PlayerEvent {

    @SubscribeEvent
    public static void onRightClickMilk(PlayerInteractEvent.RightClickEmpty e) {
        if (e.getPlayer() == null || e.getHand() != InteractionHand.MAIN_HAND) return;
        if (e.getItemStack() != ItemStack.EMPTY) return;
        HitResult.Type type = getRayTraceResult(e.getPlayer()).getType();
        if (type == HitResult.Type.BLOCK) {
            BlockHitResult ray = (BlockHitResult) getRayTraceResult(e.getPlayer());
            Block b = e.getPlayer().level.getBlockState(ray.getBlockPos()).getBlock();
            if (b != SMSBlocks.MILK_FLUID_BLOCK.get()) return;
            e.getWorld().playSound(e.getPlayer(), ray.getBlockPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    private static HitResult getRayTraceResult(Player player) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vector3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue();
        Vec3 vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return player.level.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE,
                ClipContext.Fluid.SOURCE_ONLY, player));
    }
}
