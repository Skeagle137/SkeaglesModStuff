package net.skeagle.skeaglesmodstuff.Block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class DuckBlock extends Block {

    public DuckBlock() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(2.0f)
                .lightValue(20)
        );
        setRegistryName("duckblock");
    }
}
