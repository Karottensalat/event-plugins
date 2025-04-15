package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
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

    private HashMap<EntityType, EntityType> entityTypeHashMap = new HashMap<>();
    private HashMap<SpawnCategory, Integer> spawnLimitHashMap = new HashMap<>();

    @Override
    public void load() {
        scheduleTask(200);
        Debug.debugMessage("load entity spawn challenge");
    }

    @Override
    public void unload() {
        entityTypeHashMap = null;
        spawnLimitHashMap = null;
        Debug.debugMessage("unload entity spawn challenge");
    }

    @Override
    public void runTask() {
        for (Map.Entry<SpawnCategory, Integer> entry : spawnLimitHashMap.entrySet()) {
            SpawnCategory category = entry.getKey();

            assert world != null;
            int amount = world.getEntities().stream()
                    .filter(entity -> entity.getSpawnCategory() == category)
                    .mapToInt(entity -> 1)
                    .sum();

            Debug.debugMessage("SpawnCategory " + category + " has " + amount + " entities.");
        }

    }

    @EventHandler
    public void onEntitySpawn (EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();

        if (entityType != EntityType.PLAYER && entityType != EntityType.ITEM && entityType != EntityType.UNKNOWN) {
            if (!entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND) && !entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && !entity.getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.EXPLOSION)) {

                if (entityTypeHashMap.get(entityType) == null) {
                    entityTypeHashMap.put(entityType, getRandomEntity());
                    spawnLimitHashMap.put(entity.getSpawnCategory(), entity.getWorld().getSpawnLimit(entity.getSpawnCategory()));
                }

                if (spawnLimitHashMap.get(entity.getSpawnCategory()) >= 1) {
                    entity.getWorld().spawnEntity(entity.getLocation(), entityTypeHashMap.get(entityType));
                    spawnLimitHashMap.put(entity.getSpawnCategory(), spawnLimitHashMap.get(entity.getSpawnCategory()) - 1);
                } else {
                    event.setCancelled(true);
                }
                entity.remove();
            }
        }
    }

    private static EntityType getRandomEntity() {
        Random rand = new Random();
        return Arrays.asList(EntityType.values()).get(rand.nextInt(EntityType.values().length));
    }
}
