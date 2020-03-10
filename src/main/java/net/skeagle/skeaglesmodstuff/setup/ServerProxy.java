package net.skeagle.skeaglesmodstuff.setup;

import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public void init() {

    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("nah fam, only on the client");
    }
}
