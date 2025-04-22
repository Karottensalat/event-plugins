package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class AttackingBlocksChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load attacking blocks challenge");
    }

    @Override
    public void unload() {
        server.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity.getScoreboardTags().contains("unpredictor_block")) {
                    entity.remove();
                }
            });
        });
        Debug.debugMessage("unload attacking blocks challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockMaterial = event.getBlock().getType();
        Location blockLocation = event.getBlock().getLocation();

        BlockDisplay blockDisplay = event.getBlock().getWorld().spawn(blockLocation, BlockDisplay.class);
        blockDisplay.setBlock(blockMaterial.createBlockData());
        blockDisplay.addScoreboardTag("unpredictor_block");
    }
}
