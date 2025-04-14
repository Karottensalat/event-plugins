package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteractionChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load entity interaction challenge");
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload entity interaction challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        event.getPlayer().addPassenger(event.getRightClicked());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Location location = event.getDamager().getLocation();
            location.getWorld().spawnEntity(location, EntityType.TNT);
        }
    }
}
