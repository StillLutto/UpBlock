package com.lutto.upblock.listeners;

import com.lutto.upblock.Rank;
import com.lutto.upblock.UpBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class RankListener implements Listener {

    private UpBlock mainClass;

    public RankListener(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        PermissionAttachment permissionAttachment;
        HashMap<UUID, PermissionAttachment> permissions = mainClass.getRankManager().getPermissions();

        if (!permissions.containsKey(player.getUniqueId())) {
            permissionAttachment = player.addAttachment(mainClass);
            permissions.put(player.getUniqueId(), permissionAttachment);
        } else {
            permissionAttachment = permissions.get(player);
        }

        for (String permission : mainClass.getRankManager().getRank(player.getUniqueId()).getPermissions()) {
            permissionAttachment.setPermission(permission, true);
        }

        Rank playerRank = mainClass.getRankManager().getRank(player.getUniqueId());
        player.setPlayerListName(playerRank.getDisplay() + " " + ChatColor.WHITE + player.getName());

    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        event.setCancelled(true);

        Player player = event.getPlayer();

        Bukkit.broadcastMessage(mainClass.getRankManager().getRank(player.getUniqueId()).getDisplay() + ChatColor.WHITE + " " + player.getName() + " -> " + ChatColor.WHITE + event.getMessage());

    }

}
