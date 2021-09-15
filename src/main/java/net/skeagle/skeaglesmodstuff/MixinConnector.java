package net.skeagle.skeaglesmodstuff;


import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector {

    @Override
    public void connect() {
        MixinBootstrap.init();
        Mixins.addConfigurations("smodstuff.mixins.json");
    }
}
