package net.skeagle.skeaglesmodstuff;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeEntity;

import static net.skeagle.skeaglesmodstuff.Registry.ENTITIES;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMSEntities {

    public static final EntityType<MilkCubeEntity> MILK_CUBE_TYPE = EntityType.Builder.create(MilkCubeEntity::new, EntityClassification.MONSTER).size(2.04F, 2.04F).trackingRange(10).build("milk_cube");
    public static final RegistryObject<EntityType<MilkCubeEntity>> MILK_CUBE = ENTITIES.register("milk_cube", () -> MILK_CUBE_TYPE);

    public static final EntityType<MilkCubeEntity> FIDO_TYPE = EntityType.Builder.create(MilkCubeEntity::new, EntityClassification.MISC).size(0.6F, 1.95F).trackingRange(8).build("fido");
    public static final RegistryObject<EntityType<MilkCubeEntity>> FIDO = ENTITIES.register("fido", () -> MILK_CUBE_TYPE);

    static void registerSpawns() {
        EntitySpawnPlacementRegistry.register(MILK_CUBE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MilkCubeEntity::canSpawn);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        e.put(MILK_CUBE.get(), MilkCubeEntity.registerAttributes().create());
    }

    static void init() {
    }
}
