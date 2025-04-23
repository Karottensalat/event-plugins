package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.LightningStrikeEvent;

import java.util.Arrays;
import java.util.Random;

public class LightningSpawnsEntitiesChallenge extends DefaultChallenge {
    private static final World world = Unpredictor.getInstance().getServer().getWorld("world");

    @Override
    public void load() {
        world.setThundering(true);
        world.setThunderDuration(24000);
        Debug.debugMessage("load lightning spawning challenge");
    }

    @Override
    public void unload() {
        world.setThundering(false);

        server.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity.getScoreboardTags().contains("unpredictor_lightning_entity")) {
                    entity.remove();
                }
            });
        });

        Debug.debugMessage("unload lightning spawning challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        Location location = event.getLightning().getLocation();
        EntityType type = getRandomEntity();
        if (type != EntityType.PLAYER && type != EntityType.ITEM && type != EntityType.UNKNOWN) {
            Entity entity = event.getWorld().spawnEntity(location, type);
            entity.addScoreboardTag("unpredictor_lightning_entity");
        }
    }

    private static EntityType getRandomEntity() {
        Random rand = new Random();
        return Arrays.asList(EntityType.values()).get(rand.nextInt(EntityType.values().length));
    }
}
