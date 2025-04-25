package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.Random;

public class FallingAnvilChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load falling anvil challenge");
        int task = scheduleTask(5);
        Debug.debugMessage("scheduled task " + task);
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload falling anvil challenge");
    }

    @Override
    public void runTask() {
        server.getOnlinePlayers().forEach(this::spawnAnvil);
    }

    private void spawnAnvil(Player player){
        if (player.getLocation().getWorld() == server.getWorld("world")) {
            player.getWorld().getBlockAt(spawnLocation(player.getLocation())).setType(Material.DIAMOND_BLOCK);
        }
    }

    private Location spawnLocation(Location playerLocation) {
        Random rand = new Random();
        int x = playerLocation.getBlockX() - 8 + rand.nextInt(16);
        int z = playerLocation.getBlockY() - 8 + rand.nextInt(16);
        int y = playerLocation.getWorld().getHighestBlockYAt(x , z) + 30;
        return new Location(playerLocation.getWorld(), x, y, z);
    }
}
