package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class DuckBlock extends Block {

    public DuckBlock() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(2.0f)
                .setLightLevel(l -> 20)
        );
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

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader read, List<ITextComponent> list, ITooltipFlag tooltip) {
        list.add(new TranslationTextComponent(TextFormatting.GOLD + "Duck block. What else?"));
    }
}
