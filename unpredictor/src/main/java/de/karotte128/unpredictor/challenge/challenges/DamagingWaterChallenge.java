package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DamagingWaterChallenge extends DefaultChallenge {

    @Override
    public void load() {
        scheduleTask(20);
        Debug.debugMessage("load water damage challenge");
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload water damage challenge");
    }

    public void runTask() {
        for (Player player : server.getOnlinePlayers()) {
            Material blockType = player.getLocation().getBlock().getType();
            if (blockType == Material.WATER) {
                player.damage(2);
            }
        }
    }
}
