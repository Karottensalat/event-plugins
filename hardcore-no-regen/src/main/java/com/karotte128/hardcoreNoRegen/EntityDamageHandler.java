package com.karotte128.hardcoreNoRegen;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageHandler implements Listener {

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player) {
            Player player = (Player) entity;
            player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(player.getHealth() - event.getDamage());
        }
    }
}