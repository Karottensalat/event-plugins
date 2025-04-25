package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class FallingAnvilChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load test challenge");
        int task = scheduleTask(5);
        Debug.debugMessage("scheduled task " + task);
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload test challenge");
    }

    @Override
    public void runTask() {
        Bukkit.getOnlinePlayers().forEach(player -> spawnAnvil(player));
        server.broadcast(Component.text("running test challenge task"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        server.broadcast(Component.text("Block break"));
    }

    private void spawnAnvil(Player player){
        Block anvil = player.getWorld().getBlockAt(spawnLocation(player.getLocation()));
        anvil.setType(Material.DAMAGED_ANVIL);
    }

    private Location spawnLocation(Location playerLocation){
        if (playerLocation.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
            Random rand = new Random();
            int grosse = 8;
            int bereichStartX = playerLocation.getBlockX() - grosse;
            int bereichStartY = playerLocation.getBlockY() - grosse;
            int hohe = playerLocation.getWorld().getHighestBlockYAt(bereichStartX + rand.nextInt(16), bereichStartY + rand.nextInt(16));
            Location location = new Location(playerLocation.getWorld(), bereichStartX + rand.nextInt(16), bereichStartY + rand.nextInt(16), hohe + 30);
            return location;
        }else {
            return null;
        }
    }
}
