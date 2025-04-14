package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DamagingWaterChallenge extends DefaultChallenge {

    @Override
    public void load() {
        scheduleTask(20);
        server.broadcast(Component.text("load water damage challenge"));
    }

    @Override
    public void unload() {
        server.broadcast(Component.text("unload water damage challenge"));
    }

    public void runTask() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Material blockTyp = player.getLocation().getBlock().getType();
            if (blockTyp == Material.WATER) {
                player.damage(2);
            }
        }
    }
}
