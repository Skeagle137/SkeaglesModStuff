package net.skeagle.skeaglesmodstuff;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.skeagle.skeaglesmodstuff.entity.eduardo.EduardoEntity;
import net.skeagle.skeaglesmodstuff.entity.giantskeleton.GiantSkeletonEntity;
import net.skeagle.skeaglesmodstuff.entity.human.SMSHumanEntity;
import net.skeagle.skeaglesmodstuff.entity.human.dad.DadEntity;
import net.skeagle.skeaglesmodstuff.entity.human.fido63.FidoEntity;
import net.skeagle.skeaglesmodstuff.entity.milkcube.MilkCubeEntity;

import static net.skeagle.skeaglesmodstuff.Registry.ENTITIES;

@Mod.EventBusSubscriber(modid = SMSMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMSEntities {

    public static final EntityType<MilkCubeEntity> MILK_CUBE_TYPE = EntityType.Builder.of(MilkCubeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).setTrackingRange(10).build("milk_cube");
    public static final RegistryObject<EntityType<MilkCubeEntity>> MILK_CUBE = ENTITIES.register("milk_cube", () -> MILK_CUBE_TYPE);

    public static final EntityType<FidoEntity> FIDO_TYPE = EntityType.Builder.of(FidoEntity::new, MobCategory.MISC).sized(0.6F, 1.95F).setTrackingRange(8).build("fido");
    public static final RegistryObject<EntityType<FidoEntity>> FIDO = ENTITIES.register("fido", () -> FIDO_TYPE);

    public static final EntityType<DadEntity> DAD_TYPE = EntityType.Builder.of(DadEntity::new, MobCategory.MISC).sized(0.6F, 1.95F).setTrackingRange(8).build("dad");
    public static final RegistryObject<EntityType<DadEntity>> DAD = ENTITIES.register("dad", () -> DAD_TYPE);

    public static final EntityType<GiantSkeletonEntity> GIANT_SKELETON_TYPE = EntityType.Builder.of(GiantSkeletonEntity::new, MobCategory.MONSTER).sized(3.6F, 12.0F).setTrackingRange(10).build("giant_skeleton");
    public static final RegistryObject<EntityType<GiantSkeletonEntity>> GIANT_SKELETON = ENTITIES.register("giant_skeleton", () -> GIANT_SKELETON_TYPE);

    public static final EntityType<EduardoEntity> EDUARDO_ENTITY_TYPE = EntityType.Builder.of(EduardoEntity::new, MobCategory.CREATURE).sized(0.9F, 1.4F).setTrackingRange(10).build("eduardo");
    public static final RegistryObject<EntityType<EduardoEntity>> EDUARDO = ENTITIES.register("eduardo", () -> EDUARDO_ENTITY_TYPE);

    static void registerSpawns() {
        SpawnPlacements.register(MILK_CUBE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MilkCubeEntity::canSpawn);
        SpawnPlacements.register(FIDO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SMSHumanEntity::canSpawn);
        SpawnPlacements.register(DAD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SMSHumanEntity::canSpawn);
        SpawnPlacements.register(GIANT_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GiantSkeletonEntity::checkAnyLightMonsterSpawnRules);
        SpawnPlacements.register(EDUARDO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EduardoEntity::checkAnimalSpawnRules);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent e) {
        e.put(MILK_CUBE.get(), MilkCubeEntity.registerAttributes().build());
        e.put(FIDO.get(), FidoEntity.registerAttributes().build());
        e.put(DAD.get(), DadEntity.registerAttributes().build());
        e.put(GIANT_SKELETON.get(), GiantSkeletonEntity.registerAttributes().build());
        e.put(EDUARDO.get(), EduardoEntity.createAttributes().build());
    }

    static void init() {
    }
}
