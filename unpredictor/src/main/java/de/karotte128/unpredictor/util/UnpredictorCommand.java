package de.karotte128.unpredictor.util;

import de.karotte128.unpredictor.challenges.ChallengeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnpredictorCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage(strings);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> validArguments = new ArrayList<>();

        if (strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], List.of("setchallenge"), validArguments);
            return validArguments;
        }

        if (strings.length == 2) {
            switch (strings[0]) {
                case "setchallenge":
                    StringUtil.copyPartialMatches(strings[1], Arrays.stream(ChallengeManager.getAllChallenges()).toList(), validArguments);
            }
        }

        return validArguments;
    }
}
