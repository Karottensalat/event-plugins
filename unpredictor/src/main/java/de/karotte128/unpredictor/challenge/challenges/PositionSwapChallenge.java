package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PositionSwapChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load swap position challenge");
        scheduleTask(1200);
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload swap position challenge");
    }

    @Override
    public void runTask() {
        List<Player> players = new ArrayList<>(server.getOnlinePlayers());

        if (players.size() < 2) {
            return;
        }

        List<Location> positions = new ArrayList<>();
        for (Player player : players) {
            positions.add(player.getLocation().clone());
        }

        Collections.shuffle(positions);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Location newPosition = positions.get(i);

            player.teleport(newPosition);
        }
    }
}
