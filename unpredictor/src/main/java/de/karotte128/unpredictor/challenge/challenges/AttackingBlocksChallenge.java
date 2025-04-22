package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class AttackingBlocksChallenge extends DefaultChallenge {

    @Override
    public void load() {
        Debug.debugMessage("load attacking blocks challenge");
    }

    @Override
    public void unload() {
        server.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity.getScoreboardTags().contains("unpredictor_attacking_block")) {
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
        World world = event.getBlock().getWorld();

        Transformation transformation = new Transformation(new Vector3f(0, -1, 0), new AxisAngle4f(), new Vector3f(1, 1, 1), new AxisAngle4f());

        BlockDisplay blockDisplay = event.getBlock().getWorld().spawn(blockLocation, BlockDisplay.class);
        blockDisplay.setBlock(blockMaterial.createBlockData());
        blockDisplay.setTransformation(transformation);
        blockDisplay.addScoreboardTag("unpredictor_attacking_block");

        Zombie zombie = world.spawn(blockLocation, Zombie.class);
        zombie.setInvisible(true);
        zombie.setShouldBurnInDay(false);
        zombie.setBaby();
        zombie.addScoreboardTag("unpredictor_attacking_block");

        zombie.addPassenger(blockDisplay);
    }
}
