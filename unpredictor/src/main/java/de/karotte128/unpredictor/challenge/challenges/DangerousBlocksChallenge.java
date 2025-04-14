package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import de.karotte128.unpredictor.util.Debug;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;
import java.util.Random;

public class DangerousBlocksChallenge extends DefaultChallenge {

    private final Material[] blocksList = {
            Material.STONE, Material.DEEPSLATE, Material.DIRT, Material.GRASS_BLOCK, Material.SAND,
            Material.NETHERRACK, Material.END_STONE, Material.WATER, Material.GRAVEL, Material.DIORITE,
            Material.ANDESITE, Material.GRANITE, Material.TUFF
    };

    private Material dangerousBlock;

    @Override
    public void load() {
        Debug.debugMessage( "load dangerous block challenge");
        Random rand = new Random();
        dangerousBlock = Arrays.asList(blocksList).get(rand.nextInt(blocksList.length));
        Debug.debugMessage("The dangerous block is: " + dangerousBlock);
    }

    @Override
    public void unload() {
        Debug.debugMessage("unload dangerous block challenge");
    }

    @Override
    public void runTask() {
        //nothing here
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Material block = event.getPlayer().getLocation().add(-0, -1, -0).getBlock().getType();

        if (block == dangerousBlock) {
            event.getPlayer().getLocation().createExplosion(2);
        }
    }
}
