package com.lutto.upblock.commands;

import com.lutto.upblock.enums.Rank;
import com.lutto.upblock.UpBlock;
import com.lutto.upblock.utils.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RankCommand implements CommandExecutor {

    private UpBlock mainClass;

    public RankCommand(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("upblock.rank")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return false;
            }

            if (!(args.length == 3) || !args[0].equals("set")) {
                player.sendMessage(ChatColor.RED + "Invalid Usage!");
                player.sendMessage(ChatColor.RED + "Please use /rank set <player> <rank>");
                return false;
            }

            if (Bukkit.getPlayerExact(args[1]) == null) {
                player.sendMessage(ChatColor.RED + "Player not found!");
                return false;
            }


            OfflinePlayer targetPlayer = Bukkit.getPlayerExact(args[1]);
            for (Rank rank : Rank.values()) {

                if (rank.name().equalsIgnoreCase(args[2])) {

                    // possibly commented for testing purposes
                    if (targetPlayer.getUniqueId() == player.getUniqueId()) {
                        player.sendMessage(ChatColor.RED + "You can't change your own rank!");
                        return false;
                    }

                    try {
                        CustomPlayer playerData = mainClass.getPlayerManager().getCustomPlayer(targetPlayer.getUniqueId());
                        playerData.setRank(rank);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    player.sendMessage(ChatColor.GREEN + "You have changed " + targetPlayer.getName() + "'s rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                    if (targetPlayer.isOnline()) {
                        ((Player) targetPlayer).setPlayerListName(rank.getDisplay() + " " + ChatColor.WHITE + player.getName());
                        targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + " has changed you rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                    }

                    return false;
                }

            }

            // if it doesn't find the rank, it doesn't return and sends this:
            player.sendMessage(ChatColor.RED + "Rank not found!");

        }

        return false;
    }

}
