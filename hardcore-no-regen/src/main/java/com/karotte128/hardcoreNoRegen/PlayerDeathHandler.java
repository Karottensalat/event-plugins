package com.karotte128.hardcoreNoRegen;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathHandler implements Listener {

    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        Component timeMessage = Component.text(event.getPlayer().getName() + " survived for " + (event.getPlayer().getTicksLived())/20 + " seconds! (" + Timer.timerSeconds.toString() + " seconds on the global timer)");

        HardcoreNoRegen.getInstance().getServer().broadcast(timeMessage);
    }
}
