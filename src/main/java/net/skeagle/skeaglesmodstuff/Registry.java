package net.skeagle.skeaglesmodstuff;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DimensionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SMSMain.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SMSMain.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SMSMain.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SMSMain.MODID);
    public static final DeferredRegister<PaintingType> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, SMSMain.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SMSMain.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SMSMain.MODID);

    public static void register() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        SOUNDS.register(bus);
        PAINTINGS.register(bus);
        FLUIDS.register(bus);
        PARTICLES.register(bus);

        SMSEntities.init();
        SMSBlocks.init();
        SMSItems.init();
        SMSSounds.init();
        SMSPaintings.init();
        SMSFluids.init();
        SMSParticles.init();
        SMSTags.init();
    }
}
