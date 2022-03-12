package net.skeagle.skeaglesmodstuff.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.skeagle.skeaglesmodstuff.registry.SMSBlocks;
import net.skeagle.skeaglesmodstuff.utils.CustomLoot;
import net.skeagle.skeaglesmodstuff.utils.DropLoot;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlockLootGen implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator gen;
    private final Map<Supplier<Block>, LootTable.Builder> tables;

    public BlockLootGen(DataGenerator gen) {
        this.gen = gen;
        tables = new HashMap<>();
    }

    private void registerTables() {
        for (Field field : SMSBlocks.class.getFields()) {
            try {
                if (field.isAnnotationPresent(CustomLoot.class)) {

                }
                else if (field.isAnnotationPresent(DropLoot.class)) {
                    DropLoot dropLoot = field.getAnnotation(DropLoot.class);
                    int amount = dropLoot.value();
                    boolean survives = dropLoot.survives();
                    this.dropSelfBasic((RegistryObject<Block>) field.get(null), amount, survives);
                }
            }
            catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void dropSelfBasic(Supplier<Block> block, int amount, boolean survives) {
        LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(amount))
                        .add(LootItem.lootTableItem(block.get()));
        tables.put(block, LootTable.lootTable().withPool(survives ? pool.when(ExplosionCondition.survivesExplosion()) : pool));
    }

    @Override
    public void run(HashCache pCache) {
        this.registerTables();
        tables.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().get().getLootTable(), Map.Entry::getValue))
                .forEach((k, v) -> {
                    try {
                        DataProvider.save(GSON, pCache, LootTables.serialize(v.setParamSet(LootContextParamSets.BLOCK).build()),
                                gen.getOutputFolder().resolve("data/" + k.getNamespace() + "/loot_tables/" + k.getPath() + ".json"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public String getName() {
        return "SMS Block Loot Tables";
    }
}
