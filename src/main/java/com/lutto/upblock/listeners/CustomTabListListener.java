package com.lutto.upblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CustomTabListListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.setPlayerListHeader(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Up" + ChatColor.DARK_AQUA + ChatColor.ITALIC + "Block\n");
            onlinePlayer.setPlayerListFooter(ChatColor.AQUA + "\nPlayers online: " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + onlinePlayer.getServer().getOnlinePlayers().size() + "/99" + ChatColor.DARK_AQUA + "]");
        }

    }

}
