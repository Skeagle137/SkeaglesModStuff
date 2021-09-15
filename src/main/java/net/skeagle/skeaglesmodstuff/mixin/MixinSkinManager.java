package net.skeagle.skeaglesmodstuff.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.skeagle.skeaglesmodstuff.SMSMain;
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
    protected abstract ResourceLocation registerTexture(MinecraftProfileTexture texture, MinecraftProfileTexture.Type textureType, SkinManager.SkinTextureCallback callback);

    @Inject(at = @At(value = "HEAD"),
            method = "registerSkins(Lcom/mojang/authlib/GameProfile;Lnet/minecraft/client/resources/SkinManager$SkinTextureCallback;Z)V",
            cancellable = true)
    public void loadProfileTextures(GameProfile profile, SkinManager.SkinTextureCallback skinCallback, boolean requireSecure, CallbackInfo ci) {
        Runnable runnable = () -> {
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Maps.newHashMap();

            try {
                map.putAll(this.sessionService.getTextures(profile, requireSecure));
            } catch (InsecureTextureException insecuretextureexception1) {
            }

            if (map.isEmpty()) {
                profile.getProperties().clear();
                if (profile.getId().equals(Minecraft.getInstance().getUser().getGameProfile().getId())) {
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
                                    registerTexture(map.get(type), type, skinCallback);
                            })));
        };
        Util.backgroundExecutor().execute(runnable);
        ci.cancel();
    }

    private void addCape(GameProfile profile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map) {
        try {
            if (Arrays.stream(SMSMain.capeAccounts).anyMatch(name -> name.equalsIgnoreCase(profile.getName()))) {
                String url = "https://user-images.githubusercontent.com/18729303/124414664-2804e200-dd21-11eb-8d87-91d92c4ea74f.png";
                map.put(MinecraftProfileTexture.Type.CAPE, new MinecraftProfileTexture(url, null));
            }
        } catch(Exception e) {
            System.err.println("Failed to load cape for '" + profile.getName() + "' (" + profile.getId().toString() + ").");
            e.printStackTrace();
        }
    }
}
