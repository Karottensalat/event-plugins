package com.karotte128.hardcoreNoRegen;

import net.kyori.adventure.text.Component;
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
            if (Timer.timerSeconds < 0) {
                event.setCancelled(true);
            } else {
                Player player = (Player) entity;
                double remainingHealth = player.getHealth() - event.getDamage();
                player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(remainingHealth);

                Component damageMessage = Component.text("Player " + player.getName() + " took " + event.getDamage() + "HP damage, only " + remainingHealth + "HP remaining!");
                HardcoreNoRegen.getInstance().getServer().broadcast(damageMessage);
            }
        }
    }
}