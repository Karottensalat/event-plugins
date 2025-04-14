package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import java.util.HashMap;

public class EntitySpawningChallenge extends DefaultChallenge {

    private HashMap<EntityType,EntityType> entityTypeHashMap = new HashMap<>();

    @Override
    public void load() {
        for (EntityType type : EntityType.values()) {
            if (type != EntityType.PLAYER) {
                if (entityTypeHashMap.containsKey(null)) {
                    entityTypeHashMap.put(type, entityTypeHashMap.get(null));
                    entityTypeHashMap.put(entityTypeHashMap.get(null), type);
                } else {
                    entityTypeHashMap.put(null, type);
                }
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
        EntityType entityType = event.getEntityType();
        EntityType newType = entityTypeHashMap.get(entityType);
        Block spawner = entity.getLocation().getBlock();
        spawner.setType(Material.SPAWNER);
        CreatureSpawner creatureSpawner = (CreatureSpawner) spawner.getState();
        creatureSpawner.setSpawnedType(newType);
        creatureSpawner.update();
        entity.remove();
    }
}
