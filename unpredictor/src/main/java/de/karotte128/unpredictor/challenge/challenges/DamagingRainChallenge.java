package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

public class DamagingRainChallenge extends DefaultChallenge {

    private int rainTask;
    private final BukkitScheduler scheduler = server.getScheduler();

    @Override
    public void load() {
        BukkitScheduler scheduler = server.getScheduler();
        rainTask = scheduler.scheduleSyncRepeatingTask(Unpredictor.getInstance(), runTask(), 0, 10);
        server.broadcast(Component.text("load rain challenge"));
    }

    @Override
    public void unload() {
        server.broadcast(Component.text("unload rain challenge"));
        scheduler.cancelTask(rainTask);
    }

    private @NotNull Runnable runTask() {
        return () -> {
            for (Player player : server.getOnlinePlayers()) {
                if(!player.getWorld().isThundering() && !player.getWorld().hasStorm()) return;
                int blockLocation = player.getLocation().getWorld().getHighestBlockYAt(player.getLocation());
                if(blockLocation <= player.getLocation().getY()) {
                    player.damage(2);
                }
            }
        };
    }

}
