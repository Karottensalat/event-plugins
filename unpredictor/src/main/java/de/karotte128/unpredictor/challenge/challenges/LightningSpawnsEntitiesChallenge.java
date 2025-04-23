package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.LightningStrikeEvent;

import java.util.Arrays;
import java.util.Random;

public class LightningSpawnsEntitiesChallenge extends DefaultChallenge {
    @Override
    public void load() {
        Unpredictor.getInstance().getServer().getWorld("world").setThundering(true);
        Unpredictor.getInstance().getServer().getWorld("world").setThunderDuration(24000);
        Debug.debugMessage("load lightning spawning challenge");
    }

    @Override
    public void unload() {
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
            event.getWorld().spawnEntity(location, type);
        }
    }

    private static EntityType getRandomEntity() {
        Random rand = new Random();
        return Arrays.asList(EntityType.values()).get(rand.nextInt(EntityType.values().length));
    }
}
