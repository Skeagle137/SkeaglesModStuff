package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class MilkFluidBlock extends LiquidBlock {

    public MilkFluidBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties);
    }
}
