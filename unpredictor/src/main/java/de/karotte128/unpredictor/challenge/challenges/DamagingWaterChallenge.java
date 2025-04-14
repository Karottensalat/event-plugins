package de.karotte128.unpredictor.challenge.challenges;

import de.karotte128.unpredictor.challenge.DefaultChallenge;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;


    @Override
    public void load() {
    }

    @Override
    public void unload() {
    }

            for (Player player : Bukkit.getOnlinePlayers()) {
                Material blockTyp = player.getLocation().getBlock().getType();
                if(blockTyp == Material.WATER) {
                    player.damage(2);
                }
            }
    }
}
