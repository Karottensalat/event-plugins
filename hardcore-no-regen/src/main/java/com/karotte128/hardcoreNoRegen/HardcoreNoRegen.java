package com.karotte128.hardcoreNoRegen;

import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreNoRegen extends JavaPlugin {
    private static HardcoreNoRegen instance;

    public static HardcoreNoRegen getInstance() {
        return instance;
    }

    private final EntityDamageHandler entityDamageHandler;
    private final PlayerDeathHandler playerDeathHandler;
    private final PlayerChatHandler playerChatHandler;

    public HardcoreNoRegen() {
        this.entityDamageHandler = new EntityDamageHandler();
        this.playerDeathHandler = new PlayerDeathHandler();
        this.playerChatHandler = new PlayerChatHandler();
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(entityDamageHandler, this);
        getServer().getPluginManager().registerEvents(playerDeathHandler, this);
        getServer().getPluginManager().registerEvents(playerChatHandler, this);

        getLogger().info("Hardcore-No-Regen has been loaded!");

        Timer.timerSeconds = 0;

        getCommand("timer").setExecutor(new TimerCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("Stopped plugin at timer seconds: " + Timer.timerSeconds.toString());
        getLogger().info("Hardcore-No-Regen has been unloaded!");
    }
}
