package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.skeagle.skeaglesmodstuff.SMSFluids;

public class MilkFluidBlock extends FlowingFluidBlock {

    public MilkFluidBlock() {
        super(SMSFluids.MILK, Properties.create(Material.WATER)
                .doesNotBlockMovement()
                .hardnessAndResistance(100.0F)
                .noDrops()
        );
    }
}
