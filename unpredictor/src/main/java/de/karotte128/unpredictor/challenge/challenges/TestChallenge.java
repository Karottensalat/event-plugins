package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class TestChallenge extends DefaultChallenge {
    public TestChallenge(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void tick() {

    }

    @Override
    public void load() {
        server.broadcast(Component.text("load test challenge"));
    }

    @Override
    public void unload() {
        HandlerList.unregisterAll(this);
        server.broadcast(Component.text("unload test challenge"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        server.broadcast(Component.text("Block break"));
    }
}
