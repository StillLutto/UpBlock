package com.lutto.upblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Integer totalPlayersJoined = Bukkit.getOfflinePlayers().length + Bukkit.getOnlinePlayers().size() - 1; // need to -1 because it gets the player that joins as offline and online

        if (player.hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.DARK_AQUA + "Welcome to the server " + ChatColor.AQUA + player.getDisplayName() + ChatColor.DARK_AQUA + "!");
            return;
        }

        StringBuilder joinMessage = new StringBuilder();
        joinMessage.append(ChatColor.DARK_AQUA + "Welcome to the server " + ChatColor.BLUE + player.getDisplayName() + ChatColor.DARK_AQUA + "!").append("\n");
        joinMessage.append(ChatColor.AQUA + "Congrats! You are our #" + totalPlayersJoined + " player joined!");

        event.setJoinMessage(joinMessage.toString());

    }

}
