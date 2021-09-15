package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SMSMain.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SMSMain.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SMSMain.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SMSMain.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SMSMain.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SMSMain.MODID);
    public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, SMSMain.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SMSMain.MODID);

    public static void register() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ATTRIBUTES.register(bus);
        ENTITIES.register(bus);
        ITEMS.register(bus);
        BLOCKS.register(bus);
        SOUNDS.register(bus);
        FLUIDS.register(bus);
        PAINTINGS.register(bus);
        PARTICLES.register(bus);

        SMSAttributes.init();
        SMSEntities.init();
        SMSItems.init();
        SMSBlocks.init();
        SMSSounds.init();
        SMSFluids.init();
        SMSPaintings.init();
        SMSParticles.init();
        SMSTags.init();
    }
}
