package com.karotte128.hardcoreNoRegen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!commandSender.hasPermission("eventplugins.admin")) {
            commandSender.sendMessage("§cMissing permissions!");
            return false;
        }

        if (args.length != 1) {
            commandSender.sendMessage("§7Use: /timer <start/reset>");
            return false;
        }

        switch (args[0]) {
            case "start":
                Timer.startTimer();
                break;
            case "reset":
                Timer.resetTimer();
                break;
            default:
                commandSender.sendMessage("§cunknown argument!");
                return false;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        final List<String> validArguments = new ArrayList<>();

        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], List.of("start", "reset"), validArguments);
            return validArguments;
        }

        return List.of();
    }
}

