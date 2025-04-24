package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.util.Random;

public class ZombieApocalypseChallenge extends DefaultChallenge {
    private final World world = server.getWorld("world");

    @Override
    public void load() {
        scheduleTask(80);
        Debug.debugMessage("load zombie apocalypse challenge");
    }

    @Override
    public void unload() {
        world.getEntities().forEach(entity -> {
            if (entity.getScoreboardTags().contains("unpredictor_zombie_apocalypse")) {
                entity.remove();
            }
        });
        Debug.debugMessage("unload zombie apocalypse challenge");
    }

    @Override
    public void runTask() {
        world.getPlayers().forEach(this::spawnZombie);
    }

    private void spawnZombie(Player player) {
        Location spawnLocation = getRandomSpawnLocation(player.getLocation(), 24, 20, 10);

        if (spawnLocation != null) {
            Zombie zombie = world.spawn(spawnLocation, Zombie.class);
            zombie.addScoreboardTag("unpredictor_zombie_apocalypse");
            zombie.setShouldBurnInDay(false);
            zombie.setCanBreakDoors(true);
        } else {
            Debug.debugMessage("No valid spawn location found!");
        }
    }

    private Location getRandomSpawnLocation(Location center, double maxRadius, double minRadius, int maxSearchHeight) {
        Random rand = new Random();

        double angle = rand.nextDouble() * 2 * Math.PI;

        double distance = Math.sqrt(rand.nextDouble()) *
                (maxRadius - minRadius) + minRadius;

        int x = (int)(Math.cos(angle) * distance);
        int z = (int)(Math.sin(angle) * distance);

        Location testLocation = center.clone().add(x, 0, z);

        if (isSafeLocation(testLocation)) {
            return testLocation;
        }

        for (int heightOffset = 1; heightOffset <= maxSearchHeight; heightOffset++) {
            Location above = testLocation.clone().add(0, heightOffset, 0);
            if (isSafeLocation(above)) {
                return above;
            }

            Location below = testLocation.clone().add(0, -heightOffset, 0);
            if (isSafeLocation(below)) {
                return below;
            }
        }

        return null;
    }

    private boolean isSafeLocation(Location location) {

        Block block = location.getBlock();
        Block below = block.getRelative(BlockFace.DOWN);

        return below.getType().isSolid() &&
                !below.isLiquid() &&
                !block.isLiquid() &&
                block.getType().isAir();
    }
}
