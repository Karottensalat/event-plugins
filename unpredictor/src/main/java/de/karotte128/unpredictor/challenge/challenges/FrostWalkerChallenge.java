package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class FrostWalkerChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load frost walker challenge");
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload frost walker challenge");
    }

    @Override
    public void runTask() {
    }

    @EventHandler
    public void onBlockBreak(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking() && player.getGameMode().equals(GameMode.SURVIVAL)) {
            fillDisc(player.getLocation().add(0, -1, 0), Material.PACKED_ICE, 5);
        }
    }

    public void fillDisc(Location center, Material material, int radius) {
        World world = center.getWorld();

        int centerX = center.getBlockX();
        int centerY = center.getBlockY();
        int centerZ = center.getBlockZ();

        int minX = centerX - radius;
        int maxX = centerX + radius;
        int minZ = centerZ - radius;
        int maxZ = centerZ + radius;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double distance = Math.sqrt(
                        Math.pow(x - centerX, 2) +
                                Math.pow(z - centerZ, 2)
                );

                if (distance <= radius) {
                    Block block = world.getBlockAt(x, centerY, z);
                    if (block.getType().equals(Material.AIR) || block.getType().equals(Material.WATER)) {
                        block.setType(material);
                    }
                }
            }
        }
    }
}
