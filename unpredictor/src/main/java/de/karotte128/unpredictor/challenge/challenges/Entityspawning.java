package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import java.util.HashMap;

public class Entityspawning extends DefaultChallenge {

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
        server.broadcast(Component.text("load test challenge"));
    }

    @Override
    public void unload() {
        entityTypeHashMap =null;
        server.broadcast(Component.text("unload test challenge"));
    }

    @Override
    public void runTask() {
    }

    @EventHandler
    public void onEntityspawning (EntitySpawnEvent event) {

        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();
        EntityType neuType = entityTypeHashMap.get(entityType);
        Block spawner = entity.getLocation().getBlock();
        spawner.setType(Material.SPAWNER);
        CreatureSpawner creatureSpawner = (CreatureSpawner) spawner.getState();
        creatureSpawner.setSpawnedType(entityType);
        creatureSpawner.update();
        entity.remove();
    }
}
