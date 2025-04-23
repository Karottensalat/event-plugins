package de.karotte128.unpredictor.challenge;

import de.karotte128.unpredictor.Unpredictor;
import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public abstract class DefaultChallenge implements Listener {
    public static final Server server = Unpredictor.getInstance().getServer();
    private final BukkitScheduler scheduler = server.getScheduler();

    private int challengeTask;
    public final Plugin plugin = Unpredictor.getInstance();

    public void loadChallenge() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        load();
    }

    public void unloadChallenge() {
        HandlerList.unregisterAll(this);
        scheduler.cancelTask(challengeTask);
        unload();
    }

    public int scheduleTask(Integer ticks) {
        BukkitScheduler scheduler = server.getScheduler();
        challengeTask = scheduler.scheduleSyncRepeatingTask(Unpredictor.getInstance(), this::runTask, 0, ticks);
        return challengeTask;
    }

    public abstract void load();

    public abstract void unload();

    public abstract void runTask();

}
