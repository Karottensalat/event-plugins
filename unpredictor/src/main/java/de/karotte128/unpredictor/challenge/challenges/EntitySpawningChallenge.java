package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.*;

public class EntitySpawningChallenge extends DefaultChallenge {
    private static final World world = Unpredictor.getInstance().getServer().getWorld("world");

    private HashMap<EntityType,EntityType> entityTypeHashMap = new HashMap<>();
    private HashMap<EntityType,Integer> spawnLimitHashMap = new HashMap<>();
    private HashMap<SpawnCategory,Integer> orgnialSpawnLimitHashMap = new HashMap<>();

    @Override
    public void load() {
        Debug.debugMessage("load entity spawn challenge");
        for (SpawnCategory spawnCategory : SpawnCategory.values()) {
            orgnialSpawnLimitHashMap.put(spawnCategory,Bukkit.getSpawnLimit(spawnCategory));
        }
    }

    @Override
    public void unload() {
        entityTypeHashMap = null;
        spawnLimitHashMap = null;
        for (World world1 :Bukkit.getWorlds()) {
            for (SpawnCategory spawnCategory : SpawnCategory.values()) {
                world1.setSpawnLimit(spawnCategory, orgnialSpawnLimitHashMap.get(spawnCategory));
            }
        }
        Debug.debugMessage("unload entity spawn challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onEntitySpawn (EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND) && !entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && !entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.EXPLOSION)) {
            EntityType entityType = event.getEntityType();

            if (entityTypeHashMap.get(entityType) == null) {
                if (entityType != EntityType.PLAYER && entityType != EntityType.ITEM && entityType != EntityType.UNKNOWN) {
                    entityTypeHashMap.put(entityType, getRandomEntity());
                    spawnLimitHashMap.put(entityType, entity.getWorld().getSpawnLimit(entity.getSpawnCategory()));
                }
            }

            EntityType newType = entityTypeHashMap.get(entityType);
            if (spawnLimitHashMap.get(entityType) >= 1) {
                entity.getWorld().spawnEntity(entity.getLocation(), newType);
                spawnLimitHashMap.put(entityType, spawnLimitHashMap.get(entityType) - 1);
            }
            entity.remove();
        }
    }

    private static EntityType getRandomEntity() {
        Random rand = new Random();
        return Arrays.asList(EntityType.values()).get(rand.nextInt(EntityType.values().length));
    }
}
