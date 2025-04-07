package de.karotte128.unpredictor.util;

import de.karotte128.unpredictor.Unpredictor;
import de.karotte128.unpredictor.challenges.ChallengeManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Server;

public class NewDayDetector implements Runnable {
    static Server server = Unpredictor.getInstance().getServer();

    @Override
    public void run() {
        if (server.getWorld("world").getTime() == 1) {
            newDay();
        }
    }

    private static void newDay() {
        server.broadcast(Component.text("§cNew Day!"));
        server.broadcast(Component.text("Old challenge: " + ChallengeManager.getCurrentChallenge()));
        ChallengeManager.stopChallenge();
        String challenge = ChallengeManager.getRandomChallenge();
        ChallengeManager.startChallenge(challenge);
        server.broadcast(Component.text("New Challenge: " + challenge));
    }
}
