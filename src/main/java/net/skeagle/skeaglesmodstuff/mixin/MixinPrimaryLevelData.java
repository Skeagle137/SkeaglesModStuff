package net.skeagle.skeaglesmodstuff.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.storage.PrimaryLevelData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(PrimaryLevelData.class)
public class MixinPrimaryLevelData {

    @Inject(at = @At("HEAD"), method = "worldGenSettingsLifecycle", cancellable = true)
    private void forceStableLifeCycle(CallbackInfoReturnable<Lifecycle> ci) {
        if (SMSMain.DEV_MODE)
            ci.setReturnValue(Lifecycle.stable());
    }
}
