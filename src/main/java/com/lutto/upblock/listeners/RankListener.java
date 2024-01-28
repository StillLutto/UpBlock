package com.lutto.upblock.listeners;

import com.lutto.upblock.UpBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RankListener implements Listener {

    private UpBlock mainClass;

    public RankListener(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        mainClass.getNametagManager().setNametags(event.getPlayer());
        mainClass.getNametagManager().newTag(event.getPlayer());

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        mainClass.getNametagManager().removeTag(event.getPlayer());

    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        event.setCancelled(true);

        Player player = event.getPlayer();

        Bukkit.broadcastMessage(mainClass.getRankManager().getRank(player.getUniqueId()).getDisplay() + ChatColor.GRAY + " " + player.getName() + " -> " + ChatColor.GRAY + event.getMessage());

    }

}
