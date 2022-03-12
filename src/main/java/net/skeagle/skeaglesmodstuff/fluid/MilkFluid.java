package net.skeagle.skeaglesmodstuff.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;
import net.skeagle.skeaglesmodstuff.*;
import net.skeagle.skeaglesmodstuff.registry.*;

import java.util.Optional;
import java.util.Random;

public abstract class MilkFluid extends FlowingFluid {

    @Override
    public Fluid getFlowing() {
        return SMSFluids.FLOWING_MILK.get();
    }

    @Override
    public Fluid getSource() {
        return SMSFluids.MILK.get();
    }

    @Override
    public Item getBucket() {
        return SMSItems.ENCHANTED_MILK_BUCKET.get();
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.ofNullable(getAttributes().getFillSound());
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(new ResourceLocation(SMSMain.MODID,"block/milk_still"), new ResourceLocation(SMSMain.MODID,"block/milk_flow"))
                .overlay(new ResourceLocation(SMSMain.MODID, "block/milk_overlay")).translationKey("block.smodstuff.milk")
                .color(0xFFFFFFF0).sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)
                .luminosity(2).build(this);
    }

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState state, Random random) {
        if (!state.isSource() && !state.getValue(FALLING)) {
            if (random.nextInt(64) == 0)
                level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
        }
        else if (random.nextInt(10) == 0)
            level.addParticle(ParticleTypes.UNDERWATER, pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ParticleOptions getDripParticle() {
        return SMSParticles.DRIPPING_MILK.get();
    }

    @Override
    protected boolean canConvertToSource() {
        return true;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity be = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, be);
    }

    @Override
    public int getSlopeFindDistance(LevelReader level) {
        return 4;
    }

    @Override
    public BlockState createLegacyBlock(FluidState state) {
        return SMSBlocks.MILK_FLUID_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == SMSFluids.MILK.get() || fluid == SMSFluids.FLOWING_MILK.get();
    }

    @Override
    public int getDropOff(LevelReader level) {
        return 1;
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 5;
    }

    @Override
    public boolean canBeReplacedWith(FluidState fluidState, BlockGetter level, BlockPos pos, Fluid fluid, Direction dir) {
        return dir == Direction.DOWN && !fluid.is(SMSTags.MILK);
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    public static class Flowing extends MilkFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends MilkFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
