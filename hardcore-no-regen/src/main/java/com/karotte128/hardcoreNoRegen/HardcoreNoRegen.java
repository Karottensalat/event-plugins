package com.karotte128.hardcoreNoRegen;

import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreNoRegen extends JavaPlugin {

    private final EntityDamageHandler entityDamageHandler;

    public HardcoreNoRegen() {
        this.entityDamageHandler = new EntityDamageHandler();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(entityDamageHandler, this);

        getLogger().info("Hardcore-No-Regen has been loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Hardcore-No-Regen has been unloaded!");
    }
}
