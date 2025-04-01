package de.karotte128.unpredictor;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Unpredictor extends JavaPlugin {
    private static Unpredictor instance;
    private static int taskId;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        BukkitScheduler scheduler = getServer().getScheduler();
        taskId = scheduler.scheduleSyncRepeatingTask(this, new NewDayDetector(), 0, 1);

        getCommand("unpredictor").setExecutor(new UnpredictorCommand());

        getLogger().info("Unpredictor has been loaded!");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTask(taskId);
        getLogger().info("Unpredictor has been unloaded!");
    }

    public static Unpredictor getInstance() {
        return instance;
    }
}
