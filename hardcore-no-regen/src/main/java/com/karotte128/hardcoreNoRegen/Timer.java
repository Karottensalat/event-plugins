package com.karotte128.hardcoreNoRegen;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.scheduler.BukkitTask;

public class Timer {
    private static BukkitTask currentTask;
    public static Integer timerSeconds;

    public static void startTimer(){
        timerSeconds = -60;
        currentTask = Bukkit.getScheduler().runTaskTimer(HardcoreNoRegen.getInstance(),
                () -> {
                    processTimerTick();
                },
                0, 20L
        );
    }

    public static void resetTimer(){
        currentTask.cancel();
    }

    private static void processTimerTick(){
        timerSeconds++;
        if (timerSeconds <= 0) {
            announceTimer("Attack Cooldown: " + Math.abs(timerSeconds), TextColor.color(Color.RED.asRGB()));
        } else {
            announceTimer("Survived Time: " + timerSeconds, TextColor.color(Color.GREEN.asRGB()));
        }
    }

    private static void announceTimer(String time, TextColor textColor){
        Bukkit.getOnlinePlayers().forEach(player -> {
            Component component = Component.text(time).color(textColor);
            player.sendActionBar(component);
        });
    }
}