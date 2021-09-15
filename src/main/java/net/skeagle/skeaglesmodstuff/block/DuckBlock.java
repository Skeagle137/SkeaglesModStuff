package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class DuckBlock extends Block {

    public DuckBlock() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.GLASS)
                .strength(2.0f)
                .lightLevel(l -> 20)
        );
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        if (blockstate != null) {
            blockstate = blockstate.setValue(BlockStateProperties.FACING, context.getHorizontalDirection().getOpposite());
        }
        return blockstate;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter read, List<Component> list, TooltipFlag tooltip) {
        list.add(new TranslatableComponent(ChatFormatting.GOLD + "Duck block. What else?"));
    }
}
