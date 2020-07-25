package net.skeagle.skeaglesmodstuff.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class DuckCake extends CakeBlock {
    public DuckCake() {
        super(Properties.create(Material.CAKE)
                .sound(SoundType.CLOTH)
                .hardnessAndResistance(0.5f)
        );
        setRegistryName("duckcake");
        this.setDefaultState(this.stateContainer.getBaseState().with(BITES, 0));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World w, BlockPos pos, PlayerEntity p, Hand hand, BlockRayTraceResult hit) {
        if (w.isRemote) {
            ItemStack i = p.getHeldItem(hand);
            if (this.func_226911_a_(w, pos, state, p) == ActionResultType.SUCCESS) {
                return ActionResultType.SUCCESS;
            }
            if (i.isEmpty()) {
                return ActionResultType.CONSUME;
            }
        }
        return this.func_226911_a_(w, pos, state, p);
    }

    private ActionResultType func_226911_a_(final IWorld w, final BlockPos pos, final BlockState state, final PlayerEntity p) {
        if (!p.canEat(false)) {
            return ActionResultType.PASS;
        } else {
            p.addStat(Stats.EAT_CAKE_SLICE);
            p.getFoodStats().addStats(2, 0.1F);
            int i = state.get(BITES);
            if (i < 6) {
                w.setBlockState(pos, state.with(BITES, i + 1), 3);
            } else {
                World world = p.world;
                world.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 2, Explosion.Mode.BREAK);
                w.removeBlock(pos, false);
            }
            return ActionResultType.SUCCESS;
        }
    }
}
