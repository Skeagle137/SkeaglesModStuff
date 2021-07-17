package net.skeagle.skeaglesmodstuff.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.storage.ServerWorldInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(ServerWorldInfo.class)
public class MixinServerWorldInfo {

    @Inject(at = @At("HEAD"), method = "getLifecycle", cancellable = true)
    private void forceStableLifeCycle(CallbackInfoReturnable<Lifecycle> ci) {
        if (SMSMain.DEV_MODE)
            ci.setReturnValue(Lifecycle.stable());
    }
}
