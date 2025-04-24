package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakClearInvChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load sneak clear inv challenge");
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload sneak clear inv challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        event.getPlayer().getInventory().clear();
    }
}
