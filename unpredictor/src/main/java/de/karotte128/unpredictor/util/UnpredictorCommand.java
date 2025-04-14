package de.karotte128.unpredictor.util;

import de.karotte128.unpredictor.challenge.ChallengeManager;
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
        switch (strings[0]) {
            case "setchallenge":
                if (strings.length == 2) {
                    if (Arrays.stream(ChallengeManager.getAllChallenges()).toList().contains(strings[1])) {
                        ChallengeManager.stopChallenge();
                        ChallengeManager.startChallenge(strings[1]);
                        commandSender.sendMessage("§2Set Challenge: " + strings[1]);
                    } else {
                        commandSender.sendMessage("§4Challenge not found!");
                        return false;
                    }
                } else {
                    commandSender.sendMessage("§4Challenge can not be empty!");
                    return false;
                }
                break;
            case "debug":
                if (strings.length == 2) {
                    if (strings[1].equals("true")) {
                        Debug.debug = true;
                        commandSender.sendMessage("§2Enabled Debug Mode");
                    } else if (strings[1].equals("false")) {
                        Debug.debug = false;
                        commandSender.sendMessage("§2Disabled Debug Mode");
                    } else {
                        commandSender.sendMessage("§3Invalid Command argument!");
                        return false;
                    }
                } else {
                    commandSender.sendMessage("§3Wrong number of arguments!");
                    return false;
                }
                break;
            default:
                commandSender.sendMessage("§3Invalid Command argument!");
                return false;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> validArguments = new ArrayList<>();

        if (strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], List.of("setchallenge", "debug"), validArguments);
            return validArguments;
        }

        if (strings.length == 2) {
            switch (strings[0]) {
                case "setchallenge":
                    StringUtil.copyPartialMatches(strings[1], Arrays.stream(ChallengeManager.getAllChallenges()).toList(), validArguments);
                    break;
                case "debug":
                    StringUtil.copyPartialMatches(strings[1] , List.of("true" , "false"), validArguments);
            }
        }

        return validArguments;
    }
}
