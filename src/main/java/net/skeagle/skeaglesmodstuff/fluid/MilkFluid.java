package net.skeagle.skeaglesmodstuff.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import net.skeagle.skeaglesmodstuff.*;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class MilkFluid extends FlowingFluid {

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(new ResourceLocation(SMSMain.MODID,"block/milk_still"), new ResourceLocation(SMSMain.MODID,"block/milk_flow"))
                .color(0xFFFFFFF0).density(1000).luminosity(2)
                .overlay(new ResourceLocation(SMSMain.MODID, "block/milk_overlay"))
                .build(this);
    }

    public Fluid getFlowingFluid() {
        return SMSFluids.FLOWING_MILK.get();
    }

    public Fluid getStillFluid() {
        return SMSFluids.MILK.get();
    }

    public Item getFilledBucket() {
        return SMSItems.ENCHANTED_MILK_BUCKET.get();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, FluidState state, Random random) {
        if (!state.isSource() && !state.get(FALLING))
            if (random.nextInt(64) == 0)
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);

    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IParticleData getDripParticleData() {
        return SMSParticles.DRIPPING_MILK.get();
    }

    protected boolean canSourcesMultiply() {
        return true;
    }

    protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
        TileEntity tileentity = state.hasTileEntity() ? worldIn.getTileEntity(pos) : null;
        Block.spawnDrops(state, worldIn, pos, tileentity);
    }

    public int getSlopeFindDistance(IWorldReader worldIn) {
        return 4;
    }

    public BlockState getBlockState(FluidState state) {
        return SMSBlocks.MILK_FLUID_BLOCK.get().getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == SMSFluids.MILK.get() || fluidIn == SMSFluids.FLOWING_MILK.get();
    }

    public int getLevelDecreasePerBlock(IWorldReader worldIn) {
        return 1;
    }

    public int getTickRate(IWorldReader p_205569_1_) {
        return 5;
    }

    public boolean canDisplace(FluidState fluidState, IBlockReader blockReader, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER);
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public static class Flowing extends MilkFluid {
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL_1_8);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends MilkFluid {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
