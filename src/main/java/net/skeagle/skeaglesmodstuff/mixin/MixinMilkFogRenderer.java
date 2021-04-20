package net.skeagle.skeaglesmodstuff.mixin;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(FogRenderer.class)
public class MixinMilkFogRenderer {

    @Shadow private static long waterFogUpdateTime;
    @Shadow private static float red;
    @Shadow private static float green;
    @Shadow private static float blue;

    @Inject(at = @At("RETURN"), method = "updateFogColor(Lnet/minecraft/client/renderer/ActiveRenderInfo;FLnet/minecraft/client/world/ClientWorld;IF)V")
    private static void updateFogColor(ActiveRenderInfo activeRenderInfoIn, float partialTicks, ClientWorld worldIn, int renderDistanceChunks, float bossColorModifier, CallbackInfo ci) {
        FluidState fluidstate = activeRenderInfoIn.getFluidState();
        if (fluidstate.isTagged(SMSTags.Fluids.MILK)) {
            red = 0.98F;
            green = 0.98F;
            blue = 0.98F;
            waterFogUpdateTime = -1L;
        }
    }
}
