package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class TestChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load test challenge");
        int task = scheduleTask(60);
        Debug.debugMessage("scheduled task " + task);
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload test challenge");
    }

    @Override
    public void runTask() {
        server.broadcast(Component.text("running test challenge task"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        server.broadcast(Component.text("Block break"));
    }
}
