package net.skeagle.skeaglesmodstuff.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class SMSNetwork {

    public static SimpleChannel channel;
    public static final String PROTOCOL = "1.0";
    public static final ResourceLocation channelRLoc = new ResourceLocation("smodstuff", "smsnetwork");

    public static void registerChannel() {
        channel = NetworkRegistry.newSimpleChannel(channelRLoc, () -> PROTOCOL,
                PROTOCOL::equalsIgnoreCase, PROTOCOL::equalsIgnoreCase);
    }

}
