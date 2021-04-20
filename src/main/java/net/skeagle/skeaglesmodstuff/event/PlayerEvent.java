package net.skeagle.skeaglesmodstuff.event;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skeagle.skeaglesmodstuff.SMSBlocks;

@Mod.EventBusSubscriber
public class PlayerEvent {

    @SubscribeEvent
    public static void onRightClickMilk(PlayerInteractEvent.RightClickEmpty e) {
        if (e.getPlayer() == null || e.getHand() != Hand.MAIN_HAND) return;
        if (e.getItemStack() != ItemStack.EMPTY) return;
        RayTraceResult.Type type = getRayTraceResult(e.getPlayer()).getType();
        if (type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult ray = (BlockRayTraceResult) getRayTraceResult(e.getPlayer());
            Block b = e.getPlayer().world.getBlockState(ray.getPos()).getBlock();
            if (b != SMSBlocks.MILK_FLUID_BLOCK.get()) return;
            e.getWorld().playSound(e.getPlayer(), ray.getPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    private static RayTraceResult getRayTraceResult(PlayerEntity player) {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vector3d vector3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue();;
        Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return player.world.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE,
                RayTraceContext.FluidMode.SOURCE_ONLY, player));
    }
}
