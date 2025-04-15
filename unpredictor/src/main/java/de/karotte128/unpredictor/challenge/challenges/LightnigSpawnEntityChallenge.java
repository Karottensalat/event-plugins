package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

import java.util.Arrays;
import java.util.Random;

public class LightnigSpawnEntityChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Bukkit.getWorld("Overworld").setThunderDuration(24000);
        Debug.debugMessage("load test challenge");

    }

    @Override
    public void unload() {
        Debug.debugMessage("unload test challenge");
    }

    @Override
    public void runTask() {
    }

    @EventHandler
    public void onBlockBreak(LightningStrikeEvent event) {
        Location location = event.getLightning().getLocation();
        EntityType type = getRandomEntity();
        if (type != EntityType.PLAYER && type != EntityType.ITEM && type != EntityType.UNKNOWN) {
            event.getWorld().spawnEntity(location,type);
        }
    }

    private static EntityType getRandomEntity() {
        Random rand = new Random();
        return Arrays.asList(EntityType.values()).get(rand.nextInt(EntityType.values().length));
    }
}
