package de.karotte128.unpredictor;

import de.karotte128.unpredictor.util.NewDayDetector;
import de.karotte128.unpredictor.util.UnpredictorCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public final class Unpredictor extends JavaPlugin {
    private static Unpredictor instance;
    private static int newDayDetectorTask;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        BukkitScheduler scheduler = getServer().getScheduler();
        newDayDetectorTask = scheduler.scheduleSyncRepeatingTask(this, new NewDayDetector(), 0, 1);

        Objects.requireNonNull(getCommand("unpredictor")).setExecutor(new UnpredictorCommand());

        getLogger().info("Unpredictor has been loaded!");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTask(newDayDetectorTask);
        getLogger().info("Unpredictor has been unloaded!");
    }

    public static Unpredictor getInstance() {
        return instance;
    }
}
