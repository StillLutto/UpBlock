package com.lutto.upblock.commands;

import com.lutto.upblock.UpBlock;
import com.lutto.upblock.utils.CustomPlayer;
import com.lutto.upblock.utils.SetPlaytimeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class PlaytimeCommand implements CommandExecutor {

    private UpBlock mainClass;

    public PlaytimeCommand(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid usage!");
            player.sendMessage(ChatColor.RED + "Please use /playtime <player>");
            return false;
        }

        if (Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {

            Player target = (Player) Bukkit.getOfflinePlayer(args[0]);

            SetPlaytimeEvent setPlaytimeEvent = new SetPlaytimeEvent(target);
            Bukkit.getPluginManager().callEvent(setPlaytimeEvent);

            Long targetPlaytime = mainClass.getPlayerManager().getCustomPlayer(target.getUniqueId()).getPlaytime() / 60;

            long playtimeToDays = TimeUnit.SECONDS.toDays(targetPlaytime);
            long playtimeToHours = (TimeUnit.SECONDS.toHours(targetPlaytime) - (playtimeToDays * 24L));
            long playtimeToMinutes = (TimeUnit.SECONDS.toMinutes(targetPlaytime) - (TimeUnit.SECONDS.toHours(targetPlaytime) * 60));
            long playtimeToSeconds = (TimeUnit.SECONDS.toSeconds(targetPlaytime) - (TimeUnit.SECONDS.toMinutes(targetPlaytime) * 60));
            player.sendMessage(ChatColor.GREEN + target.getName() + " has played for " + playtimeToDays + " days, " + playtimeToHours + " hours, " + playtimeToMinutes + " minutes, " + playtimeToSeconds + " seconds.");

        } else {
            player.sendMessage(ChatColor.RED + "Player not found! Has the player joined before?");
        }

        return false;
    }

}
