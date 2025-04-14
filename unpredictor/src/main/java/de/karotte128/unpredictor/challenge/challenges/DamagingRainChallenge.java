package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class DamagingRainChallenge extends DefaultChallenge {

    @Override
    public void load() {
        scheduleTask(10);
        server.broadcast(Component.text("load rain challenge"));
    }

    @Override
    public void unload() {
        server.broadcast(Component.text("unload rain challenge"));
    }

    @Override
    public void runTask() {
        for (Player player : server.getOnlinePlayers()) {
            if (!player.getWorld().isThundering() && !player.getWorld().hasStorm()) return;
            int blockLocation = player.getLocation().getWorld().getHighestBlockYAt(player.getLocation());
            if (blockLocation <= player.getLocation().getY()) {
                player.damage(1);
            }
        }
    }

}
