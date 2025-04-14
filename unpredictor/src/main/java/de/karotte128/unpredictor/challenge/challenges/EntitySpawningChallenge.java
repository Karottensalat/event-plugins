package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.*;

public class EntitySpawningChallenge extends DefaultChallenge {

    private ArrayList<EntityType> preList = new ArrayList<>();
    private HashMap<EntityType,EntityType> entityTypeHashMap = new HashMap<>();

    @Override
    public void load() {
        for (EntityType t :EntityType.values()){
            if (t != EntityType.PLAYER || t != EntityType.ITEM || t != EntityType.UNKNOWN) {
                preList.add(t);
            }
        }
        Collections.shuffle(preList);
        for (EntityType type : preList) {
            if (entityTypeHashMap.containsKey(null)) {
                entityTypeHashMap.put(type, entityTypeHashMap.get(null));
                entityTypeHashMap.put(entityTypeHashMap.get(null), type);
            } else {
                entityTypeHashMap.put(null, type);
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
//        entity.getWorld().spawnEntity(entity.getLocation(),newType);
        SpawnerMinecart w = (SpawnerMinecart) entity.getWorld().spawnEntity(entity.getLocation(),EntityType.SPAWNER_MINECART);
        w.spawnAt(entity.getLocation());
        w.setSpawnedType(newType);
        w.setMaxSpawnDelay(0);
        w.remove();
        entity.remove();
    }
}
