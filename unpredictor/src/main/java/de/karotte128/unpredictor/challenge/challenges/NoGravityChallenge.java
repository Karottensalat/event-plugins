package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;

public class NoGravityChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load no gravity challenge");
        setGravity(false);
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload no gravity challenge");
        setGravity(true);
    }

    @Override
    public void runTask() {
        //nothing here
    }

    private void setGravity(boolean gravity) {
        server.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                entity.setGravity(gravity);
            });
        });
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.getEntity().setGravity(false);
    }
}
