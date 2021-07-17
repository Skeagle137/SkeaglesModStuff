package net.skeagle.skeaglesmodstuff.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mixin(SkinManager.class)
public abstract class MixinSkinManager {

    @Shadow @Final
    private MinecraftSessionService sessionService;

    @Shadow
    protected abstract ResourceLocation loadSkin(MinecraftProfileTexture texture, MinecraftProfileTexture.Type type, SkinManager.ISkinAvailableCallback callback);

    @Inject(at = @At(value = "HEAD"),
            method = "loadProfileTextures(Lcom/mojang/authlib/GameProfile;Lnet/minecraft/client/resources/SkinManager$ISkinAvailableCallback;Z)V",
            cancellable = true)
    public void loadProfileTextures(GameProfile profile, SkinManager.ISkinAvailableCallback skinAvailableCallback, boolean requireSecure, CallbackInfo ci) {
        Runnable runnable = () -> {
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Maps.newHashMap();

            try {
                map.putAll(this.sessionService.getTextures(profile, requireSecure));
            } catch (InsecureTextureException insecuretextureexception1) {
            }

            if (map.isEmpty()) {
                profile.getProperties().clear();
                if (profile.getId().equals(Minecraft.getInstance().getSession().getProfile().getId())) {
                    profile.getProperties().putAll(Minecraft.getInstance().getProfileProperties());
                    map.putAll(this.sessionService.getTextures(profile, false));
                } else {
                    this.sessionService.fillProfileProperties(profile, requireSecure);

                    try {
                        map.putAll(this.sessionService.getTextures(profile, requireSecure));
                    } catch (InsecureTextureException insecuretextureexception) {
                    }
                }
            }
            addCape(profile, map);

            Minecraft.getInstance().execute(() ->
                    RenderSystem.recordRenderCall(() ->
                            ImmutableList.of(MinecraftProfileTexture.Type.SKIN, MinecraftProfileTexture.Type.CAPE).forEach((type) -> {
                                if (map.containsKey(type))
                                    loadSkin(map.get(type), type, skinAvailableCallback);
                            })));
        };
        Util.getServerExecutor().execute(runnable);
        ci.cancel();
    }

    private void addCape(GameProfile profile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map) {
        String[] accounts = { "Skeagle_", "IcaLLJAckS", "Tamunda", "Valasa", "Fido63", "Megaderp_", "Sockings", "SerenadeSP",
                "Spoop_me_boopy", "NinjaofTehSkai", "Chykneese", "That_one_kid46", "YouCantBetMe", "DeadedAndBreaded" };
        try {
            if (Arrays.stream(accounts).anyMatch(name -> name.equalsIgnoreCase(profile.getName()))) {
                String url = "https://user-images.githubusercontent.com/18729303/124414664-2804e200-dd21-11eb-8d87-91d92c4ea74f.png";
                map.put(MinecraftProfileTexture.Type.CAPE, new MinecraftProfileTexture(url, null));
            }
        } catch(Exception e) {
            System.err.println("Failed to load cape for '" + profile.getName() + "' (" + profile.getId().toString() + ").");
            e.printStackTrace();
        }
    }
}
