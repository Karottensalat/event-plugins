package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TestChallenge extends DefaultChallenge {

    private int inWaterTask;
    private final BukkitScheduler scheduler = server.getScheduler();

    @Override
    public void load() {
        BukkitScheduler scheduler = server.getScheduler();
        inWaterTask = scheduler.scheduleSyncRepeatingTask(Unpredictor.getInstance(), runTask(), 0, 20);
        server.broadcast(Component.text("load test challenge"));
    }

    @Override
    public void unload() {
        server.broadcast(Component.text("unload test challenge"));
        scheduler.cancelTask(inWaterTask);
    }

    private @NotNull Runnable runTask() {
        return () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Material blockTyp = player.getLocation().getBlock().getType();
                if(blockTyp == Material.WATER) {
                    player.damage(2);
                }
            }
        };
    }
}
