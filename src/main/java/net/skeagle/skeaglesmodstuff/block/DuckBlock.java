package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

import javax.annotation.Nullable;

public class DuckBlock extends Block {

    public DuckBlock() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(2.0f)
                .lightValue(20)
        );
        setRegistryName("duckblock");
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        if (blockstate != null) {
            blockstate = blockstate.with(BlockStateProperties.FACING, context.getPlacementHorizontalFacing().getOpposite());
        }
        return blockstate;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }
}
