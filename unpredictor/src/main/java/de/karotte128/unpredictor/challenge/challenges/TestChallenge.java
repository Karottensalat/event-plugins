package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class TestChallenge extends DefaultChallenge {

    @Override
    public void tick() {

    }

    @Override
    public void load() {
        server.broadcast(Component.text("load test challenge"));
    }

    @Override
    public void unload() {
        server.broadcast(Component.text("unload test challenge"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        server.broadcast(Component.text("Block break"));
    }
}
