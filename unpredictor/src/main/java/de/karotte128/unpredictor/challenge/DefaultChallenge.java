package de.karotte128.unpredictor.challenge;

import de.karotte128.unpredictor.Unpredictor;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class DefaultChallenge implements Listener {
    public Server server = Unpredictor.getInstance().getServer();

    public DefaultChallenge(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    public abstract void tick();

    public abstract void load();

    public abstract void unload();

}
