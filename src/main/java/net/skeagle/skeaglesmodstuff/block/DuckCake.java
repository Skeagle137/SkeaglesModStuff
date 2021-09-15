package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class DuckCake extends CakeBlock {
    public DuckCake() {
        super(Properties.of(Material.CAKE)
                .sound(SoundType.WOOL)
                .strength(0.5f)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
    }

    @Override
    public InteractionResult use(BlockState state, Level w, BlockPos pos, Player p, InteractionHand hand, BlockHitResult hit) {
        if (w.isClientSide) {
            ItemStack i = p.getItemInHand(hand);
            if (this.eatSlice(w, pos, state, p) == InteractionResult.SUCCESS)
                return InteractionResult.SUCCESS;
            if (i.isEmpty())
                return InteractionResult.CONSUME;
        }
        return this.eatSlice(w, pos, state, p);
    }

    private InteractionResult eatSlice(final LevelAccessor w, final BlockPos pos, final BlockState state, final Player p) {
        if (!p.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            p.awardStat(Stats.EAT_CAKE_SLICE);
            p.getFoodData().eat(2, 0.1F);
            int i = state.getValue(BITES);
            if (i < 6) {
                w.setBlock(pos, state.setValue(BITES, i + 1), 3);
            } else {
                p.level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4f, Explosion.BlockInteraction.BREAK);
                w.removeBlock(pos, false);
            }
            return InteractionResult.SUCCESS;
        }
    }
}
