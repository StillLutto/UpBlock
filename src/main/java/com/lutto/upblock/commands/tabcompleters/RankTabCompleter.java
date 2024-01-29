package com.lutto.upblock.commands.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("set"), new ArrayList<>());
        } else if (args.length == 2) {
            List<String> playerNames = new ArrayList<>();
            for (Player playerName : Bukkit.getOnlinePlayers()) {
                playerNames.add(playerName.getName());
            }
            return StringUtil.copyPartialMatches(args[1], playerNames, new ArrayList<>());
        } else if (args.length == 3) {
            return StringUtil.copyPartialMatches(args[2], Arrays.asList("Owner", "Moderator", "Helper", "Tester"), new ArrayList<>());
        }

        return new ArrayList<>();
    }

}
