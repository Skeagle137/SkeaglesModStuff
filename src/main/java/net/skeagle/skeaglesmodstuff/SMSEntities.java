package net.skeagle.skeaglesmodstuff;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.skeagle.skeaglesmodstuff.entity.giantskeleton.GiantSkeletonEntity;
import net.skeagle.skeaglesmodstuff.entity.human.SMSHumanEntity;
import net.skeagle.skeaglesmodstuff.entity.human.dad.DadEntity;
import net.skeagle.skeaglesmodstuff.entity.human.fido63.FidoEntity;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeEntity;

import static net.skeagle.skeaglesmodstuff.Registry.ENTITIES;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMSEntities {

    public static final EntityType<MilkCubeEntity> MILK_CUBE_TYPE = EntityType.Builder.create(MilkCubeEntity::new, EntityClassification.MONSTER).size(2.04F, 2.04F).trackingRange(10).build("milk_cube");
    public static final RegistryObject<EntityType<MilkCubeEntity>> MILK_CUBE = ENTITIES.register("milk_cube", () -> MILK_CUBE_TYPE);

    public static final EntityType<FidoEntity> FIDO_TYPE = EntityType.Builder.create(FidoEntity::new, EntityClassification.MISC).size(0.6F, 1.95F).trackingRange(8).build("fido");
    public static final RegistryObject<EntityType<FidoEntity>> FIDO = ENTITIES.register("fido", () -> FIDO_TYPE);

    public static final EntityType<DadEntity> DAD_TYPE = EntityType.Builder.create(DadEntity::new, EntityClassification.MISC).size(0.6F, 1.95F).trackingRange(8).build("dad");
    public static final RegistryObject<EntityType<DadEntity>> DAD = ENTITIES.register("dad", () -> DAD_TYPE);

    public static final EntityType<GiantSkeletonEntity> GIANT_SKELETON_TYPE = EntityType.Builder.create(GiantSkeletonEntity::new, EntityClassification.MONSTER).size(3.6F, 12.0F).trackingRange(10).build("giant_skeleton");
    public static final RegistryObject<EntityType<GiantSkeletonEntity>> GIANT_SKELETON = ENTITIES.register("giant_skeleton", () -> GIANT_SKELETON_TYPE);

    static void registerSpawns() {
        EntitySpawnPlacementRegistry.register(MILK_CUBE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MilkCubeEntity::canSpawn);
        EntitySpawnPlacementRegistry.register(FIDO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SMSHumanEntity::canSpawn);
        EntitySpawnPlacementRegistry.register(DAD.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SMSHumanEntity::canSpawn);
        EntitySpawnPlacementRegistry.register(GIANT_SKELETON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GiantSkeletonEntity::canSpawnOn);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        e.put(MILK_CUBE.get(), MilkCubeEntity.registerAttributes().create());
        e.put(FIDO.get(), FidoEntity.registerAttributes().create());
        e.put(DAD.get(), DadEntity.registerAttributes().create());
        e.put(GIANT_SKELETON.get(), GiantSkeletonEntity.registerAttributes().create());
    }

    static void init() {
    }
}
