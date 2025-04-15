package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.*;

public class EntitySpawningChallenge extends DefaultChallenge {

    private HashMap<EntityType,EntityType> entityTypeHashMap = new HashMap<>();

    @Override
    public void load() {
        for (EntityType type :EntityType.values()){
            if (type != EntityType.PLAYER && type != EntityType.ITEM && type != EntityType.UNKNOWN) {
                entityTypeHashMap.put(type, getRandomEntity());
            }
        }

        Debug.debugMessage("load entity spawn challenge");
    }

    @Override
    public void unload() {
        entityTypeHashMap =null;
        Debug.debugMessage("unload entity spawn challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onEntitySpawn (EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND)&&!entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) &&!entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.EXPLOSION) ) {
            EntityType entityType = event.getEntityType();
            EntityType newType = entityTypeHashMap.get(entityType);
            entity.getWorld().spawnEntity(entity.getLocation(), newType);
            entity.remove();
        }
    }

    private static EntityType getRandomEntity() {
        Random rand = new Random();
        return Arrays.asList(EntityType.values()).get(rand.nextInt(EntityType.values().length));
    }
}
