package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

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
        Location spawnLocation = player.getLocation(); //TODO: add some spread logic

        Zombie zombie = world.spawn(spawnLocation, Zombie.class);
        zombie.addScoreboardTag("unpredictor_zombie_apocalypse");
        zombie.setShouldBurnInDay(false);
        zombie.setCanBreakDoors(true);
    }
}
