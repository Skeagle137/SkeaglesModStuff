package net.skeagle.skeaglesmodstuff.proxy;

import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("nah fam, only on the client");
    }
}
