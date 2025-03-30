package com.karotte128.hardcoreNoRegen;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatHandler implements Listener {

    @EventHandler
    public void AsyncChatEvent(AsyncChatEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }
}
