package com.lutto.upblock.listeners;

import com.lutto.upblock.UpBlock;
import com.lutto.upblock.utils.CustomPlayer;
import com.lutto.upblock.utils.SetPlaytimeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlaytimeListener implements Listener {

    private UpBlock mainClass;

    private HashMap<UUID, Long> playerJoinTime = new HashMap<>();

    public PlaytimeListener(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playerJoinTime.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {

        if (!playerJoinTime.containsKey(event.getPlayer().getUniqueId())) return;

        setPlaytime(event.getPlayer());
        playerJoinTime.remove(event.getPlayer().getUniqueId());

    }

    @EventHandler
    public void onSetPlaytime(SetPlaytimeEvent event) { // custom event, ik bad naming

        if (!playerJoinTime.containsKey(event.getPlayer().getUniqueId())) return;

        setPlaytime(event.getPlayer());
        playerJoinTime.put(event.getPlayer().getUniqueId(), 0L);

        System.out.println(playerJoinTime.get(event.getPlayer().getUniqueId()));

    }

    public void setPlaytime(Player player) {

        Long currentSessionTime = System.currentTimeMillis() - playerJoinTime.get(player.getUniqueId());
        long currentSessionInSeconds = currentSessionTime / 1000 % 60;

        CustomPlayer playerData = mainClass.getPlayerManager().getCustomPlayer(player.getUniqueId());
        playerData.setPlaytime(playerData.getPlaytime() + currentSessionInSeconds);

    }

}
