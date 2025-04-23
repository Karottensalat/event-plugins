package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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

        Transformation transformation = new Transformation(new Vector3f(-0.5F, -1, -0.5F), new AxisAngle4f(), new Vector3f(1, 1, 1), new AxisAngle4f());

        BlockDisplay blockDisplay = event.getBlock().getWorld().spawn(blockLocation, BlockDisplay.class);
        blockDisplay.setBlock(blockMaterial.createBlockData());
        blockDisplay.setTransformation(transformation);
        blockDisplay.addScoreboardTag("unpredictor_attacking_block");
        blockDisplay.addScoreboardTag("unpredictor_block_display");

        Zombie zombie = world.spawn(blockLocation.toCenterLocation().add(0, -0.5, 0), Zombie.class);
        zombie.setInvisible(true);
        zombie.setShouldBurnInDay(false);
        zombie.setBaby();
        zombie.setCanPickupItems(false);
        zombie.getEquipment().clear();
        zombie.setSilent(true);
        zombie.customName(Component.text("attacking block"));
        zombie.addScoreboardTag("unpredictor_attacking_block");
        zombie.addScoreboardTag("unpredictor_zombie");

        zombie.addPassenger(blockDisplay);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getScoreboardTags().contains("unpredictor_zombie")) {
            event.getDrops().clear();
            event.setDroppedExp(0);
            event.getEntity().getPassengers().forEach(Entity::remove);
        }
    }
}
